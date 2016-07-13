package com.mobile.recplay.recplaymobile;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    private WebView recplayer;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recplayer = (WebView)findViewById(R.id.recplayer_web_view);
        WebSettings settings = recplayer.getSettings();

        settings.setJavaScriptEnabled(true);

        settings.setAllowFileAccess(true);


        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.ECLAIR) {
            try {
                Log.d(TAG, "Enabling HTML5-Features");
                Method m1 = WebSettings.class.getMethod("setDomStorageEnabled", new Class[]{Boolean.TYPE});
                m1.invoke(settings, Boolean.TRUE);

                Method m2 = WebSettings.class.getMethod("setDatabaseEnabled", new Class[]{Boolean.TYPE});
                m2.invoke(settings, Boolean.TRUE);

                Method m3 = WebSettings.class.getMethod("setDatabasePath", new Class[]{String.class});
                m3.invoke(settings, "/data/data/" + getPackageName() + "/databases/");

                Method m4 = WebSettings.class.getMethod("setAppCacheMaxSize", new Class[]{Long.TYPE});
                m4.invoke(settings, 1024*1024*8);

                Method m5 = WebSettings.class.getMethod("setAppCachePath", new Class[]{String.class});
                m5.invoke(settings, "/data/data/" + getPackageName() + "/cache/");

                Method m6 = WebSettings.class.getMethod("setAppCacheEnabled", new Class[]{Boolean.TYPE});
                m6.invoke(settings, Boolean.TRUE);

                Log.d(TAG, "Enabled HTML5-Features");
            }
            catch (NoSuchMethodException e) {
                Log.e(TAG, "Reflection fail", e);
            }
            catch (InvocationTargetException e) {
                Log.e(TAG, "Reflection fail", e);
            }
            catch (IllegalAccessException e) {
                Log.e(TAG, "Reflection fail", e);
            }
        }
    }

    @Override
    protected void onResume() {
        String file = "file:android_asset/recplayer.html";
        recplayer.loadUrl(file);

        super.onResume();
    }
}
