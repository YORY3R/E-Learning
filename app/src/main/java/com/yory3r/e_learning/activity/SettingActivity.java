package com.yory3r.e_learning.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import com.yory3r.e_learning.R;
import com.yory3r.e_learning.databinding.ActivitySettingBinding;
import com.yory3r.e_learning.fragment.FirstPageFragmentTheme;

public class SettingActivity extends AppCompatActivity
{
    private ActivitySettingBinding activitySettingBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activitySettingBinding = DataBindingUtil.setContentView(this,R.layout.activity_setting);
        activitySettingBinding.setActivitySetting(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentChangeSetting,FirstPageFragmentTheme.newInstance(1,"Page 2")).commit();
    }

    public View.OnClickListener btnClicked = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            if(view.getId() == R.id.btnBack)
            {
                finish();
            }
        }
    };
}