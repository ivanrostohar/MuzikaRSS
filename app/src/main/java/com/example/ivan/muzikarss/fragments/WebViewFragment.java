package com.example.ivan.muzikarss.fragments;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ivan.muzikarss.R;
import com.example.ivan.muzikarss.activities.MainActivity;

import java.util.EmptyStackException;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewFragment extends Fragment {
    private WebView webView;
    private String link;


    public WebViewFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.web_view_layout, container, false);

        webView = (WebView)view.findViewById(R.id.web_view);

        loadUrl(link);

        return view;
    }

    /**
     * Method for loading web site based on provided url
     * @param url Url of the website to load
     */
    private void loadUrl(String url){

        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), getString(R.string.pd_title), getString(R.string.pd_description), true);
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
