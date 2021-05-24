package com.example.kitchenstories.Model;

public class Recipe {

    private int image_CookingRecipe;
    private String name_cooking_recipe;
    private int image_author;
    private String name_author;
    private String name_authorGroup;


    public Recipe(int image_CookingRecipe, String name_cooking_recipe, int image_author, String name_author, String name_authorGroup) {
        this.image_CookingRecipe = image_CookingRecipe;
        this.name_cooking_recipe = name_cooking_recipe;
        this.image_author = image_author;
        this.name_author = name_author;
        this.name_authorGroup = name_authorGroup;
    }

    public Recipe(int image_CookingRecipe, String name_cooking_recipe, String name_authorGroup) {
        this.image_CookingRecipe = image_CookingRecipe;
        this.name_cooking_recipe = name_cooking_recipe;
        this.name_authorGroup = name_authorGroup;
    }

    public Recipe(int image_CookingRecipe, String name_cooking_recipe) {
        this.image_CookingRecipe = image_CookingRecipe;
        this.name_cooking_recipe = name_cooking_recipe;
    }

    public int getImage_CookingRecipe() {
        return image_CookingRecipe;
    }

    public void setImage_CookingRecipe(int image_CookingRecipe) {
        this.image_CookingRecipe = image_CookingRecipe;
    }

    public String getName_cooking_recipe() {
        return name_cooking_recipe;
    }

    public void setName_cooking_recipe(String name_cooking_recipe) {
        this.name_cooking_recipe = name_cooking_recipe;
    }

    public int getImage_author() {
        return image_author;
    }

    public void setImage_author(int image_author) {
        this.image_author = image_author;
    }

    public String getName_author() {
        return name_author;
    }

    public void setName_author(String name_author) {
        this.name_author = name_author;
    }

    public String getName_authorGroup() {
        return name_authorGroup;
    }

    public void setName_authorGroup(String name_authorGroup) {
        this.name_authorGroup = name_authorGroup;
    }
}
