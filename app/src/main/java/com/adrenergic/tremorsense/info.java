package com.adrenergic.tremorsense;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class info extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //Set up the webView
        WebView infoWebView = (WebView) findViewById(R.id.infoView);
        WebSettings webSettings = infoWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        infoWebView.loadUrl("file:///android_asset/web/info.html");
    }
}
