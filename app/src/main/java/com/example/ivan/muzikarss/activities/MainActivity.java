package com.example.ivan.muzikarss.activities;

import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.ivan.muzikarss.R;
import com.example.ivan.muzikarss.adapters.KalendarHrvatskaAdapter;
import com.example.ivan.muzikarss.adapters.NovostiAdapter;
import com.example.ivan.muzikarss.adapters.TabAdapter;
import com.example.ivan.muzikarss.fragments.HrvatskaFragment;
import com.example.ivan.muzikarss.fragments.KalendarFragment;
import com.example.ivan.muzikarss.fragments.NovostiFragment;
import com.example.ivan.muzikarss.fragments.WebViewFragment;
import com.example.ivan.muzikarss.models.KalendarHrvatskaModel;
import com.example.ivan.muzikarss.models.NovostiRssItem;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    public TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up the toolbar with home icon enabled
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setCustomToolbar(toolbar, getString(R.string.app_name));

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        //defined the coordinates for the toolbar options menu icon (3 vertical dots)
        Target target = new Target() {
            @Override
            public Point getPoint() {
                int actionBarSize = toolbar.getWidth();
                int x = actionBarSize;
                int y = toolbar.getHeight() / 2;
                return new Point(x,y);
            }
        };

        //builder for building the transparent instruction layout for using the app
        new ShowcaseView.Builder(this)
                .setTarget(target)
                .setContentTitle(getString(R.string.showcase_view_title))
                .setContentText(getString(R.string.showcase_view_description))
                .hideOnTouchOutside()
                .withHoloShowcase()
                .build();


    }

    /**
     * Method for setting up the viewPager with custom adapter that consists of new fragment and its title
     *
     * @param viewPager layout manager which behavior is defined in layout activity_main
     */
    private void setupViewPager(ViewPager viewPager) {
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        tabAdapter.addFragment(new NovostiFragment(), getString(R.string.tab_novosti));
        tabAdapter.addFragment(new HrvatskaFragment(), getString(R.string.tab_hrvatska));
        tabAdapter.addFragment(new KalendarFragment(), getString(R.string.tab_kalendar));
        viewPager.setAdapter(tabAdapter);
    }

    public void switchFragment(int id, Fragment fragment) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(id, fragment).addToBackStack(null).commit();
    }

    /**
     * Method for setting up the toolbar
     *
     * @param toolbar toolbar layout that you want to show
     * @param title   toolbar title
     */
    public void setCustomToolbar(Toolbar toolbar, String title) {
        AppCompatActivity act = this;
        act.setSupportActionBar(toolbar);
        if (toolbar != null)
            toolbar.setTitle(title);
    }

    /**
     * Method for setting up the adapter to recyclerview. It's used in 2 places in Fragment classes. First onCreateView with empty arraylist because onPostExecute
     * otherwise returns null on setup adapter and second time in onPostExecute with new arraylist we populated in doInBackground method. Also here i implemented
     * custom click listener for recycler view items.
     *
     * @param rV                      RecyclerView you want to populate
     * @param arrayList               ArrayList with data you want to show in recyclerView
     * @param tag                     Tag that helps to identify wich custom adapter should be used
     * @param novostiAdapter          Custom adapter for binding arrayList in recyclerView
     * @param kalendarHrvatskaAdapter Custom adapter for binding arrayList in recyclerView
     */
    public void setCustomLayout(RecyclerView rV, ArrayList arrayList, String tag, NovostiAdapter novostiAdapter, KalendarHrvatskaAdapter kalendarHrvatskaAdapter) {
        switch (tag) {
            case "NOVOSTI_ADAPTER":
                novostiAdapter = new NovostiAdapter(getApplicationContext(), arrayList, new NovostiAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(NovostiRssItem novostiRssItem) {
                        Log.v("TITLE", novostiRssItem.getTitle());
                        WebViewFragment webViewFragment = new WebViewFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("LINK", novostiRssItem.getLink());
                        webViewFragment.setArguments(bundle);
                        switchFragment(R.id.activity_main, webViewFragment);
                    }
                });
                rV.setAdapter(novostiAdapter);
                novostiAdapter.notifyDataSetChanged();
                break;

            case "KALENDAR_HRVATSKA_ADAPTER":
                kalendarHrvatskaAdapter = new KalendarHrvatskaAdapter(getApplicationContext(), arrayList, new KalendarHrvatskaAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(KalendarHrvatskaModel kalendarHrvatskaModel) {
                        Log.v("TITLE2", kalendarHrvatskaModel.getTitle());
                        WebViewFragment webViewFragment = new WebViewFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("LINK", kalendarHrvatskaModel.getLink());
                        webViewFragment.setArguments(bundle);
                        switchFragment(R.id.activity_main, webViewFragment);
                    }
                });
                rV.setAdapter(kalendarHrvatskaAdapter);
                kalendarHrvatskaAdapter.notifyDataSetChanged();
                break;
        }

    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0)
            super.onBackPressed();
        else
            getSupportFragmentManager().popBackStack();

    }

}
