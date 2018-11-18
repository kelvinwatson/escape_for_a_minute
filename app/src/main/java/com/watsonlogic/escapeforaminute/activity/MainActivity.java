package com.watsonlogic.escapeforaminute.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.watsonlogic.escapeforaminute.R;
import com.watsonlogic.escapeforaminute.activity.introactivity.IntroActivity;
import com.watsonlogic.escapeforaminute.activity.webwork.WebWorkActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener
{
    @Inject
    SharedPreferences sharedPreferences;

    private static final int REQUEST_CODE_SETTINGS = 36;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        AndroidInjection.inject(this);
        //7. don't forget this, so that sharedPreferences can be injected

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view ->
        {
            // some action
        });

        DrawerLayout drawer = getDrawerLayout();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string
            .navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ((NavigationView)findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = getDrawerLayout();
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.action_settings:
                startActivityForResult(new Intent(this, SettingsActivity.class),
                    REQUEST_CODE_SETTINGS);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {
            // Handle the camera action
        }
        else if (id == R.id.our_web_work)
        {
//            final Intent webViewIntent = new Intent(this, WebViewActivity.class);
//            webViewIntent.putExtra(EXTRA_URL, "https://kelvinwatson.com/me");
//            webViewIntent.putExtra(EXTRA_TITLE, "Kelvin Watson");
//            startActivity(webViewIntent);
            startActivity(new Intent(this, WebWorkActivity.class));
        }
        else if (id == R.id.nav_slideshow)
        {

        }
        else if (id == R.id.nav_manage)
        {

        }
        else if (id == R.id.nav_share)
        {

        }
        else if (id == R.id.nav_send)
        {

        }

        getDrawerLayout().closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode)
        {
            case REQUEST_CODE_SETTINGS:
                if (RESULT_OK == resultCode)
                {
                    /*
                     * Clear existing preferences and restart the app
                     */
                    PreferenceManager.getDefaultSharedPreferences(this).edit().clear().commit();
                    Intent intent = new Intent(this, IntroActivity.class);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

    @Nullable
    private DrawerLayout getDrawerLayout()
    {
        return findViewById(R.id.drawer_layout);
    }
}
