package com.example.newsgateway;
import java.io.Serializable;

 public class Article implements Serializable {
        String author;
        String title;
        String descr;
        String ar_img;
        String date1;
        String ar_url;

        public String getAr_url() {
            return ar_url;
        }

        public void setAr_url(String ar_url) {
            this.ar_url = ar_url;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescr() {
            return descr;
        }

        public void setDescr(String descr) {
            this.descr = descr;
        }

        public String getAr_img() {
            return ar_img;
        }

        public void setAr_img(String ar_img) {
            this.ar_img = ar_img;
        }

        public String getDate1() {
            return date1;
        }

        public void setDate1(String date1) {
            this.date1 = date1;
        }
    }


