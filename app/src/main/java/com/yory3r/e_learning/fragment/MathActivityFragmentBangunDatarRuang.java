package com.yory3r.e_learning.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yory3r.e_learning.R;
import com.yory3r.e_learning.adapter.BangunDatarRuangAdapter;
import com.yory3r.e_learning.adapter.FirstPageAdapter;
import com.yory3r.e_learning.databinding.FragmentMathActivityBangunDatarRuangBinding;

public class MathActivityFragmentBangunDatarRuang extends Fragment
{
    private FragmentMathActivityBangunDatarRuangBinding fragmentMathActivityBangunDatarRuangBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        fragmentMathActivityBangunDatarRuangBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_math_activity_bangun_datar_ruang,container,false);
        fragmentMathActivityBangunDatarRuangBinding.setFragmentMathActivityBangunDatarRuang(this);

        View view = fragmentMathActivityBangunDatarRuangBinding.getRoot();


        BangunDatarRuangAdapter bangunDatarRuangAdapter = new BangunDatarRuangAdapter(getActivity().getSupportFragmentManager());
        fragmentMathActivityBangunDatarRuangBinding.viewPagerBangunDatarRuang.setAdapter(bangunDatarRuangAdapter);

        fragmentMathActivityBangunDatarRuangBinding.viewPagerBangunDatarRuang.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {

            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });





        return view;
    }
}