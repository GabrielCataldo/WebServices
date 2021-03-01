package com.ws.integration.interfaces;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import retrofit2.Call;
import retrofit2.Response;


/***************************************************************
 * @author : Gabriel Henrique Cataldo Nascimento Paes
 * @since : 27 de fev de 2021
 ***************************************************************/
public interface ICallbackAPI<T> {
    void onSuccessResponse(@NonNull Response<T> response);
    void onFailureResponse(@NonNull Call<T> call, @Nullable Throwable t);
}
