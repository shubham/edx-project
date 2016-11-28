package com.example.shubham.edx_project.View;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shubham.edx_project.FavoriteListDatabase.FavoriteListDB;
import com.example.shubham.edx_project.R;
import com.example.shubham.edx_project.Utility.AppContext;
import com.example.shubham.edx_project.Utility.NetworkStateChecker;
import com.squareup.picasso.Picasso;

/**
 * Edx Starting Page after splash
 *
 * Created by shubham on 14/11/16.
 *  */
public class EdxProjectActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mEdxBackground;
    private Button mFindCourseButton;
    private Button mFavouriteCoursesButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ed_xcourse);
        mEdxBackground=(ImageView)findViewById(R.id.edx_background);
        Picasso.with(AppContext.getAppContext()).load(R.drawable.launch_background).into(mEdxBackground);
        //button for opening the course activity
        mFindCourseButton = (Button) findViewById(R.id.find_courses_button);
        mFindCourseButton.setOnClickListener(this);
        //button for opening the favourite course activity
        mFavouriteCoursesButton=(Button)findViewById(R.id.favourite_courses_button);
        mFavouriteCoursesButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id)
        {
            case R.id.find_courses_button:
                //getting network state
                //starting findCourseActivity
                if (NetworkStateChecker.isConnected())
                 {
                    launchIntent(FindCourseActivity.class);
                 }
                else
                {
                    Toast.makeText(AppContext.getAppContext(),""+ "Network Connection Error",Toast.LENGTH_SHORT).show();
                    Log.e("Exception", String.valueOf(Exception.class));
                }
                break;
            case R.id.favourite_courses_button:
                //getting database instance
                FavoriteListDB favoriteListDB=new FavoriteListDB(AppContext.getAppContext());
                //getting readable database
                SQLiteDatabase mDatabase=favoriteListDB.getReadableDatabase();
                try {
                    //getting data from table and storing in cursor
                    Cursor cursor= favoriteListDB.getDataFromCourseTable(mDatabase);
                    int cursorLength=cursor.getCount();
                    if (cursorLength==0)
                    {
                        Toast.makeText(AppContext.getAppContext(),""+"No Course Added",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        launchIntent(FavoriteListActivity.class);
                    }
                    cursor.close();
                }
                catch (SQLiteException|NullPointerException e)
                {
                   e.printStackTrace();
                }

            break;
        }
    }
    /**
     * method for launching activity
     * @param iActivityClass instance of passed class name
     */
    private  void launchIntent(Class iActivityClass){
        //opening the passed class
        Intent intent=new Intent(AppContext.getAppContext(),iActivityClass);
        startActivity(intent);
    }
}
