package com.yory3r.e_learning.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.yory3r.e_learning.R;
import com.yory3r.e_learning.adapter.MathAdapter;
import com.yory3r.e_learning.databinding.ActivityMathBinding;
import com.yory3r.e_learning.fragment.MathActivityFragmentKalkulator;
import com.yory3r.e_learning.model.Math;
import com.yory3r.e_learning.model.MathDummy;

import java.util.ArrayList;

public class MathActivity extends AppCompatActivity
{
    private ActivityMathBinding activityMathBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activityMathBinding = DataBindingUtil.setContentView(this,R.layout.activity_math);
        activityMathBinding.setActivityMath(this);

        ArrayList<Math> listMath = new MathDummy().listMath;
        MathAdapter mathAdapter = new MathAdapter(listMath,this);

        activityMathBinding.rvMathItem.setAdapter(mathAdapter);
        activityMathBinding.rvMathItem.setLayoutManager(new LinearLayoutManager(this));

        MathActivityFragmentKalkulator mathActivityFragmentKalkulator = new MathActivityFragmentKalkulator();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentChangeMath,mathActivityFragmentKalkulator).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed()
    {
        finish();
        super.onBackPressed();
    }
}