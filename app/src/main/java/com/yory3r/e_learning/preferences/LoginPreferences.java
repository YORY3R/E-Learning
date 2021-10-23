package com.yory3r.e_learning.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import com.yory3r.e_learning.model.Login;

public class LoginPreferences
{
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    public LoginPreferences(Context context)
    {
        this.context = context;

        sharedPreferences = context.getSharedPreferences("loginPreferences",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLogin(String username, String password)
    {
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_PASSWORD,password);
        editor.commit();
    }

    public Login getUserLogin()
    {
        String username;
        String password;

        username = sharedPreferences.getString(KEY_USERNAME,null);
        password = sharedPreferences.getString(KEY_PASSWORD,null);

        return new Login(username,password);
    }

    public boolean checkLogin()
    {
        return sharedPreferences.getBoolean(IS_LOGIN,false);
    }

    public void Logout()
    {
        editor.clear();
        editor.commit();
    }
}