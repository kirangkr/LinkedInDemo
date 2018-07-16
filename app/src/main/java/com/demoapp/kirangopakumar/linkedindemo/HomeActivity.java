package com.demoapp.kirangopakumar.linkedindemo;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.demoapp.kirangopakumar.linkedindemo.Utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class HomeActivity extends AppCompatActivity {


    private static final String TAG = "HomeActivity";
    private BottomNavigationViewEx bottomNavigationViewEx;
    private   FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //Adding HomeFragment here
        fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        fragmentManager.beginTransaction().add(R.id.relLayout2,homeFragment).commit();


        bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottom_navigation_menu);

        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);



        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.ic_home:

                        //Replace home fragment
                        HomeFragment homeFragment = new HomeFragment();
                        fragmentManager.beginTransaction().replace(R.id.relLayout2,homeFragment).commit();
                        item.setChecked(true);

                        break;

                    case R.id.ic_network:

                        //Replace network fragment
                        NetworkFragment networkFragment = new NetworkFragment();
                        fragmentManager.beginTransaction().replace(R.id.relLayout2,networkFragment).commit();
                        item.setChecked(true);
                        break;

                    case R.id.ic_alert:

                        //Replace notification fragment
                        NotificationFragment notificationFragment = new NotificationFragment();
                        fragmentManager.beginTransaction().replace(R.id.relLayout2,notificationFragment).commit();
                        item.setChecked(true);
                        break;
                    case R.id.ic_messaging:

                        //Replace message  fragment
                        MessageFragment messageFragment = new MessageFragment();
                        fragmentManager.beginTransaction().replace(R.id.relLayout2,messageFragment).commit();
                        item.setChecked(true);
                        break;
                    case R.id.ic_jobs:

                        //Replace job fragment
                        JobFragment jobFragment = new JobFragment();
                        fragmentManager.beginTransaction().replace(R.id.relLayout2,jobFragment).commit();
                        item.setChecked(true);
                        break;

                }

                return false;
            }
        });



    }











}
