package com.zohaib.trivia.helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Utils {

    private static ProgressDialog proDialog;

    public static void showProgress(Context context){
        proDialog = ProgressDialog.show(context, "Please Wait", "Fetching Questions...");

    }

    public static void hideProgress(){
        if (proDialog != null){
            proDialog.dismiss();
            proDialog = null;
        }
    }

    public static void showToast(Context context , String message){
        Toast.makeText(context , message , Toast.LENGTH_SHORT).show();
    }
}
