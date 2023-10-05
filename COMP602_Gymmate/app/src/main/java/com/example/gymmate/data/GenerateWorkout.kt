package com.example.gymmate.data

import com.example.gymmate.data.exercisedata.Exercise
import com.example.gymmate.data.exercisedata.ExerciseDay
import com.example.gymmate.data.userdata.User

class GenerateWorkout(var userId: Int, var goal: String, var daysAvailable: List<Boolean>, val exerciseList: List<List<String>>){

    val dayString =
        listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    // List for all muscle groups
    val abdominalList: MutableList<Exercise> = mutableListOf()
    val backList: MutableList<Exercise> = mutableListOf()
    val bicepList: MutableList<Exercise> = mutableListOf()
    val calveList: MutableList<Exercise> = mutableListOf()
    val chestList: MutableList<Exercise> = mutableListOf()
    val legList: MutableList<Exercise> = mutableListOf()
    val lowerBackList: MutableList<Exercise> = mutableListOf()
    val shoulderList: MutableList<Exercise> = mutableListOf()
    val tricepList: MutableList<Exercise> = mutableListOf()

    val muscleGroupList = listOf(
        abdominalList, // push
        backList, // pull
        bicepList, // pull
        calveList, // leg
        chestList, // push
        legList, // leg
        lowerBackList, // pull
        shoulderList, // push
        tricepList // push
    )

    val pullDayList = listOf(
        muscleGroupList[1], // back
        muscleGroupList[2], // bicep
        muscleGroupList[0] // abs
    )

    val legDayList = listOf(
        muscleGroupList[5], // legs
        muscleGroupList[3], // calves
        muscleGroupList[0], // abs
    )

    val pushDayVariation1List = listOf(
        muscleGroupList[4], // chest
        muscleGroupList[7],  // shoulder
        muscleGroupList[8], // tricep
    )

    val pushDayVariation2List = listOf(
        muscleGroupList[8],  // tricep
        muscleGroupList[4], // chest
        muscleGroupList[7], // shoulder
    )

    val workoutPriority = listOf(
        pullDayList,
        legDayList,
        pushDayVariation1List,
        pushDayVariation2List
    )

    // Hardcoded numbers because the exercise csv will never change
    val muscleGroupIndex = listOf(35, 62, 100, 100, 147, 200, 207, 231, 269)

    // Main function

    fun generateWorkout() :List<ExerciseDay>{
        // Puts all the exercises into a list
        toList()
        // Calculate the amount of days the user will be working out
        var amountDays: Int = amountDaysAvailable()

        // The weekly exercise schedule
        var exerciseDayList: MutableList<ExerciseDay> = mutableListOf()
        var count = 0
        var secondCounter = 0

        // Loops 7 times representing each day of the week
        // Gets the day in string
        // Checks if available
        // Runs the function required to generate a workout for that day
        while (count < 7) {
            // Temp values needed to initialize
            val tempDay = dayString[count]
            val tempIsAvailable: Boolean = daysAvailable[count]
            var tempExerciseList: MutableList<Exercise> = mutableListOf()


            if (tempIsAvailable) {
                addExerciseToDay(
                    tempDay,
                    tempExerciseList,
                    workoutPriority[count % 4],
                    goal
                )
            }
            exerciseDayList.add(ExerciseDay(tempDay, tempExerciseList, tempIsAvailable))
            count++
        }
        return exerciseDayList.toList()
    }

    // Sub functions
    fun amountDaysAvailable(): Int {
        var count = 0
        for (days in daysAvailable) {
            if (days) {
                count++
            }
        }
        return count
    }

    private fun toList() {
        var muscleIndex = 0
        var listIndex = 0

        for (muscleList in muscleGroupList) {
            var index = 0
            while (listIndex <= exerciseList.size) {
                muscleList.add(stringToExercise(exerciseList[listIndex]))

                if (listIndex == muscleGroupIndex[muscleIndex]) {
                    muscleIndex++
                    break
                }
                listIndex++
            }
        }
    }

    private fun stringToExercise(exerciseString: List<String>): Exercise {
        // Sorry for my crime, it must be done to initialize the exercise object
        return (Exercise(
            id = userId,
            "",
            exerciseString[0],
            exerciseString[1],
            exerciseString[2],
            exerciseString[3],
            exerciseString[4],
            exerciseString[5],
            exerciseString[6]

        ))
    }


    fun addExerciseToDay(
        day: String,
        exerciseDayList: MutableList<Exercise>,
        muscleListToAdd: List<MutableList<Exercise>>,
        difficulty: String
    ) {
        if (difficulty.equals("gain muscle", ignoreCase = true)) {
            exerciseDayList.addAll(
                addExercise(
                    day,
                    muscleListToAdd[0],
                    2,
                    "Intermediate"
                )
            )
            exerciseDayList.addAll(addExercise(day, muscleListToAdd[0], 1, "Advanced"))
            exerciseDayList.addAll(
                addExercise(
                    day,
                    muscleListToAdd[1],
                    2,
                    "Intermediate"
                )
            )
            exerciseDayList.addAll(addExercise(day, muscleListToAdd[2], 1, "Beginner"))


        } else if (difficulty.equals("lose weight", ignoreCase = true)) {
            exerciseDayList.addAll(addExercise(day, muscleListToAdd[0], 2, "Beginner"))
            exerciseDayList.addAll(
                addExercise(
                    day,
                    muscleListToAdd[0],
                    2,
                    "Intermediate"
                )
            )
            exerciseDayList.addAll(addExercise(day, muscleListToAdd[1], 2, "Beginner"))
            exerciseDayList.addAll(addExercise(day, muscleListToAdd[2], 1, "Beginner"))
        }
    }

    private fun addExercise(
        day: String,
        muscleList: MutableList<Exercise>,
        amountAdd: Int,
        difficulty: String
    ): MutableList<Exercise> {
        var count = 0
        var tempReturnList: MutableList<Exercise> = mutableListOf()
        var tempList: MutableList<Exercise> = mutableListOf()
        tempList = sortTempList(tempList, muscleList, difficulty)

        while (count < amountAdd) {
            var randomElement: Exercise
            if (tempList.isNotEmpty()) {
                randomElement = tempList.random()
                randomElement.day = day
                tempReturnList.add(randomElement)
            } else {
                randomElement = muscleList.random()
                randomElement.day = day
                tempReturnList.add(randomElement)
            }
            count++
        }
        return tempReturnList
    }

    private fun sortTempList(
        tempList: MutableList<Exercise>,
        muscleList: MutableList<Exercise>,
        difficulty: String
    ): MutableList<Exercise> {
        for (muscle in muscleList) {
            if (muscle.difficulty.equals(difficulty, ignoreCase = true)) {
                tempList.add(muscle)
            }
        }
        return tempList
    }

    fun printOut() {
        for (muscleGroup in muscleGroupList) {
            for (exercise in muscleGroup) {
                println(exercise)
            }
        }
    }


}