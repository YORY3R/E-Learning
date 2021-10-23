package com.yory3r.e_learning.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yory3r.e_learning.R;
import com.yory3r.e_learning.fragment.MainActivityFragmentAbout;
import com.yory3r.e_learning.fragment.MainActivityFragmentHome;
import com.yory3r.e_learning.fragment.MainActivityFragmentProfil;
import com.yory3r.e_learning.databinding.ActivityMainBinding;
import com.yory3r.e_learning.preferences.LoginPreferences;
import com.yory3r.e_learning.preferences.ThemePreferences;

public class MainActivity extends AppCompatActivity
{
    private LoginPreferences loginPreferences;
    private ThemePreferences themePreferences;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setMainActivity(this);

        loginPreferences = new LoginPreferences(MainActivity.this);
        themePreferences = new ThemePreferences(MainActivity.this);

        checkLogin();
        checkTheme();

        MainActivityFragmentHome mainActivityFragmentHome = new MainActivityFragmentHome();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentChange,mainActivityFragmentHome).commit();

        activityMainBinding.topAppBar.setTitle("Halo " + loginPreferences.getUserLogin().getUsername());
    }

    public Toolbar.OnMenuItemClickListener topAppBar = new Toolbar.OnMenuItemClickListener()
    {
        @Override
        public boolean onMenuItemClick(MenuItem item)
        {
            if(item.getItemId() == R.id.topAdmin)
            {
                Intent intent = new Intent(MainActivity.this,AdminActivity.class);
                startActivity(intent);
            }
            else if(item.getItemId() == R.id.topPengaturan)
            {
                Intent intent = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent);
            }
            else if(item.getItemId() == R.id.topLogout)
            {
                loginPreferences.Logout();
                checkLogin();
            }

            return true;
        }
    };

    public BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationView = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        MainActivityFragmentHome mainActivityFragmentHome = new MainActivityFragmentHome();
        MainActivityFragmentProfil mainActivityFragmentProfil = new MainActivityFragmentProfil();
        MainActivityFragmentAbout mainActivityFragmentAbout = new MainActivityFragmentAbout();

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            if(item.getItemId() == R.id.bottomHome)
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentChange,mainActivityFragmentHome).commit();
            }
            else if(item.getItemId() == R.id.bottomProfil)
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentChange,mainActivityFragmentProfil).commit();
            }
            else if(item.getItemId() == R.id.bottomAbout)
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentChange,mainActivityFragmentAbout).commit();
            }

            return true;
        }
    };

    private void checkLogin()
    {
        if(!loginPreferences.checkLogin())
        {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }

    private void checkTheme()
    {
        if(themePreferences.checkTheme())
        {
            String themeName = themePreferences.getTheme().getThemeName();

            if(themeName.equals("Terang"))
            {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            else if(themeName.equals("Gelap"))
            {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        }
    }
}