package com.example.shubham.edx_project.Utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Network Connection Checking
 *
 * Created by shubham on 24/11/16.
 */
public class NetworkStateChecker {

    /**
     * Get the network info
     * @return network state information
     */
    private static NetworkInfo getNetworkInfo(){
        ConnectivityManager cm = (ConnectivityManager) AppContext.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    /**
     * Check if there is any connectivity
     * @return boolean
     */
    public static boolean isConnected(){
        NetworkInfo info = NetworkStateChecker.getNetworkInfo();
        return (info != null && info.isConnected());
    }
}
