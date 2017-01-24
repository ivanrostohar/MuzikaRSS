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


public class KalendarFragment extends Fragment {
    private static final String KALENDAR_HRVATSKA_URL = "http://www.muzika.hr/rss/kalendar/hrvatska.aspx";
    private static final String KALENDAR_INOZEMSTVO_URL = "http://www.muzika.hr/rss/kalendar/inozemstvo.aspx";
    private static final String KALENDAR_SVE_URL = "http://www.muzika.hr/rss/kalendar/sve.aspx";
    private static final String TAG_KALENDAR = "KALENDAR_HRVATSKA_ADAPTER";

    private Toolbar toolbar;
    private KalendarHrvatskaRssReader rssReader;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<KalendarHrvatskaModel> emptyKHArray;
    private KalendarHrvatskaAdapter khAdapter;

    public KalendarFragment() {
    }


    public static KalendarFragment newInstance() {
        KalendarFragment fragment = new KalendarFragment();
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

        emptyKHArray = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .color(R.color.colorDivider)
                .build());

        ((MainActivity) getActivity()).setCustomLayout(recyclerView, emptyKHArray, TAG_KALENDAR, null, khAdapter);


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

            ((MainActivity) getActivity()).setCustomLayout(recyclerView, kalendarHrvatskaModels, TAG_KALENDAR, null, khAdapter);

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_kalendar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_kalendar_hrvatska:
                new getRssData().execute(KALENDAR_HRVATSKA_URL);
                toolbar.setTitle(item.getTitle());
                break;
            case R.id.menu_kalendar_inozemstvo:
                new getRssData().execute(KALENDAR_INOZEMSTVO_URL);
                toolbar.setTitle(item.getTitle());
                break;
            case R.id.menu_kalendar_sve:
                new getRssData().execute(KALENDAR_SVE_URL);
                toolbar.setTitle(item.getTitle());
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
