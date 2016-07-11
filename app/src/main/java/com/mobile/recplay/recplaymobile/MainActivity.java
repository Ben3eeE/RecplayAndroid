package com.mobile.recplay.recplaymobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {
    private WebView recplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recplayer = (WebView)findViewById(R.id.recplayer_web_view);
        WebSettings settings = recplayer.getSettings();

        settings.setJavaScriptEnabled(true);
        String file = "file:android_asset/recplayer.html";
        recplayer.loadUrl(file);
    }
}
