package com.example.newsgateway;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import android.content.IntentFilter;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean serviceRunning = false;
    static final String ACTION_MSG_TO_SERVICE = "ACTION_MSG_TO_SERVICE";
    static final String ACTION_NEWS_STORY = "ACTION_NEWS_STORY";
    static final String ARTICLE_LIST = "ARTICLE_LIST";
    static final String SOURCE_ID = "SOURCE_ID";
    private ArrayList<SpannableString> sl = new ArrayList<SpannableString>();
    private ArrayList<String> cl = new ArrayList<String>();
    private ArrayList<Src> list1 = new ArrayList<Src>();
    private ArrayList<Article> list2 = new ArrayList<Article>();
    private HashMap<String, Src> hm1 = new HashMap<>();
    HashMap<String, Integer> mc = new HashMap<>();
    HashMap<String, Src> sc = new HashMap<>();
    private Menu opt_menu;
    private NewsReceiver newsReceiver;
    private String now_n;
    private ArrayAdapter adapter;
    private MyPageAdapter pageAdapter;
    private List<Fragment> fragments;
    private ViewPager pager;
    private boolean flag;
    private int now_pnt;
    private int[] cols;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        if (!serviceRunning && savedInstanceState == null) {
            Intent intent = new Intent(MainActivity.this, News_Serv.class);
            startService(intent);
            serviceRunning = true;
        }
        cols = getResources().getIntArray(R.array.rainbow);
        newsReceiver = new NewsReceiver();
        IntentFilter filter = new IntentFilter(MainActivity.ACTION_NEWS_STORY);
        registerReceiver(newsReceiver, filter);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);


        mDrawerList.setOnItemClickListener(
                new ListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        pager.setBackgroundResource(0);
                        now_pnt = position;
                        selectItem(position);

                    }
                }
        );

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        );

        adapter = new ArrayAdapter<>(this, R.layout.list_val, sl);
        mDrawerList.setAdapter(adapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        fragments = new ArrayList<>();

        pageAdapter = new MyPageAdapter(getSupportFragmentManager());
        pager = findViewById(R.id.viewPager);
        pager.setAdapter(pageAdapter);

        if (hm1.isEmpty() && savedInstanceState == null)
            new dwnld_api1(this, "").execute();

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public boolean onOptionsItemSelected(MenuItem item) {


        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        Log.d("hello", "onOptionsItemSelected: ");
        new dwnld_api1(this, item.getTitle().toString()).execute();
        mDrawerLayout.openDrawer(mDrawerList);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private void selectItem(int position) {
        now_n = sl.get(position).toString();
        Intent intent = new Intent(MainActivity.ACTION_MSG_TO_SERVICE);
        intent.putExtra(SOURCE_ID, now_n);
        sendBroadcast(intent);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("hello", "onCreateOptionsMenu: ");

        getMenuInflater().inflate(R.menu.main_menu, menu);
        opt_menu = menu;
        if (flag) {
            Log.d(TAG, "onCreateOptionsMenu: here!!");
            opt_menu.add("All");
            for (String s : cl)
                opt_menu.add(s);
        } else {
            Log.d(TAG, "onCreateOptionsMenu: here222");
        }
        return true;
    }

    public void setSources(ArrayList<Src> sourceList, ArrayList<String> categoryList) {
        hm1.clear();
        sl.clear();
        list1.clear();

        list1.addAll(sourceList);

//        for(int i=0;i<sourceList.size();i++){
//            sl.add(sourceList.get(i).gets_name());
//            hm1.put(sourceList.get(i).gets_name(), (Src)sourceList.get(i));
//        }
        if (!opt_menu.hasVisibleItems()) {
            cl.clear();
            cl = categoryList;
            opt_menu.add("All");
            Collections.sort(categoryList);
            for (String s : categoryList)
                opt_menu.add(s);
            doCols();
        }
        sl.clear();
        for (int k = 0; k < sourceList.size(); k++) {
            String key = sourceList.get(k).gets_catogary();
            Integer color = mc.get(key);
            SpannableString spanString = new SpannableString(sourceList.get(k).gets_name());
            spanString.setSpan(new ForegroundColorSpan(color), 0, spanString.length(), 0);
            sl.add(spanString);
            hm1.put(sourceList.get(k).gets_name(), (Src) sourceList.get(k));

        }
//        if(opt_menu.size()!=0)
//        {
//            for(int i=0;i<opt_menu.size();i++)
//            {
//                MenuItem item=opt_menu.getItem(i);
//                SpannableString sp=new SpannableString(item.getTitle());
//                sp.setSpan(new ForegroundColorSpan(Color.parseColor(cols[i])),0,sp.length(),0);
//                item.setTitle(sp);
//            }
//        }
        adapter.notifyDataSetChanged();
    }


    private void reDoFragments(ArrayList<Article> articles) {

        setTitle(now_n);
        for (int i = 0; i < pageAdapter.getCount(); i++)
            pageAdapter.notifyChangeInPosition(i);

        fragments.clear();

        for (int i = 0; i < articles.size(); i++) {
            Article a = articles.get(i);

            fragments.add(Fragments.newInstance(articles.get(i), i, articles.size()));
        }

        pageAdapter.notifyDataSetChanged();
        pager.setCurrentItem(0);
        list2 = articles;
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(newsReceiver);
        Intent intent = new Intent(MainActivity.this, NewsReceiver.class);
        stopService(intent);
        super.onDestroy();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Orientation_chnge orientation_chnge = new Orientation_chnge();
        orientation_chnge.setCat_l(cl);
        orientation_chnge.setSrc_list(list1);
        orientation_chnge.setVal2(pager.getCurrentItem());
        orientation_chnge.setVal(now_pnt);
        orientation_chnge.setAr_list(list2);
        orientation_chnge.setS_name(now_n);
        orientation_chnge.setcols(mc);
        outState.putSerializable("state", orientation_chnge);
        super.onSaveInstanceState(outState);
    }

    private class MyPageAdapter extends FragmentPagerAdapter {
        private long def = 0;


        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public long getItemId(int position) {
            // give an ID different from position when position has been changed
            return def + position;
        }

        public void notifyChangeInPosition(int n) {
            def += getCount() + n;
        }


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Orientation_chnge orientation_chnge = (Orientation_chnge) savedInstanceState.getSerializable("state");
        flag = true;
        setTitle("News Gateway");
        list2 = orientation_chnge.getAr_list();
        cl = orientation_chnge.getCat_l();
        list1 = orientation_chnge.getSrc_list();
        now_n = orientation_chnge.getS_name();
        mc=orientation_chnge.getcols();

        for (int i = 0; i < list1.size(); i++) {
            String key = list1.get(i).gets_catogary();
            Integer color = mc.get(key);
            SpannableString spanString = new SpannableString(list1.get(i).gets_name());
            spanString.setSpan(new ForegroundColorSpan(color), 0, spanString.length(), 0);
            sl.add(spanString);
            hm1.put(list1.get(i).gets_name(), (Src) list1.get(i));
        }
        mDrawerList.clearChoices();
        adapter.notifyDataSetChanged();
        mDrawerList.setOnItemClickListener(

                new ListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        pager.setBackgroundResource(0);
                        now_pnt = position;
                        selectItem(position);

                    }
                }
        );

        pager.setBackgroundResource(0);
        reDoFragments(list2);
        pager.setCurrentItem(orientation_chnge.getVal2());
    }


    class NewsReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case ACTION_NEWS_STORY:
                    ArrayList<Article> artList;
                    if (intent.hasExtra(ARTICLE_LIST)) {
                        artList = (ArrayList<Article>) intent.getSerializableExtra(ARTICLE_LIST);
                        reDoFragments(artList);
                    }
                    break;
            }
        }
    }


    public void doCols(){
        mc.clear();
        for(int i = 0; i < opt_menu.size(); i++) {
            MenuItem item = opt_menu.getItem(i);
            SpannableString spanString = new SpannableString(opt_menu.getItem(i).getTitle().toString());
            spanString.setSpan(new ForegroundColorSpan(cols[i]), 0,     spanString.length(), 0); //fix the color to white
            item.setTitle(spanString);
            mc.put(opt_menu.getItem(i).getTitle().toString(),cols[i]);
        }
    }}






