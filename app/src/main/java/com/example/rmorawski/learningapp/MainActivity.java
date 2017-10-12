package com.example.rmorawski.learningapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rmorawski.learningapp.activity.FragmentDrawer;
import com.example.rmorawski.learningapp.activity.FragmentFriends;
import com.example.rmorawski.learningapp.activity.FragmentHome;
import com.example.rmorawski.learningapp.activity.FragmentLogin;
import com.example.rmorawski.learningapp.activity.FragmentMessages;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_header_image)
    ImageView ivProfile;

    private FragmentDrawer fragmentDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Use butterknife
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fragmentDrawer = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        fragmentDrawer.setUp(R.id.fragment_navigation_drawer, drawerLayout, mToolbar);
        fragmentDrawer.setDrawerListener(this);

        //display the first navigation drawer view on app launch
        displayView(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml

        int id = item.getItemId();

        if(id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.action_search) {
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        Log.d("OnDrawerItemSelected", "position = " +position);
        displayView(position);
    }

    @OnClick(R.id.nav_header_image)
    public void onProfileImageClick(View view) {
        displayView(3);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = "";

        switch(position) {
            case 0:
                fragment = new FragmentHome();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new FragmentFriends();
                title = getString(R.string.title_friends);
                break;
            case 2:
                fragment = new FragmentMessages();
                title = getString(R.string.title_messages);
                break;
            case 3:
                fragment = new FragmentLogin();
                title = getString(R.string.app_name);
                break;
            default:
                break;
        }

        if(fragment != null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.container_body, fragment);
            ft.commit();

            //set the toolbat title
            getSupportActionBar().setTitle(title);
        }
    }
}
