package com.yory3r.e_learning.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.yory3r.e_learning.R;
import com.yory3r.e_learning.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashScreenBinding activitySplashScreenBinding = DataBindingUtil.setContentView(this,R.layout.activity_splash_screen);
        activitySplashScreenBinding.setActivitySplashScreen(this);




        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(SplashScreenActivity.this,FirstPageActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}