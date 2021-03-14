package com.example.fridgefood;

public class Recipes {
    private int time;
    private String summary;
    private String imageURL;
    private String recipeURL;
    private String name;
    public Recipes(int time, String summary, String imageURL, String recipeURL, String name) {
        this.name = name;
        this.time = time;
        this.summary = summary;
        this.imageURL = imageURL;
        this.recipeURL = recipeURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getRecipeURL() {
        return recipeURL;
    }

    public void setRecipeURL(String recipeURL) {
        this.recipeURL = recipeURL;
    }

    @Override
    public String toString() {
        return "Recipes{" +
                "time=" + time +
                ", summary='" + summary + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", recipeURL='" + recipeURL + '\'' +
                '}';
    }
}
