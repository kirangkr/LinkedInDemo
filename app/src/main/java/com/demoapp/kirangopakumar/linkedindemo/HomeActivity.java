package com.demoapp.kirangopakumar.linkedindemo;

import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demoapp.kirangopakumar.linkedindemo.Utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import br.com.mauker.materialsearchview.MaterialSearchView;

public class HomeActivity extends AppCompatActivity {


    private static final String TAG = "HomeActivity";
    private BottomNavigationViewEx bottomNavigationViewEx;
    private   FragmentManager fragmentManager;

    private MaterialSearchView searchView;

    private ImageView profile_icon, search, settings;
    private TextView title;

    //private static final String[] suggestions = {"Apple", "Banana", "Motorola", "Samsung"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        profile_icon = (ImageView) findViewById(R.id.profile_icon);
        search = (ImageView) findViewById(R.id.search);
        settings = (ImageView) findViewById(R.id.settings);




        //Adding HomeFragment here
        fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        fragmentManager.beginTransaction().add(R.id.relLayout2,homeFragment).commit();


        //Bottom navigation
        bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottom_navigation_menu);
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);

        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.ic_home:

                        //Replace with home fragment
                        HomeFragment homeFragment = new HomeFragment();
                        fragmentManager.beginTransaction().replace(R.id.relLayout2,homeFragment).commit();
                        item.setChecked(true);

                        break;

                    case R.id.ic_network:

                        //Replace with network fragment
                        NetworkFragment networkFragment = new NetworkFragment();
                        fragmentManager.beginTransaction().replace(R.id.relLayout2,networkFragment).commit();
                        item.setChecked(true);
                        break;

                    case R.id.ic_alert:

                        //Replace with notification fragment
                        NotificationFragment notificationFragment = new NotificationFragment();
                        fragmentManager.beginTransaction().replace(R.id.relLayout2,notificationFragment).commit();
                        item.setChecked(true);
                        break;
                    case R.id.ic_messaging:

                        //Replace with message  fragment
                        MessageFragment messageFragment = new MessageFragment();
                        fragmentManager.beginTransaction().replace(R.id.relLayout2,messageFragment).commit();
                        item.setChecked(true);
                        break;
                    case R.id.ic_jobs:

                        //Replace with job fragment
                        JobFragment jobFragment = new JobFragment();
                        fragmentManager.beginTransaction().replace(R.id.relLayout2,jobFragment).commit();
                        item.setChecked(true);
                        break;

                }

                return false;
            }
        });



    }
    // Will call once the search icon is pressed
    public void gsearch (View view){

        searchView.openSearch();
    }

    //Will call when profile icon is clicked
    public void start_profile (View view){

        Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
        startActivity(intent);

    }


   //menu item
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle toolbar item clicks here. It'll
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.search:
                // Open the search view on the menu item click.

                searchView.openSearch();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (searchView.isOpen()) {
            // Close the search on the back button press.
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }




    @Override
    protected void onPause() {
        super.onPause();
        searchView.clearSuggestions();
    }

    @Override
    protected void onResume() {
        super.onResume();
        searchView.activityResumed();
        String[] arr = getResources().getStringArray(R.array.suggestions);

        searchView.addSuggestions(arr);
    }


}
