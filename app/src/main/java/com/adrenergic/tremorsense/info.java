package com.adrenergic.tremorsense;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;


public class info extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //Set up the webView
        WebView infoWebView = (WebView) findViewById(R.id.infoView);
        infoWebView.loadUrl("file:///android_asset/web/info_en.html");
    }
}
