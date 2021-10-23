package com.yory3r.e_learning.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.yory3r.e_learning.fragment.FirstPageFragmentRegister;
import com.yory3r.e_learning.fragment.FirstPageFragmentTheme;
import com.yory3r.e_learning.fragment.FirstPageFragmentWelcome;

public class FirstPageAdapter extends FragmentPagerAdapter
{
    private static int item = 3;

    public FirstPageAdapter(@NonNull FragmentManager fm)
    {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        if(position == 0)
        {
            return FirstPageFragmentWelcome.newInstance(0,"Page 1");
        }
        else if(position == 1)
        {
            return FirstPageFragmentTheme.newInstance(1,"Page 2");
        }
        else
        {
            return FirstPageFragmentRegister.newInstance(2, "Page 3");
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
            return "Selamat Datang";
        }
        else if(position == 1)
        {
            return "Pilih Tema";
        }
        else
        {
            return "Registrasi";
        }
    }
}
