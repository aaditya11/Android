package com.example.primescreen;
import android.util.Log;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

public class CustomOnItemSelectedListener extends Products implements android.widget.AdapterView.OnItemSelectedListener {

    @Override

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(adapterView.getContext(),
                "OnItemSelectedListener : " + adapterView.getItemAtPosition(i).toString(),
                Toast.LENGTH_SHORT).show();

        if (adapterView.getItemAtPosition(i).toString().equals("T-CUP")){

           do_change();


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
