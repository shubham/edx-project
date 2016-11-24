package com.example.shubham.edx_project.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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
 * <p>
 * Created by shubham on 22/11/16.
 */
public class FavoriteListDataAdapter extends ArrayAdapter {

    private List<Object> mFavoriteCourseList = new ArrayList<Object>();
    private FavoriteListDataProvider mFavoriteListDataProvider;
    private SQLiteDatabase mSqLiteDatabase;
    private FavoriteListDB mFavoriteListDB;
    private FavoriteListActivity mFavoriteListActivity;
    /**
     * @param iPosition : for removing the item on position
     */
    public void removeItem(int iPosition) {
        remove(iPosition);
        String courseId = mFavoriteListDataProvider.getmCourseId();
        Log.d("courseId", "CourseId:::" + courseId);
        try {
            int numberOfRowDeleted = mFavoriteListDB.deleteCourseFromTable(courseId, mSqLiteDatabase);
            if (numberOfRowDeleted == 1) {
                Toast.makeText(getContext(), "Course Removed :" + iPosition, Toast.LENGTH_SHORT).show();
                mFavoriteCourseList.remove(iPosition);
                notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "Course Not Removed", Toast.LENGTH_SHORT).show();
            }
            //notifyDataSetChanged();
        } catch (SQLiteException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * DataHandler for favoriteList smooth scrolling
     */
    final static class DataHandler {
        TextView mCourseName;
        TextView mCourseNumber;
        TextView mCourseOrg;
        TextView mCourseStartDate;
        TextView mCoursePacing;
        ImageView mCourseImage;
        ImageView mNotFavorite;

        public DataHandler(View convertView) {
            mCourseName = (TextView) convertView.findViewById(R.id.favorite_course_name);
            mCourseNumber = (TextView) convertView.findViewById(R.id.favorite_course_number_by_organisation);
            mCourseOrg = (TextView) convertView.findViewById(R.id.favorite_organisation_name);
            mCourseStartDate = (TextView) convertView.findViewById(R.id.favorite_course_start_date);
            mCoursePacing = (TextView) convertView.findViewById(R.id.favorite_pacing_of_course);
            mCourseImage = (ImageView) convertView.findViewById(R.id.favorite_course_image);
            mNotFavorite = (ImageView) convertView.findViewById(R.id.not_favorite_icon);
        }
    }
        public FavoriteListDataAdapter(Context context, int resource) {
        super(context, resource);
    }
    @Override
    public void add(Object object) {
        super.add(object);
        try {
            mFavoriteCourseList.add(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_list_item, parent, false);
            dataHandler = new DataHandler(convertView);
            //put data into tag
            convertView.setTag(dataHandler);
        } else {
            dataHandler = (DataHandler) convertView.getTag();
        }
        try {
            mFavoriteListDB = new FavoriteListDB(getContext());
            mSqLiteDatabase = mFavoriteListDB.getReadableDatabase();
            mFavoriteListActivity = new FavoriteListActivity();
            mFavoriteListDataProvider = (FavoriteListDataProvider) this.getItem(position);
            dataHandler.mCourseName.setText(mFavoriteListDataProvider != null ? mFavoriteListDataProvider.getmCourseName() : null);
            Picasso.with(getContext()).load(mFavoriteListDataProvider.getmCourseImageUrl()).into(dataHandler.mCourseImage);
            dataHandler.mCourseNumber.setText(mFavoriteListDataProvider.getmCourseNumber());
            dataHandler.mCourseOrg.setText(mFavoriteListDataProvider.getmCourseOrg());
            dataHandler.mCourseStartDate.setText(mFavoriteListDataProvider.getmCourseStartDate());
            dataHandler.mCoursePacing.setText(mFavoriteListDataProvider.getmCoursePacing());

            dataHandler.mNotFavorite.setTag(position);
        } catch (NullPointerException e) {
            Log.e("NullPoint Error", "" + e.getMessage());
        } catch (android.database.SQLException e) {
            Log.e("Database Error", "" + e.getMessage());
        }
        return convertView;
    }
}

////    /*@Override
////    public void onClick(View view) {
////
////        String courseId = mFavoriteListDataProvider.getmCourseId();
////
////        Log.e("courseId", "CourseId:::" + courseId);
////        int numberOfRowDeleted = mFavoriteListDB.deleteCourseFromTable(courseId, mSqLiteDatabase);
////
////        if (numberOfRowDeleted == 1) {
////            Toast.makeText(getContext(), "Course Removed :" + view.getTag(), Toast.LENGTH_SHORT).show();
////            if (view.getTag() != null) {
////                mFavoriteCourseList.remove(view.getTag());
////            }
////
////            notifyDataSetChanged();
////            // mFavoriteListDB.close();
////            Log.e("DB Operation", "Table Close");
////        } else {
////            Toast.makeText(getContext(), "Course Not Removed", Toast.LENGTH_SHORT).show();
////
////        }
//
////}
//}