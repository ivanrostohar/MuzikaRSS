package com.example.ivan.muzikarss.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.ivan.muzikarss.R;
import com.example.ivan.muzikarss.activities.MainActivity;
import com.example.ivan.muzikarss.adapters.KalendarHrvatskaAdapter;
import com.example.ivan.muzikarss.models.KalendarHrvatskaModel;
import com.example.ivan.muzikarss.utilities.KalendarHrvatskaRssReader;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;


public class HrvatskaFragment extends Fragment {
    private static final String HRVATSKA_KONCERTI_URL = "http://www.muzika.hr/rss/kalendar/hrvatska/1/koncerti.aspx";
    private static final String HRVATSKA_PARTY_URL = "http://www.muzika.hr/rss/kalendar/hrvatska/2/party.aspx";
    private static final String HRVATSKA_OKOLO_URL = "http://www.muzika.hr/rss/kalendar/hrvatska/5/okolo.aspx";
    private static final String HRVATSKA_IZLASCI_URL = "http://www.muzika.hr/rss/kalendar/hrvatska/3/izlasci.aspx";
    private static final String TAG_HRVATSKA = "KALENDAR_HRVATSKA_ADAPTER";

    private Toolbar toolbar;
    private KalendarHrvatskaRssReader rssReader;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<KalendarHrvatskaModel> emptyKHItems;
    private KalendarHrvatskaAdapter khAdapter;

    public HrvatskaFragment() {
    }


    public static HrvatskaFragment newInstance() {
        HrvatskaFragment fragment = new HrvatskaFragment();
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

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((MainActivity) getActivity()).setCustomToolbar(toolbar, getString(R.string.app_name));

        emptyKHItems = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .color(R.color.colorDivider)
                .build());

        ((MainActivity) getActivity()).setCustomLayout(recyclerView, emptyKHItems, TAG_HRVATSKA, null, khAdapter);


        return view;
    }


    private class getRssData extends AsyncTask<String, Void, ArrayList<KalendarHrvatskaModel>> {

        @Override
        protected ArrayList<KalendarHrvatskaModel> doInBackground(String... strings) {
            ArrayList<KalendarHrvatskaModel> kaArrayItems = null;

            try {
                kaArrayItems = new ArrayList<>();
                rssReader = new KalendarHrvatskaRssReader(strings[0]);
                kaArrayItems = rssReader.getItems();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return kaArrayItems;
        }

        @Override
        protected void onPostExecute(ArrayList<KalendarHrvatskaModel> kalendarHrvatskaModels) {

            ((MainActivity) getActivity()).setCustomLayout(recyclerView, kalendarHrvatskaModels, TAG_HRVATSKA, null, khAdapter);


            super.onPostExecute(kalendarHrvatskaModels);
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_hrvatska, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_hrvatska_koncerti:
                new getRssData().execute(HRVATSKA_KONCERTI_URL);
                toolbar.setTitle(item.getTitle());
                break;
            case R.id.menu_hrvatska_partyi:
                new getRssData().execute(HRVATSKA_PARTY_URL);
                toolbar.setTitle(item.getTitle());
                break;
            case R.id.menu_hrvatska_okolo:
                new getRssData().execute(HRVATSKA_OKOLO_URL);
                toolbar.setTitle(item.getTitle());
                break;
            case R.id.menu_hrvatska_izlasci:
                new getRssData().execute(HRVATSKA_IZLASCI_URL);
                toolbar.setTitle(item.getTitle());
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
