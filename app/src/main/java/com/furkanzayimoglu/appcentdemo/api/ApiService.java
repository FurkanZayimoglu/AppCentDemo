package com.furkanzayimoglu.appcentdemo.api;

import com.furkanzayimoglu.appcentdemo.model.ImageResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {


    @GET("rest/")
    Call<ImageResponseModel> getRecent(@Query("method") String method, @Query("api_key")String API_KEY, @Query("extras") String EXTRA_SMALL_URL,
                                       @Query("per_page") int perpage, @Query("page") int page, @Query("format") String format, @Query("nojsoncallback") String set);

}


