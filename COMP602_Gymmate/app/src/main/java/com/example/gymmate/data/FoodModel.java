package com.example.myapplication;
public class FoodModel {

    private int id;
    private String foodname;
    private int calories;
    private String portion;
    private float protein;
    private float carbs;
    private float fat;

    // Constructor
    public FoodModel(int id, String foodname, int calories, String portion, float protein, float carbs, float fat) {
        this.id = id;
        this.foodname = foodname;
        this.calories = calories;
        this.portion = portion;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }

    // Getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getCarbs() {
        return carbs;
    }

    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }


    // toString method
    @Override
    public String toString() {
        return foodname  + ", " +
                calories + " Calories per " +
                portion + " Protein: " +
                protein + "g Carbs: "+
                carbs +"g Fat: " +
                fat + "g ";
    }

}
