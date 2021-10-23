package com.yory3r.e_learning.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.yory3r.e_learning.R;
import com.yory3r.e_learning.databinding.ActivityWebBinding;

public class WebActivity extends AppCompatActivity
{
    private ActivityWebBinding activityWebBinding;
    private String homeURL = "https://www.google.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activityWebBinding = DataBindingUtil.setContentView(this,R.layout.activity_web);
        activityWebBinding.setActivityWeb(this);

        activityWebBinding.wvWeb.getSettings().setJavaScriptEnabled(true);
        activityWebBinding.wvWeb.loadUrl(homeURL);

        activityWebBinding.wvWeb.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url)
            {
                activityWebBinding.etUrlWeb.setText(url);
                return false;
            }

            @Override
            public void onPageFinished(WebView webView, String url)
            {
                super.onPageFinished(webView, url);
            }
        });
    }

    public View.OnClickListener btnCLicked = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            if(view.getId() == R.id.btnHomeWeb)
            {
                activityWebBinding.wvWeb.loadUrl(homeURL);
            }
            else if(view.getId() == R.id.btnSearchWeb)
            {
                activityWebBinding.wvWeb.loadUrl(activityWebBinding.etUrlWeb.getText().toString().trim());
            }
        }
    };

    @Override
    public void onBackPressed()
    {
        if(activityWebBinding.wvWeb.getUrl().equals(homeURL))
        {
            super.onBackPressed();
        }
        else
        {
            if(activityWebBinding.wvWeb.canGoBack())
            {
                activityWebBinding.wvWeb.goBack();
            }
            else
            {
                super.onBackPressed();
            }
        }
    }
}