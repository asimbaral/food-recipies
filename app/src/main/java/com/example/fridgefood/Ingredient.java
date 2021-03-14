package com.example.fridgefood;

public class Ingredient {
    private String name;
    private int quantity;
    public Ingredient(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
