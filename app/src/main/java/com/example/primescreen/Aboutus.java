package com.example.primescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class Aboutus extends AppCompatActivity {
    String TAG="Aboutus";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("About Prime Screen");
        setContentView(R.layout.activity_aboutus);
        TextView tv1= (TextView)findViewById(R.id.textView2);
        tv1.setMovementMethod(new ScrollingMovementMethod());

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //getSupportActionBar().setLogo(R.drawable.arrow_with_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG,"callback---------------------");
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
