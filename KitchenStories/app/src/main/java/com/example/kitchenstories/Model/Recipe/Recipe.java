package com.example.kitchenstories.Model.Recipe;

import java.util.ArrayList;
import java.util.Map;

public class Recipe {

    private int image_CookingRecipe;
    private String name_cooking_recipe;
    private String url_image_CookingRecipe;


    private int image_author;
    private String name_author;
    private String name_authorGroup;
    private String contact_author;
    private String author_description;
    private String url_image_author;

    private String likeAmount;
    private String ratingAmount;

    private String difficulty_Level_Recipe;
    private ArrayList<String> periodCooking;
    private ArrayList<String> ingredients;
    private ArrayList<String> amountOfIngredients;
    private String utensils;
    private ArrayList<String> nutritionPerServing;
    private Map<String, Boolean> tags;

    //private ArrayList<StepsForRecipe> steps;




    public Recipe() {
    }

    public Recipe(String name_cooking_recipe, String url_image_CookingRecipe) {
        this.name_cooking_recipe = name_cooking_recipe;
        this.url_image_CookingRecipe = url_image_CookingRecipe;
    }

    // doing Firebase
    public Recipe(String name_cooking_recipe,
                  String url_image_CookingRecipe,
                  String name_author,
                  String name_authorGroup,
                  String contact_author,
                  String author_description,
                  String url_image_author,
                  String likeAmount,
                  String ratingAmount,
                  String difficulty_Level_Recipe,
                  ArrayList<String> periodCooking,
                  ArrayList<String> ingredients,
                  ArrayList<String> amountOfIngredients,
                  String utensils,
                  ArrayList<String> nutritionPerServing,
                  Map<String, Boolean> tags) {
        this.name_cooking_recipe = name_cooking_recipe;
        this.url_image_CookingRecipe = url_image_CookingRecipe;
        this.name_author = name_author;
        this.name_authorGroup = name_authorGroup;
        this.contact_author = contact_author;
        this.author_description = author_description;
        this.url_image_author = url_image_author;
        this.likeAmount = likeAmount;
        this.ratingAmount = ratingAmount;
        this.difficulty_Level_Recipe = difficulty_Level_Recipe;
        this.periodCooking = periodCooking;
        this.ingredients = ingredients;
        this.amountOfIngredients = amountOfIngredients;
        this.utensils = utensils;
        this.nutritionPerServing = nutritionPerServing;
        this.tags = tags;
    }


    // add recipe to USER
    public Recipe(String name_cooking_recipe,
                  String url_image_CookingRecipe,
                  String name_author,
                  String name_authorGroup,
                  String url_image_author,
                  String likeAmount,
                  ArrayList<String> periodCooking) {
        this.name_cooking_recipe = name_cooking_recipe;
        this.url_image_CookingRecipe = url_image_CookingRecipe;
        this.name_author = name_author;
        this.name_authorGroup = name_authorGroup;
        this.url_image_author = url_image_author;
        this.likeAmount = likeAmount;
        this.periodCooking = periodCooking;
    }

    // default
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

    public String getUrl_image_CookingRecipe() {
        return url_image_CookingRecipe;
    }

    public void setUrl_image_CookingRecipe(String url_image_CookingRecipe) {
        this.url_image_CookingRecipe = url_image_CookingRecipe;
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

    public String getDifficulty_Level_Recipe() {
        return difficulty_Level_Recipe;
    }

    public void setDifficulty_Level_Recipe(String difficulty_Level_Recipe) {
        this.difficulty_Level_Recipe = difficulty_Level_Recipe;
    }



    public String getUtensils() {
        return utensils;
    }

    public void setUtensils(String utensils) {
        this.utensils = utensils;
    }

    public ArrayList<String> getPeriodCooking() {
        return periodCooking;
    }

    public void setPeriodCooking(ArrayList<String> periodCooking) {
        this.periodCooking = periodCooking;
    }

    public ArrayList<String> getNutritionPerServing() {
        return nutritionPerServing;
    }

    public void setNutritionPerServing(ArrayList<String> nutritionPerServing) {
        this.nutritionPerServing = nutritionPerServing;
    }

    public Map<String, Boolean> getTags() {
        return tags;
    }

    public void setTags(Map<String, Boolean> tags) {
        this.tags = tags;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getAmountOfIngredients() {
        return amountOfIngredients;
    }

    public void setAmountOfIngredients(ArrayList<String> amountOfIngredients) {
        this.amountOfIngredients = amountOfIngredients;
    }

    public String getContact_author() {
        return contact_author;
    }

    public void setContact_author(String contact_author) {
        this.contact_author = contact_author;
    }

    public String getAuthor_description() {
        return author_description;
    }

    public void setAuthor_description(String author_description) {
        this.author_description = author_description;
    }

    public String getUrl_image_author() {
        return url_image_author;
    }

    public void setUrl_image_author(String url_image_author) {
        this.url_image_author = url_image_author;
    }

    public String getLikeAmount() {
        return likeAmount;
    }

    public void setLikeAmount(String likeAmount) {
        this.likeAmount = likeAmount;
    }

    public String getRatingAmount() {
        return ratingAmount;
    }

    public void setRatingAmount(String ratingAmount) {
        this.ratingAmount = ratingAmount;
    }


}

