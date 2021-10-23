package com.yory3r.e_learning.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yory3r.e_learning.R;
import com.yory3r.e_learning.database.DatabaseRegister;
import com.yory3r.e_learning.databinding.ActivityLoginBinding;
import com.yory3r.e_learning.model.Register;
import com.yory3r.e_learning.preferences.FirstPagePreferences;
import com.yory3r.e_learning.preferences.LoginPreferences;
import com.yory3r.e_learning.preferences.ThemePreferences;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity
{
    private FirstPagePreferences firstPagePreferences;
    private LoginPreferences loginPreferences;
    private ThemePreferences themePreferences;

    private ActivityLoginBinding activityLoginBinding;
    private Intent intent;
    private int extraID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.setActivityLogin(this);

        firstPagePreferences = new FirstPagePreferences(LoginActivity.this);
        loginPreferences = new LoginPreferences(LoginActivity.this);
        themePreferences = new ThemePreferences(LoginActivity.this);

        checkFirstOpen();
        checkLogin();
        checkTheme();




    }

    public View.OnClickListener btnClicked = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            if(view.getId() == R.id.btnLogin)
            {
                String username = activityLoginBinding.etUsernameLogin.getText().toString();
                String password = activityLoginBinding.etPasswordLogin.getText().toString();

                List<Register> register = new ArrayList<>();

                register = DatabaseRegister.getInstance(view.getContext()).getDatabase().registerDao().getAll();

                if(username.isEmpty() || password.isEmpty())
                {
                    if(username.isEmpty())
                    {
                        activityLoginBinding.etUsernameLogin.setError("Username Kosong !");
                    }

                    if(password.isEmpty())
                    {
                        activityLoginBinding.etPasswordLogin.setError("Password Kosong !");
                    }
                }
                else
                {
                    if(register.size() == 0)
                    {
                        Toast.makeText(LoginActivity.this, "Data Masih Kosong !", Toast.LENGTH_SHORT).show();
                        Toast.makeText(LoginActivity.this, "Silahkan Register Dulu !", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        for(int a = 0 ; a < register.size() ; a++)
                        {
                            if(register.get(a).getUsername().equals(username) && register.get(a).getPassword().equals(password))
                            {
                                loginPreferences.setLogin(username,password);
                                checkLogin();
                                break;
                            }
                            else if(register.get(a).getUsername().equals(username) || register.get(a).getPassword().equals(password))
                            {
                                Toast.makeText(LoginActivity.this, "Username Atau Password Salah !", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            else
                            {
                                if(a == register.size() - 1)
                                {
                                    Toast.makeText(LoginActivity.this, "User Tidak Ada !", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
            }
            else if(view.getId() == R.id.btnRegister)
            {
                firstPagePreferences.logoutFirstOpen();
                intent = new Intent(LoginActivity.this,FirstPageActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    private void checkFirstOpen()
    {
        if(!firstPagePreferences.checkFirstOpen())
        {
            intent = new Intent(LoginActivity.this, FirstPageActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void checkLogin()
    {
        if(loginPreferences.checkLogin())
        {
            String username = loginPreferences.getUserLogin().getUsername();
            String password = loginPreferences.getUserLogin().getPassword();

            List<Register> register = new ArrayList<>();
            register = DatabaseRegister.getInstance(getApplicationContext()).getDatabase().registerDao().getAll();

            for(int a = 0 ; a < register.size() ; a++)
            {
                if(register.get(a).getUsername().equals(username) && register.get(a).getPassword().equals(password))
                {
                    extraID = register.get(a).getId();

                    intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("IndexID",extraID);

                    startActivity(intent);
                    finish();
                    break;
                }
            }
        }
    }

    private void checkTheme()
    {
        if(themePreferences.checkTheme())
        {
            String themeName = themePreferences.getTheme().getThemeName();

            if(themeName.equals("Terang"))
            {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            else if(themeName.equals("Gelap"))
            {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        }
    }
}
