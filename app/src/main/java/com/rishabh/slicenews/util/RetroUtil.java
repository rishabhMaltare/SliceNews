package com.rishabh.slicenews.util;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rishabh on 09-04-2018.
 */

public class RetroUtil {

    private static Retrofit retrofit;
    private static OkHttpClient httpClient;

    public static <T> T getRetroService(Class<T> className) {
        return getRetrofit().create(className);
    }

    static Retrofit getRetrofit() {
        if (retrofit == null) {
            String BASE_URL = "https://newsapi.org/v2/";
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = new OkHttpClient.Builder().build();
        }
        return httpClient;
    }


}
