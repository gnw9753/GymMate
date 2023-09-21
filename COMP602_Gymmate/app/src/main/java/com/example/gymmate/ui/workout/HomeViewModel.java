package com.example.gymmate.ui.workout;

import androidx.lifecycle.ViewModel;

import com.example.gymmate.data.MyStorage;
import com.example.gymmate.data.WorkoutItem;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {


    public List<WorkoutItem> getItems()
    {
        if(MyStorage.workoutItemList==null)
        {
            MyStorage.workoutItemList=new ArrayList<>();
        }
        if(MyStorage.workoutItemList.size()==0) {


            WorkoutItem item = new WorkoutItem();
            item.setDate("Monday");
            List<String> contents = new ArrayList<>();
            contents.add("Upper");
            item.setContents(contents);
            item.setId(1);
            MyStorage.workoutItemList.add(item);

            WorkoutItem item1 = new WorkoutItem();
            item1.setDate("Tuesday");
            List<String> contents1 = new ArrayList<>();
            contents1.add("Downner");
            item1.setContents(contents1);
            item1.setId(2);
            MyStorage.workoutItemList.add(item1);
        }
        return  MyStorage.workoutItemList;
    }
}