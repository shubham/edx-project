package com.example.shubham.edx_project.EdxServerInstance;

import com.example.shubham.edx_project.EdXModel.CourseModel.CourseApiModel;
import com.example.shubham.edx_project.EdXModel.DetailModel.CourseDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by shubham on 15/11/16.
 */
public interface EdxRequestInterface {

        @GET("/api/courses/v1/courses/")
        Call<CourseApiModel> getCourse(@Query("page") int id);

        @GET("/api/courses/v1/courses/{courseId}")
        Call<CourseDetail> getCourseDetail(@Path("courseId")String courseId);




}
