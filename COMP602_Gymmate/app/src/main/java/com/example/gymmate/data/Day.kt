package com.example.gymmate.data

data class Day (
    val day: String,
    var exerciseList: List<Exercise>,
    var isAvailable: Boolean,
){
}