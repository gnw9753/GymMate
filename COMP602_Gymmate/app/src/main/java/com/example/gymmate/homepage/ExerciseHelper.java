package com.example.gymmate.homepage;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;

import com.example.gymmate.data.DataBaseHelper;
import com.example.gymmate.data.ExerciseModel;

import java.util.List;

public class ExerciseHelper {


    public List<ExerciseModel> getData(Context context)
    {
        String email=context.getSharedPreferences("user_prefs", MODE_PRIVATE).getString("userEmail","test@test.com");
        List<ExerciseModel> list=new DataBaseHelper(context).getExerciseListByEmail(email);
        if(list==null|| list.size()==0)
        {
            //list1.add("yoga");
            //list1.add("Pilates");
            //list1.add("Push-up");
            //list1.add("Sit-up");
            //list1.add("Aerobics");
            //list1.add("Rope skipping");
            ExerciseModel model=new ExerciseModel(email,"Monday","musle","Push-up","high","arm","type one","at gym","arm");
            new DataBaseHelper(context).addOne(model);
            model=new ExerciseModel(email,"Thuesday","musle","Rope skipping","middle","leg","type one","at gym","leg");
            new DataBaseHelper(context).addOne(model);
            list=new DataBaseHelper(context).getExerciseListByEmail(email);
        }
        else
        {

        }
        return list;
    }
}
