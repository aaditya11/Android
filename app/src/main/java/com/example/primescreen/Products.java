package com.example.primescreen;

import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Products extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    List<Product_var> list1 = new ArrayList<>();
    RecyclerView recyclerView;
    Product_Adpat mAdapter;
    DataBase_Helper mDb_help;
    String TAG = "Products";
    String Head_URL = "https://www.ebay.com/itm/";
    Spinner spinner1;
    SearchView searchView;
    public Product_Adpat a_Adapter;
    private static final String[] paths = {"All ▼", "T-Cup ▼", "Dip Cup ▼"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.rec1);

        mAdapter = new Product_Adpat(Products.this, list1);
        Log.d(TAG, "do_change1: " + mAdapter);

        mDb_help = new DataBase_Helper(this);
        recyclerView.setAdapter(mAdapter);
        populate_list();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        searchView = (SearchView) findViewById(R.id.sv1);
        spinner1 = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(Products.this);

//        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


//        addListenerOnSpinnerItemSelection();


        Add_dt("WDOA-234", "3-Panel Card; COC, mAMP, THC", "image_dip", "163730455736");
        Add_dt("WDOA-144", "4-Panel Card; COC, mAMP, OPI, THC", "image_dip", "163065031779");
        Add_dt("WDOA-154", "5-Panel Card; AMP, COC, OPI, PCP, THC", "image_dip", "163063263511");
        Add_dt("WDOA-254", "5-Panel Card; AMP, COC, mAMP, OPI, THC", "image_dip", "163730465992");
        Add_dt("WDOA-354", "5-Panel Card; COC, mAMP, OPI, OXY, THC", "image_dip", "163063266119");
        Add_dt("WDOA-554", "5-Panel Card; COC, mAMP, MDMA, OPI, THC", "image_dip", "163730579908");
        Add_dt("WDOA-654", "5-Panel Card; BZO, COC, mAMP, OPI, THC", "image_dip", "163063242043");
        Add_dt("WDOA-754", "5-Panel Card; AMP, BZO, COC, OPI, THC", "image_dip", "163063243267");
        Add_dt("WDOA-264", "6-Panel Card; AMP, BZO, COC, mAMP, OPI, THC", "image_dip", "163730566215");
        Add_dt("WDOA-564", "6-Panel Card; BZO, COC, mAMP, OPI, OXY, THC", "image_dip", "163730564147");
        Add_dt("WDOA-865", "6-Panel Card; BUP, BZO, COC, MOP, MTD, OXY", "image_dip", "163730562781");
        Add_dt("WDOA-274", "7-Panel Card; AMP, BZO, COC, mAMP, OPI, OXY, THC", "image_dip", "163730557383");
        Add_dt("WDOA-295", "9-Panel Card; AMP, BUP, BZO, COC, MOP, MTD, OXY, PPX, THC", "image_dip", "163730557383");
        Add_dt("WDOA-1104", "10-Panel Card; AMP, BAR, BZO, COC, mAMP, MTD, OPI, PCP, TCA, THC", "image_dip", "162911177784");
        Add_dt("WDOA-3104", "10-Panel Card; AMP, BAR, BZO, COC, mAMP, MDMA, MTD, OPI, PCP, THC", "image_dip", "162911230213");
        Add_dt("WDOA-4104", "10-Panel Card; AMP, BAR, BZO, COC, mAMP, MTD, OPI, OXY, PCP, THC", "image_dip", "163063266124");
        Add_dt("WDOA-7104", "10-Panel Card; BAR, BUP, BZO, COC, mAMP, MTD, OPI, OXY, TCA, THC\n", "image_dip", "163063262527");
        Add_dt("WDOA-9104", "10-Panel Card; AMP, BAR, BUP, BZO, COC, mAMP, MTD, OPI, OXY, THC", "image_dip", "163730552558");
        Add_dt("WDOA-1124", "12-Panel Card; AMP, BAR, BZO, COC, mAMP, MDMA, MTD, OPI, OXY, PCP, PPX, THC", "image_dip", "163730503117");
        Add_dt("WDOA-3124", "12-Panel Card; AMP, BAR, BUP, BZO, COC, MDMA, MTD, OPI, OXY, PCP, TCA, THC", "image_dip", "163730498738");
        Add_dt("WDOA-6124", "12-Panel Card; AMP, BAR, BUP, BZO, COC, mAMP, MDMA, MTD, OPI, OXY, PCP, THC", "image_dip", "163730489481");
        Add_dt("WDOA-6125", "12-Panel Card; AMP, BAR, BUP, BZO, COC, mAMP, MDMA, MOP, MTD, OXY, PCP, THC", "image_dip", "163730483677");
        Add_dt("WDOA-7125", "12-Panel Card; AMP, BAR, BZO, COC, mAMP, MDMA, MOP, MTD, OXY, PCP, TCA, THC", "image_dip", "163730468147");

        Add_dt("TDOA-154", "5-Panel T-Cup; AMP,COC,OPI,PCP,THC", "tcup_bg", "163730305149");
        Add_dt("TDOA-254", "5-Panel T-Cup; AMP,COC,OPI,mAMP,THC", "tcup_bg", "163728727593");
        Add_dt("TDOA-254A3", "5-Panel T-Cup; AMP,COC,OPI,mAMP,THC with 3 AD*", "tcup_bg", "163728740075");
        Add_dt("TDOA-654", "5-Panel T-Cup; BZO, COC, mAMP, OPI, THC", "tcup_bg", "163728748785");
        Add_dt("TDOA-264", "6-Panel T-Cup; AMP, BZO, COC, mAMP, OPI, THC", "tcup_bg", "163728755592");
        Add_dt("TDOA-264A3", "6-Panel T-Cup; AMP, BZO, COC, mAMP, OPI, THC with 3 AD", "tcup_bg", "163728758530");
        Add_dt("TDOA-564", "6-Panel T-Cup; BZO, COC, mAMP, OPI, OXY, THC", "tcup_bg", "163728762985");
        Add_dt("TDOA-274", "7-Panel T-Cup; AMP, BZO, COC, mAMP, OPI, OXY, THC", "tcup_bg", "163728769273");
        Add_dt("TDOA-3104", "10-Panel T-Cup; AMP, BAR, BZO, COC, mAMP, MDMA, MTD, OPI, PCP, THC", "tcup_bg", "163728805991");
        Add_dt("TDOA-3104A3", "10-Panel T-Cup; AMP, BAR, BZO, COC, mAMP, MDMA, MTD, OPI, PCP, THC with 3 AD", "tcup_bg", "163728815292");
        Add_dt("TDOA-4104", "10-Panel T-Cup; AMP, BAR, BZO, COC, mAMP, MTD, OPI, OXY, PCP, THC", "tcup_bg", "163728826812");
        Add_dt("TDOA-7104", "10-Panel T-Cup; BAR, BUP, BZO, COC, mAMP, MTD, OPI, OXY, TCA, THC", "tcup_bg", "163728826812");
        Add_dt("TDOA-7104A3", "10-Panel T-Cup; BAR, BUP, BZO, COC, mAMP, MTD, OPI, OXY, TCA, THC with 3 AD", "tcup_bg", "163728832498");
        Add_dt("TDOA-8104", "10-Panel T-Cup; AMP, BUP, BZO, COC, mAMP, MDMA, MTD, OPI, OXY, THC", "tcup_bg", "163728835156");
        Add_dt("TDOA-3124", "12-Panel T-Cup; AMP, BAR, BUP, BZO, COC, MDMA, MTD, OPI, OXY, PCP, TCA, THC", "tcup_bg", "163728838537");
        Add_dt("TDOA-4124", "12-Panel T-Cup; AMP, BAR, BUP, BZO, COC, mAMP, MTD, OPI, OXY, PCP, PPX, THC", "tcup_bg", "163728840794");
        Add_dt("TDOA-6124", "12-Panel T-Cup; AMP, BAR, BUP, BZO, COC, mAMP, MDMA, MTD, OPI, OXY, PCP, THC", "tcup_bg", "163728842665");
        Add_dt("TDOA-6124A3", "12-Panel T-Cup; AMP, BAR, BUP, BZO, COC, mAMP, MDMA, MTD, OPI, OXY, PCP, THC with 3 AD", "tcup_bg", "163728845353");
        Add_dt("TDOA-6125", "12-Panel T-Cup; AMP, BAR, BUP, BZO, COC, mAMP, MDMA, MOP, MTD, OXY, PCP, THC", "tcup_bg", "163728848794");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                CharSequence use = searchView.getQuery();
                Log.d(TAG, "onQueryTextSubmit: hh55" + use);
                search_populate(use);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
