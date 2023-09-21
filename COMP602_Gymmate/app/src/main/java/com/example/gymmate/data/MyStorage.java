package com.example.gymmate.data;

import java.util.ArrayList;
import java.util.List;

public class MyStorage {

    public static UserInfo userInfo;

    public static List<WorkoutItem> workoutItemList;

    public static List<FoodItem> foodItems;

    public static void addWorkout(String date,String item)
    {
        if(workoutItemList==null)
            workoutItemList=new ArrayList<>();
        boolean isIn=false;
        for(WorkoutItem obj:workoutItemList)
        {
            if(obj.date.equals(date))
            {
                isIn=true;
                obj.contents.add(item);
                break;
            }
        }
        if(!isIn)
        {
            WorkoutItem obj=new WorkoutItem();
            obj.date=date;
            obj.id=5;
            obj.contents=new ArrayList<>();
            obj.contents.add(item);
            workoutItemList.add(obj);
        }
    }
}
