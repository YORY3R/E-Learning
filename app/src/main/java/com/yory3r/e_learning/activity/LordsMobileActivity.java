package com.yory3r.e_learning.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import com.yory3r.e_learning.R;
import com.yory3r.e_learning.databinding.ActivityLordsMobileBinding;

public class LordsMobileActivity extends AppCompatActivity
{
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityLordsMobileBinding activityLordsMobileBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        activityLordsMobileBinding = DataBindingUtil.setContentView(this,R.layout.activity_lords_mobile);
        activityLordsMobileBinding.setActivityLordsMobile(this);

        setSupportActionBar(activityLordsMobileBinding.appBarLordsMobile.toolbar);

        activityLordsMobileBinding.appBarLordsMobile.fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(LordsMobileActivity.this, "Selamat Belajar", Toast.LENGTH_SHORT).show();
            }
        });

        activityLordsMobileBinding.appBarLordsMobile.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem)
            {
                finish();
                return true;
            }
        });

        DrawerLayout drawer = activityLordsMobileBinding.drawerLayout;
        NavigationView navigationView = activityLordsMobileBinding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_lords_mobile);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.lords_mobile, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_lords_mobile);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }
}