// do something when text changes
                CharSequence use = searchView.getQuery();
                Log.d(TAG, "onQueryTextSubmit: hh55" + use);
                search_populate1(use);
                return false;
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.product_menu, menu);
        return true;
    }

    public void Add_dt(String i, String j, String k, String l) {
        boolean insert_data = mDb_help.add_data(i, j, k, l);
        if (insert_data) {
            Log.d(TAG, "Add_dt: success");
        } else {
            Log.d(TAG, "Failed");
        }
    }

    //    public void setData() {
//
//        ArrayList<Product_var> ls = new ArrayList<>();
//        for(int i=0;i<5;i++) {
//            Product_var pv=new Product_var();
//            pv.setName("SNU"+i);
//            pv.setImage("Image Here");
//            pv.setPositions("Short Description here"+i);
//            ls.add(pv);
//        }
//        setmadapter(ls);
//    }
    public void setmadapter(ArrayList<Product_var> ls) {
        list1.clear();
        list1.addAll(ls);
        java.util.Collections.shuffle(list1);
        mAdapter.Refreshadapter(list1);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View view) {
        int pos = recyclerView.getChildLayoutPosition(view);
        Product_var p = list1.get(pos);
        String url = Head_URL + p.getURL();

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void populate_list() {
        Log.d("here121", "popilating");
        ArrayList<Product_var> ls = new ArrayList<>();
        Cursor data = mDb_help.getData();
        ;
        while (data.moveToNext()) {
            Product_var pv = new Product_var();
            pv.setName(data.getString(1));
            Log.d(TAG, "populate_list22: " + data.getString(1));
            pv.setPositions(data.getString(2));
            pv.setImage(data.getString(0));
            pv.setURL(data.getString(3));
            ls.add(pv);
        }
        setmadapter(ls);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("◘ Press for Ebay\n\n◘ Long Press for Amazon");
                builder.setTitle("View Product");
                builder.setIcon(android.R.drawable.ic_menu_info_details);
                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);

    }

    //    public void addListenerOnSpinnerItemSelection() {
