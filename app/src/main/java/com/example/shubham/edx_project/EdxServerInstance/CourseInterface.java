package com.example.shubham.edx_project.EdxServerInstance;

import com.example.shubham.edx_project.EdXModel.CourseModel.CourseApiModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by shubham on 15/11/16.
 */
public interface CourseInterface {

    String BASE_URL="https://courses.edx.org";
    @GET(URLExtensions.COURSE_EXTENSION)
    Call<CourseApiModel> getCourse();

    class CourseFactory
    {
        private static CourseInterface courseInterface;
        public static CourseInterface getInstance()
        {
            if(courseInterface==null)
            {
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                courseInterface=retrofit.create(CourseInterface.class);
                return courseInterface;
            }
            else
                return courseInterface;
        }
    }





}
