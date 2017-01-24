package com.example.ivan.muzikarss.activities;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ivan.muzikarss.R;

public class WebShowActivity extends AppCompatActivity {
    private WebView webView;
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_layout);

        link = getIntent().getStringExtra("LINK");

        webView = (WebView) findViewById(R.id.web_view);

        loadUrl(link);

    }

    /**
     * Method for loading web site based on provided url
     * @param url Url of the website to load
     */
    private void loadUrl(String url){

        final ProgressDialog progressDialog = ProgressDialog.show(this, getString(R.string.pd_title), getString(R.string.pd_description), true);
        progressDialog.setCancelable(true);


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setBuiltInZoomControls(true);

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                progressDialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressDialog.dismiss();
            }
        });

        webView.loadUrl(link);
    }
}
