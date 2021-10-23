package com.yory3r.e_learning.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.yory3r.e_learning.model.FirstPage;

public class FirstPagePreferences
{
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_FIRSTOPEN = "firstOpen";

    public FirstPagePreferences(Context context)
    {
        this.context = context;

        sharedPreferences = context.getSharedPreferences("firstPagePreferences",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setFirstOpen(String isOpen)
    {
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(KEY_FIRSTOPEN,isOpen);
        editor.commit();
    }


    public boolean checkFirstOpen()
    {
        return sharedPreferences.getBoolean(IS_LOGIN,false);
    }

    public void logoutFirstOpen()
    {
        editor.clear();
        editor.commit();
    }
}
