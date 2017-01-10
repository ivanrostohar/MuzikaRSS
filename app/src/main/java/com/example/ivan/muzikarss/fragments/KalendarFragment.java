package com.example.ivan.muzikarss.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ivan.muzikarss.R;


public class KalendarFragment extends Fragment {

    public KalendarFragment() {
    }


    public static KalendarFragment newInstance() {
        KalendarFragment fragment = new KalendarFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kalendar, container, false);




        return view;
    }

}
