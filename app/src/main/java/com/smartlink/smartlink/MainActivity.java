package com.smartlink.smartlink;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.facebook.AccessToken;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    String baseURL = "https://main.dmxqa22ma9yp.amplifyapp.com";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();

        System.out.println(AccessToken.getCurrentAccessToken());
        String accessToken = Objects.requireNonNull(AccessToken.getCurrentAccessToken()).getToken();

        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(baseURL);

        CookieManager.getInstance().setCookie(baseURL, "token=" + accessToken + "; path=/");

        webView.setWebViewClient(new WebViewClient());
    }
}