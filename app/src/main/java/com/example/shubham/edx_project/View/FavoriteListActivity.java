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
    private ListView mListView;
    private SQLiteDatabase mDatabase;
    private FavoriteListDB mFavoriteListDB;
    private Cursor mCursor;
    private FavoriteListDataAdapter mFavoriteListDataAdapter;
    private FavoriteListDataProvider mFavoriteListDataProvider;

    //string for data
    private String mCourseIdData;
    private String mOrgNameData;
    private String mCourseImageLink;
    private String mCourseNumberData;
    private String mCourseStartDate;
    private String mCoursePacingData;
    private String mCourseNameData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.favoritelist);

        setViews();

    }

    /**
     *setViews for setting views and populating data in list from Database
     */
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
                mCourseIdData =mCursor.getString(0);
                mCourseNameData =mCursor.getString(1);
                mCourseNumberData =mCursor.getString(2);
                mOrgNameData =mCursor.getString(3);
                mCourseStartDate =mCursor.getString(4);
                mCoursePacingData =mCursor.getString(5);
                mCourseImageLink =mCursor.getString(6);

                mFavoriteListDataProvider=new FavoriteListDataProvider(mCourseIdData, mCourseNameData, mCourseNumberData, mOrgNameData, mCourseStartDate, mCoursePacingData, mCourseImageLink);

                //add to list
                mFavoriteListDataAdapter.add(mFavoriteListDataProvider);

            }while (mCursor.moveToNext());
        }
    }
}
