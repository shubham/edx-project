package com.example.shubham.edx_project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shubham.edx_project.EdXModel.CourseModel.Result;
import com.example.shubham.edx_project.FavoriteListDatabase.FavoriteListDB;
import com.example.shubham.edx_project.R;
import com.example.shubham.edx_project.View.EdxCourseDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * inflates data into the RecyclerView
 *
 * Created by shubham on 17/11/16.
 */
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>  {
    private List<Result> courseApiModelList;
    private SQLiteDatabase mSqLiteDatabase;
    private Context mContext;
    private FavoriteListDB mFavoriteListDB;

    private String mOrgNameData;
    private String mCourseImageLink;
    private String mCourseNumberData;
    private String mCourseStartDate;
    private String mCoursePacingData;
    private String mCourseNameData;
    private String mCourseIdData;
    // adapter constructor
    public CourseAdapter(List<Result> courseApiModelList, Context context) {
        this.courseApiModelList = courseApiModelList;
        this.mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //data getting from json and storing in strings
        try {
            mCourseImageLink = courseApiModelList.get(position).getMedia().getImage().getLarge();
            mOrgNameData = courseApiModelList.get(position).getOrg();
            mCourseNameData = courseApiModelList.get(position).getName();
            mCourseNumberData = courseApiModelList.get(position).getNumber();
            mCourseStartDate = courseApiModelList.get(position).getStartDisplay();
            mCoursePacingData = courseApiModelList.get(position).getPacing();
            mCourseIdData=courseApiModelList.get(position).getCourseId();
            //Logging Coming Data
            Log.d("Data", mCourseImageLink);
            Log.d("Data", mCoursePacingData);
            Log.d("Data", mCourseNameData);
            if (mCourseStartDate != null)
                Log.d("Data", mCourseStartDate);
            Log.d("Data", mCourseNumberData);
            Log.d("Data",mCourseIdData);
            //setting data in views
            Picasso.with(mContext).load(mCourseImageLink).error(android.R.drawable.stat_notify_error).into(holder.courseImage);
            holder.orgName.setText(mOrgNameData);
            holder.courseName.setText(mCourseNameData);
           //setting courseId for courseName
            holder.courseImage.setTag(mCourseIdData);
            holder.courseNumber.setText(mCourseNumberData);
            holder.startDate.setText(mCourseStartDate);
            holder.coursePacing.setText(mCoursePacingData);
            //setting tag for courseId
            holder.favoriteIcon.setTag(mCourseIdData);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getItemCount() {
        return (courseApiModelList != null) ? courseApiModelList.size() : 0;
    }

    /**
     * ViewHolder Class for smooth scrolling in the view
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView courseImage;
        private ImageView favoriteIcon;
        private TextView orgName;
        private TextView courseName;
        private TextView courseNumber;
        private TextView startDate;
        private TextView coursePacing;
        //constructor for initialising the views and calling the activity respective of the button click
        public ViewHolder(View itemView)
        {
            super(itemView);
            courseImage = (ImageView) itemView.findViewById(R.id.course_image);
            orgName = (TextView) itemView.findViewById(R.id.organisation_name);
            courseName = (TextView) itemView.findViewById(R.id.course_name);
            courseNumber = (TextView) itemView.findViewById(R.id.course_number_by_organisation);
            startDate = (TextView) itemView.findViewById(R.id.course_start_date);
            coursePacing = (TextView) itemView.findViewById(R.id.pacing_of_course);
            favoriteIcon=(ImageView)itemView.findViewById(R.id.favorite_icon);

            courseImage.setOnClickListener(this);
            favoriteIcon.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.course_image)
            {
                Intent courseDetailIntent=new Intent(mContext, EdxCourseDetail.class);
                courseDetailIntent.putExtra("courseId",(String) view.getTag());
                mContext.startActivity(courseDetailIntent);
            }
            if (view.getId()==R.id.favorite_icon)
            {
                mFavoriteListDB=new FavoriteListDB(mContext);
                //getting writable database
                mSqLiteDatabase=mFavoriteListDB.getWritableDatabase();
                long checking=mFavoriteListDB.addData((String )view.getTag(), mCourseNameData, mCourseNumberData, mOrgNameData, mCourseStartDate, mCoursePacingData, mCourseImageLink,mSqLiteDatabase);
                //Checking for insertion
                if(checking>0) {
                    Toast.makeText(mContext, "One Course Added", Toast.LENGTH_SHORT).show();
                    Log.d("DB Operation","one course added");
                }
                else
                {
                    Toast.makeText(mContext, "Already Added", Toast.LENGTH_SHORT).show();
                }
                mFavoriteListDB.close();
                Log.d("DB Operation","Table Close");
            }
        }
    }
}
