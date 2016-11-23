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
 * inflates data into the favorite list
 *
 * Created by shubham on 22/11/16.
 */
public class FavoriteListDataAdapter extends ArrayAdapter implements View.OnClickListener {
    //variable declaration
    private List mFavoriteCourseList = new ArrayList();
    private FavoriteListDataProvider mFavoriteListDataProvider;
    private SQLiteDatabase mSqLiteDatabase;
    private FavoriteListDB mFavoriteListDB;
    private FavoriteListActivity mFavoriteListActivity;



    //handler for favoriteList
     static class DataHandler {

        TextView mCourseName;
        TextView mCourseNumber;
        TextView mCourseOrg;
        TextView mCourseStartDate;
        TextView mCoursePacing;
        ImageView mCourseImage;
        ImageView mNotFavorite;


        public DataHandler(View convertView){

            mCourseName =(TextView)convertView.findViewById(R.id.favorite_course_name);
            mCourseNumber =(TextView)convertView.findViewById(R.id.favorite_course_number_by_organisation);
            mCourseOrg =(TextView)convertView.findViewById(R.id.favorite_organisation_name);
            mCourseStartDate =(TextView)convertView.findViewById(R.id.favorite_course_start_date);
            mCoursePacing =(TextView)convertView.findViewById(R.id.favorite_pacing_of_course);
            mCourseImage =(ImageView)convertView.findViewById(R.id.favorite_course_image);
            mNotFavorite=(ImageView)convertView.findViewById(R.id.not_favorite_icon);

        }
    }


    public FavoriteListDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    //adding in list
    @Override
    public void add(Object object) {
        super.add(object);
       mFavoriteCourseList.add(object);
    }

    @Override
    public int getCount() {
        return mFavoriteCourseList.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return mFavoriteCourseList.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DataHandler dataHandler;

        if (convertView==null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_list_item, null);

            dataHandler = new DataHandler(convertView);

            //put data into tag
            convertView.setTag(dataHandler);
        }
        else {
            dataHandler = (DataHandler) convertView.getTag();

        }

        mFavoriteListDataProvider=(FavoriteListDataProvider)this.getItem(position);
        try {
            dataHandler.mCourseName.setText(mFavoriteListDataProvider != null ? mFavoriteListDataProvider.getmCourseName() : null);
            Picasso.with(getContext()).load(mFavoriteListDataProvider.getmCourseImageUrl()).into(dataHandler.mCourseImage);
            dataHandler.mCourseNumber.setText(mFavoriteListDataProvider.getmCourseNumber());
            dataHandler.mCourseOrg.setText(mFavoriteListDataProvider.getmCourseOrg());
            dataHandler.mCourseStartDate.setText(mFavoriteListDataProvider.getmCourseStartDate());
            dataHandler.mCoursePacing.setText(mFavoriteListDataProvider.getmCoursePacing());

            dataHandler.mNotFavorite.setOnClickListener(this);
            dataHandler.mNotFavorite.setTag(position);
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
            Toast.makeText(getContext(),""+mFavoriteListActivity.getString(R.string.course_removed)+view.getTag(),Toast.LENGTH_SHORT).show();
            if (view.getTag()!=null)
            {
                mFavoriteCourseList.remove((int)view.getTag());
            }

            notifyDataSetChanged();
            mFavoriteListDB.close();
            Log.e("DB Operation","Table Close");
        }
        else
        {
            Toast.makeText(getContext(), ""+R.string.course_not_removed,Toast.LENGTH_SHORT).show();

        }

    }
}
