package com.example.shubham.edx_project.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shubham.edx_project.EdXModel.DBModelDataProvider.FavoriteListDataProvider;
import com.example.shubham.edx_project.FavoriteListDatabase.FavoriteListDB;
import com.example.shubham.edx_project.R;
import com.example.shubham.edx_project.View.FavoriteListActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shubham on 22/11/16.
 */
public class FavoriteListDataAdapter extends ArrayAdapter implements View.OnClickListener {
    //variable declaration
    List favoriteCourseList = new ArrayList();
    FavoriteListDataProvider mFavoriteListDataProvider;
    SQLiteDatabase mSqLiteDatabase;
    FavoriteListDB mFavoriteListDB;
    FavoriteListActivity mFavoriteListActivity;



    //handler for favoriteList
    static class DataHandler {

        TextView COURSE_NAME;
        TextView COURSE_NUMBER;
        TextView COURSE_ORG;
        TextView COURSE_START_DATE;
        TextView COURSE_PACING;
        ImageView COURSE_IMAGE;
        ImageView NOT_FAVORITE;


        public DataHandler(View convertView){

            COURSE_NAME=(TextView)convertView.findViewById(R.id.favorite_course_name);
            COURSE_NUMBER=(TextView)convertView.findViewById(R.id.favorite_course_number_by_organisation);
            COURSE_ORG=(TextView)convertView.findViewById(R.id.favorite_organisation_name);
            COURSE_START_DATE=(TextView)convertView.findViewById(R.id.favorite_course_start_date);
            COURSE_PACING=(TextView)convertView.findViewById(R.id.favorite_pacing_of_course);
            COURSE_IMAGE=(ImageView)convertView.findViewById(R.id.favorite_course_image);
            NOT_FAVORITE=(ImageView)convertView.findViewById(R.id.not_favorite_icon);

        }
    }


    public FavoriteListDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    //adding in list
    @Override
    public void add(Object object) {
        super.add(object);
       favoriteCourseList.add(object);
    }

    @Override
    public int getCount() {
        return favoriteCourseList.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return favoriteCourseList.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DataHandler iDataHandler;

        if (convertView==null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_list_item, null);

            iDataHandler = new DataHandler(convertView);

            //put data into tag
            convertView.setTag(iDataHandler);
        }
        else {
            iDataHandler = (DataHandler) convertView.getTag();

        }

        mFavoriteListDataProvider=(FavoriteListDataProvider)this.getItem(position);
        try {
            iDataHandler.COURSE_NAME.setText(mFavoriteListDataProvider != null ? mFavoriteListDataProvider.getmCourseName() : null);
            Picasso.with(getContext()).load(mFavoriteListDataProvider.getmCourseImageUrl()).into(iDataHandler.COURSE_IMAGE);
            iDataHandler.COURSE_NUMBER.setText(mFavoriteListDataProvider.getmCourseNumber());
            iDataHandler.COURSE_ORG.setText(mFavoriteListDataProvider.getmCourseOrg());
            iDataHandler.COURSE_START_DATE.setText(mFavoriteListDataProvider.getmCourseStartDate());
            iDataHandler.COURSE_PACING.setText(mFavoriteListDataProvider.getmCoursePacing());

            iDataHandler.NOT_FAVORITE.setOnClickListener(this);
            iDataHandler.NOT_FAVORITE.setTag(position);
        }catch (NullPointerException e)
        {
            Log.e("NullPoint Error",""+e.getMessage());
        }
        catch (android.database.SQLException e)
        {
            Log.e("Database Error",""+e.getMessage());
        }

        return  convertView;
    }
    @Override
    public void onClick(View view) {

            String courseId=mFavoriteListDataProvider.getmCourseId();

            mFavoriteListDB=new FavoriteListDB(getContext());
            mSqLiteDatabase=mFavoriteListDB.getReadableDatabase();
            mFavoriteListActivity=new FavoriteListActivity();
            int numberOfRowDeleted=mFavoriteListDB.deleteCourseFromTable(courseId,mSqLiteDatabase);

        if (numberOfRowDeleted==1)
        {
            Toast.makeText(getContext(),"Course Removed: "+view.getTag(),Toast.LENGTH_SHORT).show();
            if (view.getTag()!=null)
            {
                favoriteCourseList.remove((int)view.getTag());
            }

            notifyDataSetChanged();
        }
        else
        {
            Toast.makeText(getContext(),"Course Not Removed",Toast.LENGTH_SHORT).show();

        }

    }
}
