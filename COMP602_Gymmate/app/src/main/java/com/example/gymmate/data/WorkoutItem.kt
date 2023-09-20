package com.example.gymmate.data

import androidx.lifecycle.LiveData
import java.io.Serializable
import java.util.List

class WorkoutItem : LiveData<WorkoutItem?>(),
    Serializable {
    var id = 0
    var date: String? = null
    var contents: List<String>? = null
}