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
import com.example.shubham.edx_project.Utility.AppContext;
import com.example.shubham.edx_project.Utility.NetworkStateChecker;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Show Course Detail for a Particular Course
 *
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
    private String mCourseDetailUrl;
    private ProgressDialog mProgressDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        Intent courseIntent=getIntent();
        mCourseDetailUrl=courseIntent.getStringExtra("courseId");
        mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setMessage("Getting data");
        mProgressDialog.show();
        initViews();
        loadCourseDetail();
        }
    /**
     * loadCourseDetail for calling api and setting data in views
     */
    private void loadCourseDetail() {
        Log.d("courseDetail", ""+ mCourseDetailUrl);
        if (NetworkStateChecker.isConnected())
        {
            EdxRequestInterface edxRequestInterface = ServiceGenerator.getRetrofitInstance().create(EdxRequestInterface.class);
            Call<CourseDetail> courseApiModelCall = edxRequestInterface.getCourseDetail(mCourseDetailUrl);
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
                    //logging data
                    Log.d("Data", courseImageLink);
                    Log.d("Data", coursePacingData);
                    Log.d("Data", courseNameData);
                    if (courseStartDate != null)
                        Log.d("Data", courseStartDate);
                    Log.d("Data", courseNumberData);
                    Log.d("Data",courseDetail);
                    //setting data
                    Picasso.with(AppContext.getAppContext()).load(courseImageLink).into(mCourseImage);
                    mOrgName.setText(orgNameData);
                    mCourseName.setText(courseNameData);
                    mCourseNumber.setText(courseNumberData);
                    mStartDate.setText(courseStartDate);
                    mCoursePacing.setText(coursePacingData);
                    mCourseDetailText.setText(Html.fromHtml(courseDetail));
                    mProgressDialog.dismiss();
                }
                @Override
                public void onFailure(Call<CourseDetail> call, Throwable t) {
                    Log.d("Exception", t.getMessage());
                    mProgressDialog.dismiss();
                }
            });
        }
    }
    /**
     * initViews for initialising views of detail_layout
     */
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