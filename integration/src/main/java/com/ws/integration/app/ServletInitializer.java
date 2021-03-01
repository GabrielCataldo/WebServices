package com.ws.integration.app;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(IntegrationApplication.class);
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException{
	    super.onStartup(servletContext);
    }

}
