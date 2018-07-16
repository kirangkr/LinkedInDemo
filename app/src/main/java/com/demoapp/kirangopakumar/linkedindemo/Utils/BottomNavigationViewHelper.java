package com.demoapp.kirangopakumar.linkedindemo.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;


import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationViewHelper {


    private static final String TAG = "BottomNavigati..Helper";


    //BottomnavigationView Setup
    public static  void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
        Log.d(TAG,"setupBottomNavigationView");

        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);




    }


    /*
    * BottomNavigationView click
    */

    public static void setupBottomnavigationClick(BottomNavigationViewEx bottomNavigationViewEx, Context context){

        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {





                return false;
            }
        });




    }

}
