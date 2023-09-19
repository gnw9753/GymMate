package com.example.myapplication;


import java.io.Serializable;

public class UserModel implements Serializable {

    private int id;
    private String email;
    private String name;
    private String gender;
    private int age;
    private int height;
    private int weight;
    private String goal;
    private String days;

    // toString method
    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", goal='" + goal + '\'' +
                ", days=" + days +
                '}';
    }

    // Constructor
    public UserModel(int id, String email, String name, String gender, int age, int height, int weight, String goal, String days) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.goal = goal;
        this.days = days;
    }
    public UserModel() {
        // Initialize with default values or leave fields null/empty as needed
        this.email = "";
        this.name = "";
        this.gender = "";
        this.age = 0;
        this.height = 0;
        this.weight = 0;
        this.goal = "";
        this.days = "";
    }
    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

}