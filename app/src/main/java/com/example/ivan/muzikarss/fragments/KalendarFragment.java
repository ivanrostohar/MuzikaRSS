package com.example.ivan.muzikarss.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.ivan.muzikarss.R;
import com.example.ivan.muzikarss.activities.MainActivity;


public class KalendarFragment extends Fragment {
    private Toolbar toolbar;

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
        View view = inflater.inflate(R.layout.fragment_kalendar, container, false);

        toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setCustomToolbar(toolbar, getString(R.string.app_name));



        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_kalendar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
