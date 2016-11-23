package com.example.shubham.edx_project.View;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shubham.edx_project.FavoriteListDatabase.FavoriteListDB;
import com.example.shubham.edx_project.R;
import com.squareup.picasso.Picasso;

/**
 * Edx Starting Page after splash
 *
 * Created by shubham on 14/11/16.
 *  *
 */
public class EdxProjectActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mEdxBackground;

    private Button mFindCourseButton;
    private Button mFavouriteCoursesButton;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ed_xcourse);

        mEdxBackground=(ImageView)findViewById(R.id.edx_background);
        Picasso.with(getApplicationContext()).load(R.drawable.launch_background).into(mEdxBackground);

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

                //checking network state
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                //starting findCourseActivity
                if (networkInfo != null && networkInfo.isConnected()) {
                    Intent intent=new Intent(this,FindCourseActivity.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(getApplicationContext(), R.string.network_connection_error,Toast.LENGTH_SHORT).show();

                    Log.e("Exception", String.valueOf(Exception.class));
                }

                break;

            case R.id.favourite_courses_button:
                //showing FavoriteCourseList activity
                //getting database instance
            FavoriteListDB favoriteListDB=new FavoriteListDB(this);
                //getting readable database
            SQLiteDatabase mDatabase=favoriteListDB.getReadableDatabase();
                //getting data from table and storing in cursor
            Cursor cursor= favoriteListDB.getDataFromCourseTable(mDatabase);

            int cursorLength=cursor.getCount();
            if (cursorLength==0)
            {
                Toast.makeText(this, R.string.no_course_added,Toast.LENGTH_SHORT).show();
            }
            else
            {
                //opening FavoriteListActivity
                Intent favoriteListIntent=new Intent(this,FavoriteListActivity.class);
                startActivity(favoriteListIntent);
            }
            break;
        }

    }
}
