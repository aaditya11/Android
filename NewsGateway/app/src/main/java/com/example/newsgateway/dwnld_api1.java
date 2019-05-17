package com.example.newsgateway;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class dwnld_api1 extends AsyncTask<String,Integer,String> {
    private static final String TAG = "API 1";
    private StringBuilder sb1;
    private boolean noDataFound=false;
    boolean isNoDataFound =true;
    private MainActivity mainActivity;
    private String category;
    private Uri.Builder buildURL = null;
    private ArrayList<Src> srcList = new ArrayList <Src>();
    private ArrayList<String> categoryList = new ArrayList <String>();
    private String API_KEY ="192bf7c3d5f24851b5bf461f479b9a5c";
    private String NewsAPI;
    //private String NewsAPI ="https://newsapi.org/v1/sources?language=en&country=us&category=cat&apiKey="+API_KEY;

    public dwnld_api1(MainActivity mainActivity, String category){
        this.mainActivity = mainActivity;
        if(category.equalsIgnoreCase("all") || category.equalsIgnoreCase("")) {
            this.category = "";
            NewsAPI ="https://newsapi.org/v2/sources?language=en&country=us&category=&apiKey="+API_KEY;
            Log.d("yello", "dwnld_api1: 1"+NewsAPI);
        }
        else
        {
            String p1= "https://newsapi.org/v2/sources?language=en&country=us&category=";
            String p2 ="&apiKey="+API_KEY;
            Log.d("yello", "dwnld_api1: 2");
            NewsAPI = p1+category+p2;
            this.category = category;
        }

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        for(int j=0;j<srcList.size();j++)
        {
            String temp = srcList.get(j).gets_catogary();
            if(!categoryList.contains(temp))
                categoryList.add(temp);
        }
        Log.d("tello", "onPostExecute: Onpost "+srcList);
        mainActivity.setSources(srcList,categoryList);
    }

    @Override
    protected String doInBackground(String... strings) {

        buildURL = Uri.parse(NewsAPI).buildUpon();
        Log.d("my", "doInBackground: "+ buildURL);
        connectToAPI();
        if(!noDataFound) {
            Log.d(TAG, "doInBackground: here!!!!");
            parseJSON1(sb1.toString());
        }
        else {
            Log.d("phsss", "doInBackground: ");
        }
        return null;
    }


    public void connectToAPI() {

        String urlToUse = buildURL.build().toString();
        sb1 = new StringBuilder();
        try {
            URL url = new URL(urlToUse);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if(conn.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND)
            {
                noDataFound=true;
            }
            else {
                conn.setRequestMethod("GET");
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader((new InputStreamReader(is)));

                String line=null;
                while ((line = reader.readLine()) != null) {
                    sb1.append(line).append('\n');
                }
                noDataFound=false;

            }
        }
        catch(FileNotFoundException fe){
            Log.d(TAG, "FileNotFoundException ");
        }
        catch (Exception e) {
            //e.printStackTrace();
            Log.d(TAG, "Exception doInBackground: " + e.getMessage());
        }
    }


    private void parseJSON1(String s) {
        try{
            if(!noDataFound){
                Log.d("qote", "parseJSON1: ");
                JSONObject jObjMain = new JSONObject(s);
                JSONArray sources = jObjMain.getJSONArray("sources");
                for(int i=0;i<sources.length();i++){
                    JSONObject src = (JSONObject) sources.get(i);
                    Src srcObj = new Src();
                    Log.d("Tello", "parseJSON1: ");
                    srcObj.sets_id(src.getString("id"));
                    Log.d("Tello", "parseJSON1: "+src.getString("id"));
                    srcObj.sets_catogary(src.getString("category"));
                    srcObj.sets_name(src.getString("name"));
                    srcObj.sets_url(src.getString("url"));
                    srcList.add(srcObj);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



}