//        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
//
//    }
    public void do_change() {
        list1.clear();
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                populate_list();
                break;
            case 1:
                populate_list_t();
                break;
            case 2:
                populate_list_w();
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }


    public void populate_list_t() {
        Log.d("here121", "popilating");
        ArrayList<Product_var> ls = new ArrayList<>();
        Cursor data = mDb_help.getData_t();
        while (data.moveToNext()) {
            Product_var pv = new Product_var();
            pv.setName(data.getString(1));
            Log.d(TAG, "populate_list22: " + data.getString(1));
            pv.setPositions(data.getString(2));
            pv.setImage(data.getString(0));
            pv.setURL(data.getString(3));
            ls.add(pv);
        }
        setmadapter(ls);
    }

    public void populate_list_w() {
        Log.d("here121", "popilating");
        ArrayList<Product_var> ls = new ArrayList<>();
        Cursor data = mDb_help.getData_w();
        while (data.moveToNext()) {
            Product_var pv = new Product_var();
            pv.setName(data.getString(1));
            Log.d(TAG, "populate_list22: " + data.getString(1));
            pv.setPositions(data.getString(2));
            pv.setImage(data.getString(0));
            pv.setURL(data.getString(3));
            ls.add(pv);
        }
        setmadapter(ls);
    }

    public void search_populate(CharSequence ch) {
        ArrayList<Product_var> ls = new ArrayList<>();
        Cursor data = mDb_help.getData_w();
        while (data.moveToNext()) {
            Product_var pv = new Product_var();
            String val = data.getString(2);

            String[] wrd=ch.toString().split("\\s");
//            Log.d(TAG, "to_comparing22: " + wrd[1]);
            if(wrd.length>1){
            for(int i=0;i<wrd.length;i++){

            if (val.toLowerCase().contains(wrd[i].toLowerCase())) {
                pv.setName(data.getString(1));
                Log.d(TAG, "comparing11: " + data.getString(2));
                pv.setPositions(data.getString(2));
                pv.setImage(data.getString(0));
                pv.setURL(data.getString(3));
                ls.add(pv);

            }}
        }
        else{
                if (val.toLowerCase().contains(ch.toString().toLowerCase())) {
                    pv.setName(data.getString(1));
                    Log.d(TAG, "comparing11: " + data.getString(2));
                    pv.setPositions(data.getString(2));
                    pv.setImage(data.getString(0));
                    pv.setURL(data.getString(3));
                    ls.add(pv);
        }
        }
        if (!ls.isEmpty()) {

            setmadapter(ls);
        } else {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Not Found Try Something Else");
            builder1.setTitle("OOPS");
            builder1.setIcon(android.R.drawable.stat_notify_error);
            builder1.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            AlertDialog dialog = builder1.create();
            dialog.show();

        }
    }}
    public void search_populate1 (CharSequence ch){
                ArrayList<Product_var> ls = new ArrayList<>();
                Cursor data = mDb_help.getData_w();
                while (data.moveToNext()) {
                    Product_var pv = new Product_var();
                    String val = data.getString(2);
                    Log.d(TAG, "to_comparing: " + data.getString(2));
                    if (val.toLowerCase().contains(ch.toString().toLowerCase())) {
                        pv.setName(data.getString(1));
                        Log.d(TAG, "comparing11: " + data.getString(2));
                        pv.setPositions(data.getString(2));
                        pv.setImage(data.getString(0));
                        pv.setURL(data.getString(3));
                        ls.add(pv);

                    }
                }
                if (!ls.isEmpty()) {

                    setmadapter(ls);
                }


            }
        }



