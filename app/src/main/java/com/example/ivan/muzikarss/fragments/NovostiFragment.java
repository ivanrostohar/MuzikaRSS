package com.example.ivan.muzikarss.fragments;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
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
import com.example.ivan.muzikarss.adapters.NovostiAdapter;
import com.example.ivan.muzikarss.models.NovostiRssItem;
import com.example.ivan.muzikarss.utilities.RssReader;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;

public class NovostiFragment extends Fragment {
    private static final String NOVOSTI_AKTUALNO_URL = "http://www.muzika.hr/rss/rubrika/7/aktualno.aspx";
    private static final String NOVOSTI_SCENA_URL = "http://www.muzika.hr/rss/rubrika/34/scena.aspx";
    private static final String NOVOSTI_RECENZIJE_URL = "http://www.muzika.hr/rss/rubrika/9/recenzije.aspx";

    private View view;
    private Toolbar toolbar;
    private RssReader rssReader;
    private RecyclerView recyclerView;
    private NovostiAdapter novostiAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private ArrayList<NovostiRssItem> emptyRSSItemArray;

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
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_novosti, container, false);

            toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
            ((MainActivity) getActivity()).setCustomToolbar(toolbar, getString(R.string.app_name));

            //initialized empty arraylist because recyclerview neads the value for adapter evan if it's empty.
            // Otherwise onPostExecute returns null on adapter
            emptyRSSItemArray = new ArrayList<>();

            recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                    .color(Color.MAGENTA)
                    .build());

            setCustomLayout(recyclerView, emptyRSSItemArray, novostiAdapter);

        }

        return view;
    }

    /**
     * AsyncTask for downloading rss feed base on url that you provide od execute method
     */
    private class GetRssDataTask extends AsyncTask<String, Void, ArrayList<NovostiRssItem>> {

        @Override
        protected ArrayList<NovostiRssItem> doInBackground(String... strings) {

            ArrayList<NovostiRssItem> novostiRssItemArrayList = null;
            try {
                novostiRssItemArrayList = new ArrayList<>();
                rssReader = new RssReader(strings[0]);
                novostiRssItemArrayList = rssReader.getItems();
                return novostiRssItemArrayList;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return novostiRssItemArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<NovostiRssItem> novostiRssItems) {
            setCustomLayout(recyclerView, novostiRssItems, novostiAdapter);

        }
    }

    /**
     * Method for setting up the adapter to recyclerview. It's used in 2 places in class. First onCreateView with empty arraylist because onPostExecute
     * otherwise returns null on setup adapter and second time in onPostExecute with new arraylist we populated in doInBackground method
     *
     * @param rV             RecyclerView you want to populate
     * @param arrayList      ArrayList with data you want to show in recyclerView
     * @param novostiAdapter Custom adapter for binding arrayList in recyclerView
     */
    public void setCustomLayout(RecyclerView rV, ArrayList<NovostiRssItem> arrayList, NovostiAdapter novostiAdapter) {
        novostiAdapter = new NovostiAdapter(getActivity(), arrayList);
        rV.setAdapter(novostiAdapter);
        novostiAdapter.notifyDataSetChanged();

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_novosti, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_novosti_aktualno:
                new GetRssDataTask().execute(NOVOSTI_AKTUALNO_URL);
                break;
            case R.id.menu_novosti_recenzija:
                new GetRssDataTask().execute(NOVOSTI_RECENZIJE_URL);
                break;
            case R.id.menu_novosti_scena:
                new GetRssDataTask().execute(NOVOSTI_SCENA_URL);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
