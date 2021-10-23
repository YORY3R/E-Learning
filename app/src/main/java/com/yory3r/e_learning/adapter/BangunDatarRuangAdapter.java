package com.yory3r.e_learning.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.yory3r.e_learning.fragment.BangunDatarRuangFragmentPersegi;
import com.yory3r.e_learning.fragment.BangunDatarRuangFragmentPersegiPanjang;
import com.yory3r.e_learning.fragment.BangunDatarRuangFragmentSegitiga;
import com.yory3r.e_learning.fragment.FirstPageFragmentRegister;
import com.yory3r.e_learning.fragment.FirstPageFragmentTheme;
import com.yory3r.e_learning.fragment.FirstPageFragmentWelcome;

public class BangunDatarRuangAdapter extends FragmentPagerAdapter
{
    private static int item = 3;

    public BangunDatarRuangAdapter(@NonNull FragmentManager fm)
    {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        if(position == 0)
        {
            return BangunDatarRuangFragmentPersegi.newInstance(0,"Persegi");
        }
        else if(position == 1)
        {
            return BangunDatarRuangFragmentPersegiPanjang.newInstance(1,"Persegi Panjang");
        }
        else
        {
            return BangunDatarRuangFragmentSegitiga.newInstance(2, "Segitiga");
        }
    }

    @Override
    public int getCount()
    {
        return item;
    }

    public CharSequence getPageTitle(int position)
    {
        if(position == 0)
        {
            return "Persegi";
        }
        else if(position == 1)
        {
            return "Persegi Panjang";
        }
        else
        {
            return "Segitiga";
        }
    }
}
