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
 * Created by shubham on 14/11/16.
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

        //button for opening the course Fragment
        mFindCourseButton = (Button) findViewById(R.id.find_courses_button);
        mFindCourseButton.setOnClickListener(this);

        //button for opening the favourite course list fragment
        mFavouriteCoursesButton=(Button)findViewById(R.id.favourite_courses_button);
        mFavouriteCoursesButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        switch (id)
        {
            case R.id.find_courses_button:
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    Intent intent=new Intent(this,FindCourseActivity.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Network Connection not Available",Toast.LENGTH_SHORT).show();

                    Log.e("Exception", String.valueOf(Exception.class));
                }

                break;

            case R.id.favourite_courses_button:
                FavoriteListDB favoriteListDB=new FavoriteListDB(this);
                SQLiteDatabase mDatabase=favoriteListDB.getReadableDatabase();
               Cursor cursor= favoriteListDB.getDataFromCourseTable(mDatabase);

                int cursorLength=cursor.getCount();
                if (cursorLength==0)
                {
                    Toast.makeText(this,"No Course Added",Toast.LENGTH_SHORT).show();
                }
                else
                {

                    Intent favoriteListIntent=new Intent(this,FavoriteListActivity.class);
                        startActivity(favoriteListIntent);
                }

                break;

        }

    }
}
