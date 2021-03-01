package com.ws.integration.helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.google.gson.Gson;


/***************************************************************
 * @author : Gabriel Henrique Cataldo Nascimento Paes
 * @since : 23 de nov de 2020
 ***************************************************************/

@Configuration
public class EmailClientHelper {
	
	
	public static class Builder{
		
		private String toEmail = "gabrielcataldo20@gmail.com";
		private String title = "ERROR WS INTEGRATION (" + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(Calendar.getInstance().getTime()) +")";

		private String fromEmail = "integrationws.exceptions@gmail.com";
		private String fromEmailPassword = "d494922f-41ed-42d7-ad46-75c24c563781";
		
		public Builder() {
		}
		
		public Builder toEmail(@Nullable String toEmail) {
			this.toEmail = toEmail;
			return this;
		}
		
		public Builder titleEmail(@Nullable String title) {
			this.title = title;
			return this;
		}
		
		public void sendEmail(@NonNull String mensagemEmail) {
		
			System.out.println("sendEmail to: "+toEmail);
			Session session = createSessionEmail();
			
			//create message using session
			MimeMessage message = new MimeMessage(session);
			
			try {

				prepareEmailMessage(message, toEmail, title, mensagemEmail);
				
				//sending message
				Transport.send(message);
				
				System.out.println("Send Sucess!");
				  
			} catch (MessagingException e) {
				System.out.println("Not Send Email: ERROR \n\n"+e.getMessage());
			}
			
	
			  
		}
		
		public void sendEmailException(@Nullable Exception e) {
			
			Session session = createSessionEmail();
			
			//create message using session
			MimeMessage message = new MimeMessage(session);
			
			String messageEmail = null;
			
			if (e != null) {
				
				messageEmail = prepareExceptionMessage(e);

			}
			
			else
				return;
				
			
			try {

				prepareEmailMessage(message, toEmail, title, messageEmail);
				
				//sending message
				Transport.send(message);
				
				//System.out.println("Send Exception Sucess!");
				  
			} catch (MessagingException e2) {
				//System.out.println("Not Send Email Exception: ERROR \n\n"+e2.getMessage());
			}  
		}

		Session createSessionEmail() {
			  
	      Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");

	      Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	          protected PasswordAuthentication getPasswordAuthentication() {
	              return new PasswordAuthentication(fromEmail, fromEmailPassword);
	          }
	      });
	      
	      return session;
	      
		}
		
		void prepareEmailMessage(MimeMessage message, String toEmail, String title, String body)
		          throws MessagingException {
		      message.setContent(body, "text/html; charset=utf-8");
		      message.setFrom(new InternetAddress(fromEmail));
		      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
		      message.setSubject(title);
		}
		
		
		String prepareExceptionMessage(@NonNull Exception e) {
			String cause = e.getCause() != null ? e.getCause().toString() : "null";
			String message1 = e.getMessage();
			String localize = e.getLocalizedMessage();
			String stacktrace = new Gson().toJson(e.getStackTrace());
			return "CAUSE: "+cause
							+"\n\n ---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
							+"MESSAGE: "+message1
							+"\n\n ---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
							+"LOCALIZED_MESSAGE: "+localize
							+"\n\n  ---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
							+"STACK_TRACE: \n\n"+stacktrace
							;
			
		}
		
	}
}
