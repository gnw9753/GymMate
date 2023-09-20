package com.example.myapplication;

public class ExerciseModel {
    private String email;
    private String day;
    private String muscle;
    private String name;
    private String level;
    private String part;
    private String type;
    private String modality;
    private String joint;

    // Constructor

    public ExerciseModel(String email, String day, String muscle, String name, String level, String part, String type, String modality, String joint) {
        this.email = email;
        this.day = day;
        this.muscle = muscle;
        this.name = name;
        this.level = level;
        this.part = part;
        this.type = type;
        this.modality = modality;
        this.joint = joint;
    }
    // Getters and setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getJoint() {
        return joint;
    }

    public void setJoint(String joint) {
        this.joint = joint;
    }

    @Override
    public String toString() {
        return "ExerciseModel{" +
                "email='" + email + '\'' +
                ", day='" + day + '\'' +
                ", muscle='" + muscle + '\'' +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", part='" + part + '\'' +
                ", type='" + type + '\'' +
                ", modality='" + modality + '\'' +
                ", joint='" + joint + '\'' +
                '}';
    }
}
