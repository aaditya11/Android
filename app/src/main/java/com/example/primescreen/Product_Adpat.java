package com.example.primescreen;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

 public class Product_Adpat extends RecyclerView.Adapter<Product_Adpat.ViewHolder> {
            private Products context;
            private List<Product_var> list;
            private String username;
            ImageView imgview;
            private static final String TAG = "StockAdapter";

            public Product_Adpat(Products context1, List<Product_var> list) {
                this.context = context1;
                this.list = list;
            }

            public void Refreshadapter(List<Product_var> list)
            {
                this.list = list;
                notifyDataSetChanged();
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list, viewGroup, false);
                view.setOnClickListener(context);
                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
                Product_var st = list.get(position);
//                byte [] encodeByte= Base64.decode(st.getImage(),Base64.DEFAULT);
//                Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
//                viewHolder.image.setImageBitmap(bitmap);
                String mDrawableName = String.valueOf(st.getImage());
                Resources res = context.getResources();
                int resID = res.getIdentifier(mDrawableName , "drawable", context.getPackageName());
//                imgView.setImageResource(resID);
                Log.d(TAG, "onBindViewHolder: hhh11"+resID+mDrawableName);
                viewHolder.image.setImageResource(resID);
                viewHolder.name.setText(String.valueOf(st.getName()));
                viewHolder.position.setText(String.valueOf(st.getPositions()));

            }

            @Override
            public int getItemCount() {
                return list.size();
            }

            class ViewHolder extends RecyclerView.ViewHolder {
                public ImageView image;
                public TextView name;
                public TextView position;


                public ViewHolder(@NonNull View itemView) {
                    super(itemView);
                    image = itemView.findViewById(R.id.imageView2);
                    name = itemView.findViewById(R.id.textView3);
                    position = itemView.findViewById(R.id.textView4);
                }
            }
        }

