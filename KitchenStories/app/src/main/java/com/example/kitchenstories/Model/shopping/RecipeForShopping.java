package com.example.kitchenstories.Model.shopping;

import android.graphics.Bitmap;

public class RecipeForShopping {

    private int id;
    private String name;
    private Bitmap image;
    private String key;

    public RecipeForShopping(){

    }

    public RecipeForShopping(int id, String name, Bitmap image, String key) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getKey() {
        return key;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
