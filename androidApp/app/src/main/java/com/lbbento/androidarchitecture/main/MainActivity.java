package com.lbbento.androidarchitecture.main;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;

import com.lbbento.androidarchitecture.R;
import com.lbbento.androidarchitecture.discussions.DiscussionsFragment;
import com.lbbento.androidarchitecture.home.HomeFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //NavigationDrawerLayout
    private DrawerLayout mNavigationDrawer;
    //NavigationView
    private NavigationView mNavigationView;
    //Toolbar
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind NavigationDrawer
        mNavigationDrawer = (DrawerLayout) findViewById(R.id.main_side_menu);
        //Bind NavigationView
        mNavigationView = (NavigationView) findViewById(R.id.main_navigationview);
        //Bind toolbar
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);


        //Set the NavigationViewItemSelected LIstener
        mNavigationView.setNavigationItemSelectedListener(this);

        //Set the toolbar as our ActionBar.
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialize with home fragment
        switchContent(HomeFragment.newInstance());
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuselected) {
        switch (menuselected.getItemId()) {
            case R.id.action_1:
                switchContent(HomeFragment.newInstance());
                break;
            case R.id.action_2:
                switchContent(DiscussionsFragment.newInstance());
                break;
            default:
                return false;
        }
        //make the current item checked
        menuselected.setChecked(true);
        mNavigationDrawer.closeDrawers();
        return true;
    }

    public void switchContent(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content_frame, fragment, fragment.getClass().getName())
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case(android.R.id.home):
                if (mNavigationDrawer.isDrawerOpen(GravityCompat.START))
                    mNavigationDrawer.closeDrawer(GravityCompat.START);
                else
                    mNavigationDrawer.openDrawer(GravityCompat.START);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
