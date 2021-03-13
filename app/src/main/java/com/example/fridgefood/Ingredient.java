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
}
