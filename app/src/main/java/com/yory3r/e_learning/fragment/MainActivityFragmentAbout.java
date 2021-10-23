package com.yory3r.e_learning.fragment;

import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yory3r.e_learning.adapter.AboutAdapter;
import com.yory3r.e_learning.model.About;
import com.yory3r.e_learning.model.AboutDummy;
import com.yory3r.e_learning.R;
import com.yory3r.e_learning.databinding.FragmentMainActivityAboutBinding;
import java.util.ArrayList;

public class MainActivityFragmentAbout extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        FragmentMainActivityAboutBinding fragmentMainActivityAboutBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_main_activity_about,container,false);
        fragmentMainActivityAboutBinding.setFragmentMainActivityAbout(this);

        View view = fragmentMainActivityAboutBinding.getRoot();

        ArrayList<About> listAbout = new AboutDummy().listAbout;
        AboutAdapter aboutAdapter = new AboutAdapter(listAbout,view.getContext());

        fragmentMainActivityAboutBinding.rvAbout.setAdapter(aboutAdapter);
        fragmentMainActivityAboutBinding.rvAbout.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }
}