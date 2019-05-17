package com.example.newsgateway;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Orientation_chnge implements Serializable {
    private ArrayList<Src> src_list = new ArrayList<Src>();
    private ArrayList<Article> ar_list = new ArrayList <Article>();
    private ArrayList<String> cat_l = new ArrayList <String>();
    private int val;
    private HashMap<String, Integer> mc = new HashMap<>();
    private String source_name;
    private int val2;

    public ArrayList <Src> getSrc_list() {
        return src_list;
    }

    public void setSrc_list(ArrayList <Src> src_list) {
        this.src_list = src_list;
    }

    public ArrayList <Article> getAr_list() {
        return ar_list;
    }

    public void setAr_list(ArrayList <Article> ar_list) {
        this.ar_list = ar_list;
    }

    public ArrayList <String> getCat_l() {
        return cat_l;
    }

    public void setCat_l(ArrayList <String> cat_l) {
        this.cat_l = cat_l;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getVal2() {
        return val2;
    }

    public void setVal2(int val2) {
        this.val2 = val2;
    }
    public String getS_name() {
        return source_name;
    }

    public void setS_name(String source_name) {
        this.source_name = source_name;
    }
    public HashMap<String, Integer> getcols() {
        return mc;
    }

    public void setcols(HashMap<String, Integer> mc) {
        this.mc = mc;
    }

}

