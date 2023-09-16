package com.example.gymmate.data;

import androidx.lifecycle.LiveData;

import java.io.Serializable;
import java.util.List;

public class WorkoutItem extends LiveData<WorkoutItem> implements Serializable {

    int id;
    String date;

    List<String> contents;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }
}
