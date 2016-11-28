package com.example.shubham.edx_project.View;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.shubham.edx_project.Adapter.FavoriteListDataAdapter;
import com.example.shubham.edx_project.EdXModel.DBModelDataProvider.FavoriteListDataProvider;
import com.example.shubham.edx_project.FavoriteListDatabase.FavoriteListDB;
import com.example.shubham.edx_project.R;
import com.example.shubham.edx_project.Utility.AppContext;

/**
 * favouriteCourse List Activity for showing the courses
 *
 * Created by shubham on 22/11/16.
 */
public class FavoriteListActivity extends AppCompatActivity {
    // variable declaration
    private ListView mListView;
    private SQLiteDatabase mDatabase;
    private FavoriteListDB mFavoriteListDB;
    private Cursor mCursor;
    private FavoriteListDataAdapter mFavoriteListDataAdapter;
    private FavoriteListDataProvider mFavoriteListDataProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favoritelist);
        setViews();
    }
    /**
     * setViews for setting views and populating data in list from Database
     */
    private void setViews() {
        mListView = (ListView) findViewById(R.id.favorite_list);
        mFavoriteListDataAdapter = new FavoriteListDataAdapter(this, R.layout.favorite_list_item);
        mListView.setAdapter(mFavoriteListDataAdapter);
        settingData();
    }
    public  void settingData()
    {
        try
        {
            mFavoriteListDB = new FavoriteListDB(AppContext.getAppContext());
            mDatabase = mFavoriteListDB.getReadableDatabase();
            mCursor = mFavoriteListDB.getDataFromCourseTable(mDatabase);
            //populating data if data is present ,else calling edxProjectActivity
            if (mCursor.moveToFirst()) {
                do {
                    String mCourseIdData = mCursor.getString(0);
                    String mCourseNameData = mCursor.getString(1);
                    String mCourseNumberData = mCursor.getString(2);
                    String mOrgNameData = mCursor.getString(3);
                    String mCourseStartDate = mCursor.getString(4);
                    String mCoursePacingData = mCursor.getString(5);
                    String mCourseImageLink = mCursor.getString(6);
                    mFavoriteListDataProvider = new FavoriteListDataProvider(mCourseIdData, mCourseNameData, mCourseNumberData, mOrgNameData, mCourseStartDate, mCoursePacingData, mCourseImageLink);
                    //add to list
                    mFavoriteListDataAdapter.add(mFavoriteListDataProvider);
                } while (mCursor.moveToNext());
            }
            else
            {
                Intent intent=new Intent(this,EdxProjectActivity.class);
                startActivity(intent);
            }
        }catch (CursorIndexOutOfBoundsException | SQLiteException | NullPointerException e)
        {
            e.printStackTrace();
        }
    }
}