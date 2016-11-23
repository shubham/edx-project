package com.example.shubham.edx_project.View;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.shubham.edx_project.Adapter.FavoriteListDataAdapter;
import com.example.shubham.edx_project.EdXModel.DBModelDataProvider.FavoriteListDataProvider;
import com.example.shubham.edx_project.FavoriteListDatabase.FavoriteListDB;
import com.example.shubham.edx_project.R;

/**
 * Created by shubham on 22/11/16.
 */
public class FavoriteListActivity extends AppCompatActivity {

    //global variable declaration
    ListView mListView;
    SQLiteDatabase mDatabase;
    FavoriteListDB mFavoriteListDB;
    Cursor mCursor;
    FavoriteListDataAdapter mFavoriteListDataAdapter;
    FavoriteListDataProvider mFavoriteListDataProvider;

    //string for data
    private String courseIdData;
    private String orgNameData;
    private String courseImageLink;
    private String courseNumberData;
    private String courseStartDate;
    private String coursePacingData;
    private String courseNameData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.favoritelist);

        setViews();

    }

    public void setViews() {
        mListView=(ListView)findViewById(R.id.favorite_list);
        mFavoriteListDataAdapter=new FavoriteListDataAdapter(getApplicationContext(),R.layout.favorite_list_item);
        mListView.setAdapter(mFavoriteListDataAdapter);
        mFavoriteListDB=new FavoriteListDB(getApplicationContext());
        mDatabase=mFavoriteListDB.getReadableDatabase();
        mCursor=mFavoriteListDB.getDataFromCourseTable(mDatabase);

        if (mCursor.moveToFirst())
        {

            do {
                courseIdData=mCursor.getString(0);
                courseNameData=mCursor.getString(1);
                courseNumberData=mCursor.getString(2);
                orgNameData=mCursor.getString(3);
                courseStartDate=mCursor.getString(4);
                coursePacingData=mCursor.getString(5);
                courseImageLink=mCursor.getString(6);

                mFavoriteListDataProvider=new FavoriteListDataProvider(courseIdData,courseNameData,courseNumberData,orgNameData,courseStartDate,coursePacingData,courseImageLink);

                //add to list
                mFavoriteListDataAdapter.add(mFavoriteListDataProvider);

            }while (mCursor.moveToNext());
        }
    }
}
