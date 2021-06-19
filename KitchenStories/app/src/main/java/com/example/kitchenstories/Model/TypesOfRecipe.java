package com.example.kitchenstories.Model;

import java.util.Map;

public class TypesOfRecipe {

    String nameOfType;
    Map<String, Boolean> tag;
    String url_typedOfRecipe;

    public TypesOfRecipe() {
    }

    public TypesOfRecipe(String nameOfType, Map<String, Boolean> tag, String url_typedOfRecipe) {
        this.nameOfType = nameOfType;
        this.tag = tag;
        this.url_typedOfRecipe = url_typedOfRecipe;
    }

    public String getNameOfType() {
        return nameOfType;
    }

    public void setNameOfType(String nameOfType) {
        this.nameOfType = nameOfType;
    }

    public Map<String, Boolean> getTag() {
        return tag;
    }

    public void setTag(Map<String, Boolean> tag) {
        this.tag = tag;
    }

    public String getUrl_typedOfRecipe() {
        return url_typedOfRecipe;
    }

    public void setUrl_typedOfRecipe(String url_typedOfRecipe) {
        this.url_typedOfRecipe = url_typedOfRecipe;
    }
}
