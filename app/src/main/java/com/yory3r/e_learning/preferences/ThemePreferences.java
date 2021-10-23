package com.yory3r.e_learning.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import com.yory3r.e_learning.model.Theme;

public class ThemePreferences
{
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_THEME = "theme";

    public ThemePreferences(Context context)
    {
        this.context = context;

        sharedPreferences = context.getSharedPreferences("themePreferences",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setTheme(String theme)
    {
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(KEY_THEME,theme);
        editor.commit();
    }

    public Theme getTheme()
    {
        String themeName;

        themeName = sharedPreferences.getString(KEY_THEME,null);

        return new Theme(themeName);
    }

    public boolean checkTheme()
    {
        return sharedPreferences.getBoolean(IS_LOGIN,false);
    }

    public void defaultTheme()
    {
        editor.clear();
        editor.commit();
    }
}