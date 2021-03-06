package com.example.shubham.edx_project.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.example.shubham.edx_project.R;
import com.example.shubham.edx_project.Utility.AppContext;
import com.squareup.picasso.Picasso;

/**
 * splash activity
 *
 * Created by shubham on 17/11/16.
 */
public class EdxSplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=3000;
    private ImageView mSplashBackground ;
    private ImageView mEdxLogo;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edx_splash);
        mSplashBackground=(ImageView)findViewById(R.id.edx_background);
        mEdxLogo=(ImageView)findViewById(R.id.edx_logo);
        //setting background image and logo image of splash screen
        Picasso.with(AppContext.getAppContext()).load(R.drawable.launch_background).into(mSplashBackground);
        Picasso.with(AppContext.getAppContext()).load(R.drawable.launch_screen_logo).into(mEdxLogo);

        //delaying the launch of EdxProjectActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(EdxSplashActivity.this,EdxProjectActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
