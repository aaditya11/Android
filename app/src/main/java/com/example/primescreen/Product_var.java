package com.example.primescreen;

import java.io.Serializable;

class Product_var  implements Serializable {
    private String Image;
    private String name;
    private String positions;
    private String URL;


    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }
    public String getURL() {
        return URL;

    }
    public void setURL(String URL) {
        this.URL = URL;
    }
}

