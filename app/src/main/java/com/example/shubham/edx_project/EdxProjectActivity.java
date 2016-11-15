package com.example.shubham.edx_project;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shubham.edx_project.EdXModel.CourseModel.CourseApiModel;
import com.example.shubham.edx_project.EdxServerInstance.CourseInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shubham on 14/11/16.
 */
public class EdxProjectActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mTestButton;
    private TextView mCourseName;
    private TextView mCourseDetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ed_xcourse);

        mTestButton = (Button) findViewById(R.id.test_button);
        mCourseDetail = (TextView) findViewById(R.id.course_detail);
        mCourseName = (TextView) findViewById(R.id.course_name);

        mTestButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            CourseInterface.CourseFactory.getInstance().getCourse().enqueue(new Callback<CourseApiModel>() {
                @Override
                public void onResponse(Call<CourseApiModel> call, Response<CourseApiModel> response) {

                    mCourseName.setText(response.body().getResults().get(1).getName());
                    mCourseDetail.setText(response.body().getResults().get(1).getShortDescription());

                }

                @Override
                public void onFailure(Call<CourseApiModel> call, Throwable t) {

                    Log.d("Exception", t.getMessage());

                }
            });


        } else {
            //to be handled later
            Log.e("Exception", String.valueOf(Exception.class));
        }
    }
}
