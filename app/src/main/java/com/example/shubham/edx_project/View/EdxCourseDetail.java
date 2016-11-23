package com.example.shubham.edx_project.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shubham.edx_project.EdXModel.DetailModel.CourseDetail;
import com.example.shubham.edx_project.EdxServerInstance.EdxRequestInterface;
import com.example.shubham.edx_project.EdxServerInstance.ServiceGenerator;
import com.example.shubham.edx_project.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shubham on 21/11/16.
 */
public class EdxCourseDetail extends AppCompatActivity {

    private CourseDetail mCourseDetail;

    private ImageView mCourseImage;
    private TextView mOrgName;
    private TextView mCourseName;
    private TextView mCourseNumber;
    private TextView mStartDate;
    private TextView mCoursePacing;
    private TextView mCourseDetailText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        Intent courseIntent=getIntent();
        String courseDetailUrl=courseIntent.getStringExtra("courseId");
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Getting data");
        progressDialog.show();
        initViews();

        EdxRequestInterface edxRequestInterface = ServiceGenerator.getRetrofitInstance().create(EdxRequestInterface.class);

        Log.d("courseDetail", ""+ courseDetailUrl);
        Call<CourseDetail> courseApiModelCall = edxRequestInterface.getCourseDetail(courseDetailUrl);
        courseApiModelCall.enqueue(new Callback<CourseDetail>() {
            @Override
            public void onResponse(Call<CourseDetail> call, Response<CourseDetail> response) {

                mCourseDetail=response.body();

                String courseImageLink = mCourseDetail.getMedia().getImage().getLarge();
                String orgNameData = mCourseDetail.getOrg();
                String courseNameData = mCourseDetail.getName();
                String courseNumberData = mCourseDetail.getNumber();
                String courseStartDate = mCourseDetail.getStartDisplay();
                String coursePacingData = mCourseDetail.getPacing();
                String courseDetail=mCourseDetail.getOverviewId();

                Log.d("Data", courseImageLink);
                Log.d("Data", coursePacingData);
                Log.d("Data", courseNameData);
                if (courseStartDate != null)
                    Log.e("Data", courseStartDate);

                Log.d("Data", courseNumberData);
                Log.d("Data",courseDetail);

                Picasso.with(getApplicationContext()).load(courseImageLink).into(mCourseImage);
                mOrgName.setText(orgNameData);
                mCourseName.setText(courseNameData);
                mCourseNumber.setText(courseNumberData);
                mStartDate.setText(courseStartDate);
                mCoursePacing.setText(coursePacingData);

                mCourseDetailText.setText(Html.fromHtml(courseDetail));

                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<CourseDetail> call, Throwable t) {
                Log.d("Exception", t.getMessage());
                progressDialog.dismiss();
            }
        });
        }

    private void initViews() {

        mCourseImage = (ImageView) findViewById(R.id.course_image);
        mOrgName = (TextView) findViewById(R.id.organisation_name);
        mCourseName = (TextView) findViewById(R.id.course_name);
        mCourseNumber = (TextView) findViewById(R.id.course_number_by_organisation);
        mStartDate = (TextView) findViewById(R.id.course_start_date);
        mCoursePacing = (TextView)findViewById(R.id.pacing_of_course);
        mCourseDetailText=(TextView)findViewById(R.id.course_detail_textView);


    }
}
