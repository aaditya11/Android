package com.example.newsgateway;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.text.method.ScrollingMovementMethod;

public class Fragments extends Fragment {
    private static final String TAG = "ArticleFragment";

    TextView headline;
    TextView date2;
    TextView author;
    TextView content;
    ImageView img;
    TextView val;
    Article a;
    int count1;
    View v;

    public static final String ARTICLE = "ARTICLE";
    public static final String INDEX = "INDEX";
    public static final String TOTAL = "TOTAL";

    public static final Fragments newInstance(Article article, int index, int total)
    {
        Fragments f = new Fragments();
        Bundle bdl = new Bundle(1);
        bdl.putSerializable(ARTICLE, article);
        bdl.putInt(INDEX, index);
        bdl.putInt(TOTAL, total);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        a  = (Article) getArguments().getSerializable(ARTICLE);
        count1 = getArguments().getInt(INDEX)+1;
        int total = getArguments().getInt(TOTAL);
        String lastLine = count1+" of "+total;


        v = inflater.inflate(R.layout.ar, container, false);
        headline = (TextView)v.findViewById(R.id.headline);
        date2 = (TextView) v.findViewById(R.id.date);
        author = (TextView) v.findViewById(R.id.author);
        content = (TextView) v.findViewById(R.id.content);
        val = (TextView) v.findViewById(R.id.index);
        img = (ImageView) v.findViewById(R.id.image);

        val.setText(lastLine);
        if(a.getTitle() != null){ headline.setText(a.getTitle());
        }
        else{headline.setText("");}

        if(a.getDate1() !=null && !a.getDate1().isEmpty()) {

            String sDate1 = a.getDate1();

            Date date1 = null;
            String pubdate = "";
            try {
                if(sDate1 != null){

                    date1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(sDate1);}
                String pattern = "MMM dd, yyyy HH:mm";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                pubdate = simpleDateFormat.format(date1);
                date2.setText(pubdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(a.getAuthor()!=null) {author.setText(a.getAuthor());}
        else{author.setText("");}

        if(a.getDescr() != null) {content.setText(a.getDescr());}
        else{content.setText("");}

        author.setMovementMethod(new ScrollingMovementMethod());

        if(a.getAr_img()!=null){loadRemoteImage(a.getAr_img());}

        headline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(a.getAr_url()));
                startActivity(intent);
            }
        });

        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(a.getAr_url()));
                startActivity(intent);
            }
        });

        author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(a.getAr_url()));
                startActivity(intent);
            }
        });


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(a.getAr_url()));
                startActivity(intent);
            }
        });

        return v;
    }


    private  void loadRemoteImage(final String imageURL){

        if (imageURL != null) {
            Picasso picasso = new Picasso.Builder(getActivity()).listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                    // Here we try https if the http image attempt failed
                    final String changedUrl = imageURL.replace("http:", "https:");
                    picasso.load(changedUrl)
                            .fit()
                            .centerCrop()
                            .error(R.drawable.glitch)
                            .placeholder(R.drawable.placeholder)
                            .into(img);
                }
            }).build();
            picasso.load(imageURL)
                    .fit()
                    .centerCrop()
                    .error(R.drawable.glitch)
                    .placeholder(R.drawable.placeholder)
                    .into(img);
        } else {
//            Picasso.with(getActivity()).load(imageURL)
            Picasso.get().load(imageURL)
                    .fit()
                    .centerCrop()
                    .error(R.drawable.glitch)
                    .placeholder(R.drawable.missing)
                    .into(img);
        }
    }
}
