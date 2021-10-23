package com.yory3r.e_learning.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.yory3r.e_learning.R;
import com.yory3r.e_learning.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment
{
    private FragmentHomeBinding fragmentHomeBinding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false);
        fragmentHomeBinding.setFragmentGallery(this);

        View view = fragmentHomeBinding.getRoot();



        return view;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        fragmentHomeBinding = null;
    }
}