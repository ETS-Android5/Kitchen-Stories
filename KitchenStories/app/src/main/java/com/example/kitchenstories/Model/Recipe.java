package com.example.kitchenstories.Model;

public class Recipe {

    private int image_CookingRecipe;
    private String name_cooking_recipe;
    private int image_author;
    private String author_recipe;
    private String authorGroup_recipe;


    public Recipe(int image_CookingRecipe, String name_cooking_recipe, int image_author, String author_recipe, String authorGroup_recipe) {
        this.image_CookingRecipe = image_CookingRecipe;
        this.name_cooking_recipe = name_cooking_recipe;
        this.image_author = image_author;
        this.author_recipe = author_recipe;
        this.authorGroup_recipe = authorGroup_recipe;
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

    public String getAuthor_recipe() {
        return author_recipe;
    }

    public void setAuthor_recipe(String author_recipe) {
        this.author_recipe = author_recipe;
    }

    public String getAuthorGroup_recipe() {
        return authorGroup_recipe;
    }

    public void setAuthorGroup_recipe(String authorGroup_recipe) {
        this.authorGroup_recipe = authorGroup_recipe;
    }
}
