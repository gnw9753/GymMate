package com.example.gymmate.data

import java.util.ArrayList
import java.util.List

object MyStorage {
    var userInfo: UserInfo? = null
    var workoutItemList: List<WorkoutItem>? = null
    var foodItems: List<FoodItem>? = null
    fun addWorkout(date: String, item: String?) {
        if (workoutItemList == null) workoutItemList = ArrayList()
        var isIn = false
        for (obj in workoutItemList) {
            if (obj.date.equals(date)) {
                isIn = true
                obj.contents.add(item)
                break
            }
        }
        if (!isIn) {
            val obj = WorkoutItem()
            obj.date = date
            obj.id = 5
            obj.contents = ArrayList()
            obj.contents.add(item)
            workoutItemList.add(obj)
        }
    }
}