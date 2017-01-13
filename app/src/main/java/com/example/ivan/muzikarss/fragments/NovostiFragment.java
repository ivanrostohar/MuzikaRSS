package com.example.ivan.muzikarss.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.ivan.muzikarss.R;
import com.example.ivan.muzikarss.activities.MainActivity;
import com.example.ivan.muzikarss.models.NovostiRssItem;
import com.example.ivan.muzikarss.utilities.RssReader;

import java.util.List;

public class NovostiFragment extends Fragment {
    private static final String NOVOSTI_AKTUALNO_URL = "http://www.muzika.hr/rss/rubrika/7/aktualno.aspx";
    private static final String NOVOSTI_SCENA_URL = "http://www.muzika.hr/rss/rubrika/34/scena.aspx";
    private static final String NOVOSTI_RECENZIJE_URL = "http://www.muzika.hr/rss/rubrika/9/recenzije.aspx";

    private Toolbar toolbar;
    private RssReader rssReader;

    public NovostiFragment() {
    }


    public static NovostiFragment newInstance() {
        NovostiFragment fragment = new NovostiFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_novosti, container, false);

        toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setCustomToolbar(toolbar, getString(R.string.app_name));







        return view;
    }

    /**
     * AsyncTask for downloading rss feed base on url that you provide od execute method
     */
    private class GetRssDataTask extends AsyncTask<String, Void, List<NovostiRssItem>>{

        @Override
        protected List<NovostiRssItem> doInBackground(String... strings) {

            try {
                rssReader = new RssReader(strings[0]);
                rssReader.getItems();
                for (int i = 0; i<rssReader.getItems().size(); i++){
                    String title = rssReader.getItems().get(i).getTitle();
                    String picture  =rssReader.getItems().get(i).getPicture();
                    Glide.with(NovostiFragment.this).load(picture).override(300,300);
                    Log.v("TITLE", title);
                    Log.v("DESCRIPTION", rssReader.getItems().get(i).getDescription());
                    Log.v("PICTURE", picture);
                    Log.v("LINK", rssReader.getItems().get(i).getLink());
                    Log.v("DATE", rssReader.getItems().get(i).getPubDate());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

    }











    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_novosti, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_novosti_aktualno:
                new GetRssDataTask().execute(NOVOSTI_AKTUALNO_URL);break;
            case R.id.menu_novosti_recenzija:
                new GetRssDataTask().execute(NOVOSTI_RECENZIJE_URL);break;
            case R.id.menu_novosti_scena:
                new GetRssDataTask().execute(NOVOSTI_SCENA_URL);break;
        }

        return super.onOptionsItemSelected(item);
    }


}
