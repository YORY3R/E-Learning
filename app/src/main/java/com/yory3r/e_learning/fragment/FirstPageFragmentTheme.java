package com.yory3r.e_learning.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yory3r.e_learning.R;
import com.yory3r.e_learning.databinding.FragmentFirstPageThemeBinding;
import com.yory3r.e_learning.preferences.ThemePreferences;

public class FirstPageFragmentTheme extends Fragment
{
    private String title;
    private int page;
    private ThemePreferences themePreferences;
    private FragmentFirstPageThemeBinding fragmentFirstPageThemeBinding;

    public static FirstPageFragmentTheme newInstance(int page, String title)
    {
        FirstPageFragmentTheme firstPageFragmentTheme = new FirstPageFragmentTheme();
        Bundle bundle = new Bundle();
        bundle.putInt("Page",page);
        bundle.putString("Title",title);
        firstPageFragmentTheme.setArguments(bundle);

        return firstPageFragmentTheme;
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
        fragmentFirstPageThemeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_first_page_theme,container,false);
        fragmentFirstPageThemeBinding.setFragmentFirstPageTheme(this);

        View view = fragmentFirstPageThemeBinding.getRoot();

        themePreferences = new ThemePreferences(view.getContext());
        checkTheme();

        return view;
    }

    public View.OnClickListener btnClicked = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            if(view.getId() == R.id.rbTerang)
            {
                String themeName = "Terang";

                themePreferences.setTheme(themeName);
            }
            else if(view.getId() == R.id.rbGelap)
            {
                String themeName = "Gelap";

                themePreferences.setTheme(themeName);
            }

            checkTheme();
        }
    };

    private void checkTheme()
    {
        if(themePreferences.checkTheme())
        {
            String themeName = themePreferences.getTheme().getThemeName();

            if(themeName.equals("Terang"))
            {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                fragmentFirstPageThemeBinding.rbTerang.setChecked(true);
                fragmentFirstPageThemeBinding.rbGelap.setChecked(false);
            }
            else if(themeName.equals("Gelap"))
            {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                fragmentFirstPageThemeBinding.rbTerang.setChecked(false);
                fragmentFirstPageThemeBinding.rbGelap.setChecked(true);
            }
        }
    }
}