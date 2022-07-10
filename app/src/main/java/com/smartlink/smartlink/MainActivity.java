package com.smartlink.smartlink;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.facebook.AccessToken;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    String frontendBaseURL = "https://main.dmxqa22ma9yp.amplifyapp.com";
    String backendAuthURL = "http://54.161.82.165:3000/api/v1/auth/facebook";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();

        // get access token from the facebook
        System.out.println(AccessToken.getCurrentAccessToken());
        String facebookAccessToken = Objects.requireNonNull(AccessToken.getCurrentAccessToken()).getToken();

        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(frontendBaseURL + "/login?token=" + facebookAccessToken);

        Intent loginActivityIntent = new Intent(this, LoginActivity.class);

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){

                 if (url.endsWith("/login")){
                    startActivity(loginActivityIntent);
                    view.stopLoading();
                }

                super.onPageStarted(view, url, favicon);
            }
        });
    }

}