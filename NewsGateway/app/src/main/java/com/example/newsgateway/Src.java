package com.example.newsgateway;

import java.io.Serializable;

public class Src implements Serializable {
    String s_id;
    String s_url;
    String s_name;
    String s_catogary;

    public String gets_url() {
        return s_url;
    }

    public void sets_url(String s_url) {
        this.s_url = s_url;
    }
    public String gets_id() {
        return s_id;
    }

    public void sets_id(String s_id) {
        this.s_id = s_id;
    }
    public String gets_catogary() {
        return s_catogary;

    }


    public void sets_catogary(String s_catogary) {
        this.s_catogary = s_catogary;
    }
    public String gets_name() {
        return s_name;
    }

    public void sets_name(String s_name) {
        this.s_name = s_name;
    }


}

