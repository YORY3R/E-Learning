package com.yory3r.e_learning.fragment;

import static com.yory3r.e_learning.Application.App.CHANNEL_1_ID;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.ColumnInfo;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.yory3r.e_learning.activity.FirstPageActivity;
import com.yory3r.e_learning.activity.LoginActivity;
import com.yory3r.e_learning.activity.MainActivity;
import com.yory3r.e_learning.adapter.RegisterAdapter;
import com.yory3r.e_learning.database.DatabaseRegister;
import com.yory3r.e_learning.databinding.FragmentFirstPageRegisterBinding;
import com.yory3r.e_learning.model.Register;
import com.yory3r.e_learning.preferences.FirstPagePreferences;
import com.yory3r.e_learning.R;
import com.yory3r.e_learning.receiver.NotificationReceiver;

import java.util.ArrayList;
import java.util.List;

public class FirstPageFragmentRegister extends Fragment
{
    private String title;
    private int page;
    private FirstPagePreferences firstPagePreferences;
    private EditText etNamaLengkap;
    private EditText etNPM;
    private EditText etNomorTelepon;
    private EditText etAlamat;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etUlangiPassword;
    private String namaLengkap ;
    private String npm;
    private String nomorTelepon;
    private String alamat;
    private String username;
    private String password;
    private String ulangiPassword;
    private FragmentFirstPageRegisterBinding fragmentFirstPageRegisterBinding;


    public static FirstPageFragmentRegister newInstance(int page, String title)
    {
        FirstPageFragmentRegister firstPageFragmentRegister = new FirstPageFragmentRegister();
        Bundle bundle = new Bundle();
        bundle.putInt("Page",page);
        bundle.putString("Title",title);
        firstPageFragmentRegister.setArguments(bundle);

        return firstPageFragmentRegister;
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
        fragmentFirstPageRegisterBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_first_page_register,container,false);
        fragmentFirstPageRegisterBinding.setFragmentFirstPageRegister(this);

        View view = fragmentFirstPageRegisterBinding.getRoot();

        firstPagePreferences = new FirstPagePreferences(view.getContext());


        etNamaLengkap = fragmentFirstPageRegisterBinding.etNamaLengkap;
        etNPM = fragmentFirstPageRegisterBinding.etNPM;
        etNomorTelepon = fragmentFirstPageRegisterBinding.etNomorTelepon;
        etAlamat = fragmentFirstPageRegisterBinding.etAlamat;
        etUsername = fragmentFirstPageRegisterBinding.etUsername;
        etPassword = fragmentFirstPageRegisterBinding.etPassword;
        etUlangiPassword = fragmentFirstPageRegisterBinding.etUlangiPassword;





        return view;
    }

    public View.OnClickListener btnClicked = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            namaLengkap = etNamaLengkap.getText().toString();
            npm = etNPM.getText().toString();
            nomorTelepon = etNomorTelepon.getText().toString();
            alamat = etAlamat.getText().toString();
            username = etUsername.getText().toString();
            password = etPassword.getText().toString();
            ulangiPassword = etUlangiPassword.getText().toString();

            if(view.getId() == R.id.btnDaftar)
            {
                if(namaLengkap.isEmpty() || npm.isEmpty() || nomorTelepon.isEmpty() || alamat.isEmpty() || username.isEmpty() || password.isEmpty() || ulangiPassword.isEmpty())
                {
                    if(namaLengkap.isEmpty())
                    {
                        etNamaLengkap.setError("Nama Kosong !");
                    }

                    if(npm.isEmpty())
                    {
                        etNPM.setError("NPM Kosong !");
                    }

                    if(nomorTelepon.isEmpty())
                    {
                        etNomorTelepon.setError("Telepon Kosong !");
                    }

                    if(alamat.isEmpty())
                    {
                        etAlamat.setError("Alamat Kosong !");
                    }

                    if(username.isEmpty())
                    {
                        etUsername.setError("Username Kosong !");
                    }

                    if(password.isEmpty())
                    {
                        etPassword.setError("Password Kosong !");
                    }

                    if(ulangiPassword.isEmpty())
                    {
                        etUlangiPassword.setError("Password Kosong !");
                    }
                }
                else if(!password.equals(ulangiPassword))
                {
                    etUlangiPassword.setError("Password Tidak Sama !");
                }
                else
                {
                    addRegister();
                    pushNotification();
                    SystemClock.sleep(5000);
                    gotoLoginActivity(view);
                }
            }
            else if(view.getId() == R.id.btnLogin)
            {
                gotoLoginActivity(view);
            }
        }
    };

    private void addRegister()
    {
        class AddRegister extends AsyncTask<Void, Void, Void>
        {
            @Override
            protected Void doInBackground(Void... voids)
            {
                Register register = new Register();
                register.setNamaLengkap(namaLengkap);
                register.setNpm(npm);
                register.setNomorTelepon(nomorTelepon);
                register.setAlamat(alamat);
                register.setUsername(username);
                register.setPassword(password);

                DatabaseRegister.getInstance(getContext()).getDatabase().registerDao().insertRegister(register);

                return null;
            }

            @Override
            protected void onPostExecute(Void unused)
            {
                super.onPostExecute(unused);

                Toast.makeText(getContext(), "Register Berhasil", Toast.LENGTH_SHORT).show();
            }
        }

        AddRegister addRegister = new AddRegister();
        addRegister.execute();
    }

    private void gotoLoginActivity(View view)
    {
        firstPagePreferences.setFirstOpen("true");
        Intent intent = new Intent(view.getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void pushNotification()
    {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),R.drawable.ic_baseline_emoji_emotions_24);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getContext(),CHANNEL_1_ID);
        notification.setSmallIcon(R.drawable.ic_baseline_emoji_emotions_24)
                .setLargeIcon(largeIcon)
                .setContentTitle("Status")
                .setContentText("Sedang Upload Data")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.RED)
                .setAutoCancel(true)
                .setOnlyAlertOnce(false);

        new Thread
        (
            new Runnable()
            {
                @Override
                public void run()
                {
                    int a;
                    int max = 100;

                    for(a = 0 ; a <= max ; a+=7)
                    {
                        notification.setProgress(max,a,false);

                        try
                        {
                            Thread.sleep(200);
                        }
                        catch(InterruptedException e)
                        {
                            Log.d("TAG", "Sleep Failure");
                        }

                        notificationManagerCompat.notify(1,notification.build());
                    }

                    for(a = 0 ; a < max ; a+=7)
                    {
                        notification.setProgress(max,a,true);

                        try
                        {
                            Thread.sleep(200);
                        }
                        catch(InterruptedException e)
                        {
                            Log.d("TAG", "Sleep Failure");
                        }

                        notificationManagerCompat.notify(1,notification.build());
                    }

                    notification.setContentText("Upload Berhasil, Silahkan Login")
                            .setProgress(max,max,false);

                    notificationManagerCompat.notify(1,notification.build());

                    SystemClock.sleep(2000);

                    notification.setProgress(0,0,false);

                    notificationManagerCompat.notify(1,notification.build());
                }
            }
        ).start();
    }
}
