package com.ws.integration.helpers;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/***************************************************************
 * @author : Gabriel Henrique Cataldo Nascimento Paes
 * @since : 27 de fev de 2021
 ***************************************************************/

public class RetrofitHelper {
	
	@Value("${TIMEOUT_CONNECTION_SEG}")
	private static long TIMEOUT_CONNECTION_SEG;
	@Value("${TIMEOUT_READ_WRITE_SERVICE_SEG}")
	private static long TIMEOUT_READ_WRITE_SERVICE_SEG;

	@Nullable
    public static Retrofit BuilderDefault(@NonNull String url) {
    	if(url == null) return null;

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(TIMEOUT_CONNECTION_SEG, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ_WRITE_SERVICE_SEG, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_READ_WRITE_SERVICE_SEG, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();

        return new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
