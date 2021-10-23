package com.yory3r.e_learning.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yory3r.e_learning.R;
import com.yory3r.e_learning.activity.LordsMobileActivity;
import com.yory3r.e_learning.activity.MapsActivity;
import com.yory3r.e_learning.activity.MathActivity;
import com.yory3r.e_learning.activity.WebActivity;
import com.yory3r.e_learning.databinding.FragmentMainActivityHomeBinding;

public class MainActivityFragmentHome extends Fragment
{
    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        FragmentMainActivityHomeBinding fragmentMainActivityHomeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_main_activity_home,container,false);
        fragmentMainActivityHomeBinding.setFragmentMainActivityHome(this);

        View view = fragmentMainActivityHomeBinding.getRoot();


        return view;
    }

    public View.OnClickListener btnClicked = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            if(view.getId() == R.id.ibtnMath)
            {
                intent = new Intent(view.getContext(), MathActivity.class);
                startActivity(intent);
            }
            else if(view.getId() == R.id.ibtnMaps)
            {
                intent = new Intent(view.getContext(), MapsActivity.class);
                startActivity(intent);
            }
            else if(view.getId() == R.id.ibtnWeb)
            {
                intent = new Intent(view.getContext(), WebActivity.class);
                startActivity(intent);
            }
            else if(view.getId() == R.id.ibtnLordMobile)
            {
                intent = new Intent(view.getContext(), LordsMobileActivity.class);
                startActivity(intent);
            }
        }
    };
}