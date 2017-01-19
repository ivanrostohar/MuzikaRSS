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
    private Toolbar toolbar;
    MainActivity mainActivity;
    private TabLayout tabLayout;


    public WebViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.web_view_layout, container, false);
        link = getArguments().getString("LINK");

//        tabLayout = (TabLayout)getActivity().findViewById(R.id.tabs);
        webView = (WebView)view.findViewById(R.id.web_view);

//        tabLayout.setVisibility(View.GONE);


        loadUrl(link);

        return view;
    }

    private void loadUrl(String url){

        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "Please wait!", "Please wait for the page to load", true);
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
