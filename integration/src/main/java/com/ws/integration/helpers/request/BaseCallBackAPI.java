package com.ws.integration.helpers.request;

import java.net.HttpURLConnection;

import org.springframework.lang.Nullable;

import com.ws.integration.helpers.EmailClientHelper;
import com.ws.integration.interfaces.ICallbackAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/***************************************************************
 * @author : Gabriel Henrique Cataldo Nascimento Paes
 * @since : 27 de fev de 2021
 ***************************************************************/
public abstract class BaseCallBackAPI<T> implements Callback<T> {

    @Nullable
    protected ICallbackAPI<T> iCallbackAPI;

    private boolean sendAlertToEmail = false;

    public BaseCallBackAPI(boolean sendAlertToEmail) {
        this.sendAlertToEmail = sendAlertToEmail;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        int responseCode = response.code();

        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
            setOnSuccessResponse(response);
            return;

        } else if (sendAlertToEmail) 
        	//todo: aqui enviamos o email de erro
        	new EmailClientHelper.Builder().sendEmailException(new Exception("Error code: " + responseCode));

        setOnFailureResponse(call, null);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
    	if(t != null) 
    		t.printStackTrace();
    	
    	if (sendAlertToEmail) 
    		new EmailClientHelper.Builder().sendEmailException(new Exception(t));
    	
        setOnFailureResponse(call, t);
    }

    void setOnSuccessResponse(Response<T> response) {
        if (iCallbackAPI != null)
            iCallbackAPI.onSuccessResponse(response);
    }

    void setOnFailureResponse(Call<T> call, @Nullable Throwable t) {
        if (iCallbackAPI != null)
            iCallbackAPI.onFailureResponse(call, t);
    }


}
