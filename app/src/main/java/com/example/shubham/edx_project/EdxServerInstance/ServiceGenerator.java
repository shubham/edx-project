package com.example.shubham.edx_project.EdxServerInstance;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shubham on 16/11/16.
 */
public class ServiceGenerator {

    static String BASE_URL = "https://courses.edx.org";
    private static Retrofit retrofit=null;



    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit;
        } else
            return retrofit;
    }
}
