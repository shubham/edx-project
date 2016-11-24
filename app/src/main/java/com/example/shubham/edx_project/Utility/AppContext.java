package com.example.shubham.edx_project.Utility;

import android.app.Application;
import android.content.Context;

/**
 * Class for getting context of application
 *
 *  Created by shubham on 24/11/16.
 */
public class AppContext extends Application {

        private static Context mContext;

        public void onCreate(){
            super.onCreate();
            mContext = this.getApplicationContext();
        }

    /***
     * method for returning the app context which can be accessed directly
     * @return app ontext
     */
        public static Context getAppContext(){
            return mContext;
        }
    }

