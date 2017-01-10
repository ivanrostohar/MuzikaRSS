package com.example.ivan.muzikarss.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.ivan.muzikarss.R;
import com.example.ivan.muzikarss.adapters.TabAdapter;
import com.example.ivan.muzikarss.fragments.HrvatskaFragment;
import com.example.ivan.muzikarss.fragments.KalendarFragment;
import com.example.ivan.muzikarss.fragments.NovostiFragment;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up the toolbar with home icon enabled
        toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setCustomToolbar(toolbar, getString(R.string.app_name));

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * Method for setting up the viewPager with custom adapter that consists of new fragment and its title
     * @param viewPager layout manager which behavior is defined in layout activity_main
     */
    private void setupViewPager(ViewPager viewPager) {
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        tabAdapter.addFragment(new NovostiFragment(), getString(R.string.tab_novosti));
        tabAdapter.addFragment(new HrvatskaFragment(), getString(R.string.tab_hrvatska));
        tabAdapter.addFragment(new KalendarFragment(), getString(R.string.tab_kalendar));
        viewPager.setAdapter(tabAdapter);
    }

    /**
     * Method for setting up the toolbar
     * @param toolbar toolbar layout that you want to show
     * @param title toolbar title
     */
    public void setCustomToolbar(Toolbar toolbar, String title){
        AppCompatActivity act = this;
        act.setSupportActionBar(toolbar);
        if(toolbar != null)
            toolbar.setTitle(title);
    }
}
