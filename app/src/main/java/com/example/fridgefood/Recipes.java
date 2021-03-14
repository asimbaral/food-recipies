package com.example.fridgefood;

public class Recipes {
    private int time;
    private String summary;
    private String imageURL;
    private String recipeURL;
    public Recipes(int time, String summary, String imageURL, String recipeURL) {
        this.time = time;
        this.summary = summary;
        this.imageURL = imageURL;
        this.recipeURL = recipeURL;
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
