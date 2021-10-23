package com.yory3r.e_learning.activity;

import static com.yory3r.e_learning.Application.App.CHANNEL_1_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.widget.Toast;

import com.yory3r.e_learning.R;
import com.yory3r.e_learning.adapter.FirstPageAdapter;
import com.yory3r.e_learning.preferences.FirstPagePreferences;
import com.yory3r.e_learning.databinding.ActivityFirstPageBinding;
import com.yory3r.e_learning.preferences.ThemePreferences;
import com.yory3r.e_learning.receiver.NotificationReceiver;

import java.util.Calendar;

public class FirstPageActivity extends AppCompatActivity
{

    private FirstPageAdapter firstPageAdapter;
    private ActivityFirstPageBinding activityFirstPageBinding;
    private FirstPagePreferences firstPagePreferences;
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFirstPageBinding = DataBindingUtil.setContentView(this, R.layout.activity_first_page);
        activityFirstPageBinding.setActivityFirstPage(this);

        firstPagePreferences = new FirstPagePreferences(this);

        notificationManagerCompat = NotificationManagerCompat.from(this);

        checkFirstOpen();


        firstPageAdapter = new FirstPageAdapter(getSupportFragmentManager());

        activityFirstPageBinding.viewPager.setAdapter(firstPageAdapter);

        activityFirstPageBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
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

    }

    private void checkFirstOpen()
    {
        if(firstPagePreferences.checkFirstOpen())
        {
            startActivity(new Intent(FirstPageActivity.this, LoginActivity.class));
            finish();
        }
        else
        {
            String title = null;
            String message;

            Calendar calendar = Calendar.getInstance();
            int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);

            if(timeOfDay >= 0 && timeOfDay < 12)
            {
                title = "Hallo, Selamat Pagi";
            }
            else if(timeOfDay >= 12 && timeOfDay < 16)
            {
                title = "Hallo, Selamat Siang";
            }
            else if(timeOfDay >= 16 && timeOfDay < 21)
            {
                title = "Hallo, Selamat Sore";
            }
            else if(timeOfDay >= 21 && timeOfDay < 24)
            {
                title = "Hallo, Selamat Malam";
            }

            message = "Jam : " + String.valueOf(timeOfDay);

            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),R.drawable.ic_baseline_emoji_emotions_24);

            NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();


            bigTextStyle.bigText("Selamat datang dan selamat bergabung di aplikasi kami");
            bigTextStyle.setSummaryText("Welcome");


            Intent activityIntent = new Intent(this, FirstPageActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0,activityIntent,0);

            Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
            broadcastIntent.putExtra("toastMessage","Terima Kasih :D");
            PendingIntent actionIntent = PendingIntent.getBroadcast(this,0,broadcastIntent,PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder notification = new NotificationCompat.Builder(this,CHANNEL_1_ID);
            notification.setSmallIcon(R.drawable.ic_baseline_emoji_emotions_24)
                    .setLargeIcon(largeIcon)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setColor(Color.BLUE)
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true)
                    .setOnlyAlertOnce(true)
                    .setStyle(bigTextStyle)
                    .addAction(R.mipmap.ic_launcher,"Suka",actionIntent);

            notificationManagerCompat.notify(1,notification.build());
        }
    }



}