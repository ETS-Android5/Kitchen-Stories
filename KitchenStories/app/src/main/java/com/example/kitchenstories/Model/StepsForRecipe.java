package com.example.kitchenstories.Model;

public class StepsForRecipe {

    private String step;
    private String url_image;
    private String ingredientsForPerStep;
    private String utensilsForPerStep;
    private String scriptForDescription;

    public StepsForRecipe() {
    }

    public StepsForRecipe(String step,
                          String url_image,
                          String ingredientsForPerStep,
                          String utensilsForPerStep,
                          String scriptForDescription) {
        this.step = step;
        this.url_image = url_image;
        this.ingredientsForPerStep = ingredientsForPerStep;
        this.utensilsForPerStep = utensilsForPerStep;
        this.scriptForDescription = scriptForDescription;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getIngredientsForPerStep() {
        return ingredientsForPerStep;
    }

    public void setIngredientsForPerStep(String ingredientsForPerStep) {
        this.ingredientsForPerStep = ingredientsForPerStep;
    }

    public String getUtensilsForPerStep() {
        return utensilsForPerStep;
    }

    public void setUtensilsForPerStep(String utensilsForPerStep) {
        this.utensilsForPerStep = utensilsForPerStep;
    }

    public String getScriptForDescription() {
        return scriptForDescription;
    }

    public void setScriptForDescription(String scriptForDescription) {
        this.scriptForDescription = scriptForDescription;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}
