package com.rishabh.slicenews.util;

import com.rishabh.slicenews.model.News;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

/**
 * Created by Rishabh on 08-04-2018.
 */

public interface IntroFit {
    @GET("top-headlines")
    public Call<News> getResponse(
            @QueryMap Map<String, String> map
    );


    @Multipart
    @POST("capture")
    Call<ResponseBody> upload(
            @PartMap Map<String, String> map,
            @Part MultipartBody.Part file
    );

}
