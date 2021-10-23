package com.yory3r.e_learning.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yory3r.e_learning.R;
import com.yory3r.e_learning.databinding.FragmentFirstPageWelcomeBinding;

public class FirstPageFragmentWelcome extends Fragment
{
    private String title;
    private int page;

    public static FirstPageFragmentWelcome newInstance(int page, String title)
    {
        FirstPageFragmentWelcome firstPageFragmentWelcome = new FirstPageFragmentWelcome();
        Bundle bundle = new Bundle();
        bundle.putInt("Page",page);
        bundle.putString("Title",title);
        firstPageFragmentWelcome.setArguments(bundle);

        return firstPageFragmentWelcome;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("Page",0);
        title = getArguments().getString("Title");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        FragmentFirstPageWelcomeBinding fragmentFirstPageWelcomeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_first_page_welcome,container,false);
        fragmentFirstPageWelcomeBinding.setFragmentFirstPageWelcome(this);

        View view = fragmentFirstPageWelcomeBinding.getRoot();





        return view;

    }
}