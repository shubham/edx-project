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
 * Created by shubham on 17/11/16.
 */
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>  {

    private List<Result> courseApiModelList;
    private LayoutInflater mLayoutInflater;
    private SQLiteDatabase mSqLiteDatabase;
    private Context mContext;
    private FavoriteListDB mFavoriteListDB;


   // private String courseIdData;
    private String orgNameData;
    private String courseImageLink;
    private String courseNumberData;
    private String courseStartDate;
    private String coursePacingData;
    private String courseNameData;

    public CourseAdapter(List<Result> courseApiModelList, Context context, LayoutInflater layoutInflater) {
        this.courseApiModelList = courseApiModelList;
        this.mContext = context;
        mLayoutInflater = layoutInflater;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //data getting from json and storing in strings
        try {
            courseImageLink = courseApiModelList.get(position).getMedia().getImage().getLarge();
            orgNameData = courseApiModelList.get(position).getOrg();
            courseNameData = courseApiModelList.get(position).getName();
            courseNumberData = courseApiModelList.get(position).getNumber();
            courseStartDate = courseApiModelList.get(position).getStartDisplay();
            coursePacingData = courseApiModelList.get(position).getPacing();
            String courseIdData=courseApiModelList.get(position).getCourseId();

            //Logging Coming Data
            Log.d("Data", courseImageLink);
            Log.d("Data", coursePacingData);
            Log.d("Data", courseNameData);
            if (courseStartDate != null)
                Log.e("Data", courseStartDate);

            Log.d("Data", courseNumberData);
            Log.d("Data",courseIdData);

            //setting data in views
            Picasso.with(mContext).load(courseImageLink).error(android.R.drawable.stat_notify_error).into(holder.courseImage);
            holder.orgName.setText(orgNameData);
            holder.courseName.setText(courseNameData);
            holder.courseName.setTag(courseIdData);

            holder.courseNumber.setText(courseNumberData);
            holder.startDate.setText(courseStartDate);
            holder.coursePacing.setText(coursePacingData);
            holder.favoriteIcon.setTag(courseIdData);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return (courseApiModelList != null) ? courseApiModelList.size() : 0;
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView courseImage;
        private ImageView favoriteIcon;
        private TextView orgName;
        private TextView courseName;
        private TextView courseNumber;
        private TextView startDate;
        private TextView coursePacing;


        public ViewHolder(View itemView) {
            super(itemView);

            courseImage = (ImageView) itemView.findViewById(R.id.course_image);
            orgName = (TextView) itemView.findViewById(R.id.organisation_name);
            courseName = (TextView) itemView.findViewById(R.id.course_name);
            courseNumber = (TextView) itemView.findViewById(R.id.course_number_by_organisation);
            startDate = (TextView) itemView.findViewById(R.id.course_start_date);
            coursePacing = (TextView) itemView.findViewById(R.id.pacing_of_course);
            favoriteIcon=(ImageView)itemView.findViewById(R.id.favorite_icon);


            courseName.setOnClickListener(this);
            favoriteIcon.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.course_name)
            {
                Intent courseDetailIntent=new Intent(mContext, EdxCourseDetail.class);
                courseDetailIntent.putExtra("courseId",(String) view.getTag());
                mContext.startActivity(courseDetailIntent);
            }
            if (view.getId()==R.id.favorite_icon)
            {
                mFavoriteListDB=new FavoriteListDB(mContext);

                mSqLiteDatabase=mFavoriteListDB.getWritableDatabase();

                long checking=mFavoriteListDB.addData((String )view.getTag(),courseNameData,courseNumberData,orgNameData,courseStartDate,coursePacingData,courseImageLink,mSqLiteDatabase);
                //Checking for insertion
                if(checking>0) {
                    Toast.makeText(mContext, "One Course Added", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(mContext, "Already Added", Toast.LENGTH_SHORT).show();
                }
                mFavoriteListDB.close();
                Log.e("DB Operation","Table Close");

            }



        }
    }
}
