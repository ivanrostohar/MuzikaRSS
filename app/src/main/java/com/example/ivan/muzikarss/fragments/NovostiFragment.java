package com.example.ivan.muzikarss.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ivan.muzikarss.R;

public class NovostiFragment extends Fragment {

    public NovostiFragment() {
    }


    public static NovostiFragment newInstance() {
        NovostiFragment fragment = new NovostiFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_novosti, container, false);


        
        return view;
    }

}
