package com.example.gymmate.data

data class Exercise (
    val exerciseName: String,
    val difficulty: Int,
    val isPush: Boolean,
    val isPull: Boolean,
    val modality: Int,
    val joint: Int,
    val muscle: String,
){
    private val setAndRepRange: String = when(difficulty){
        1 -> SetAndRepRange().beginnerSet
        2 -> SetAndRepRange().intermediateSet
        3 -> SetAndRepRange().advancedSet
        else -> SetAndRepRange().untilFailure
    }

    override fun toString(): String {
        return "$exerciseName | $setAndRepRange"
    }

}