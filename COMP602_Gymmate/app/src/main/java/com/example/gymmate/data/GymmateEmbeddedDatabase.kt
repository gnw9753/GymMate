package com.example.gymmate.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gymmate.data.exercisedata.Exercise
import com.example.gymmate.data.exercisedata.ExerciseDao

@Database(
    entities = [Exercise::class],
    version = 1,
    exportSchema = false
)
abstract class GymmateEmbeddedDatabase : RoomDatabase(){
        abstract fun exerciseDao(): ExerciseDao

        companion object {
            @Volatile
            private var Instance: GymmateEmbeddedDatabase? = null
            fun getDatabase(context: Context): GymmateEmbeddedDatabase {
                return Instance ?: synchronized(this) {
                    Room.databaseBuilder(context, GymmateEmbeddedDatabase::class.java, "exercise_database")
                        .build()
                        .also {
                            Instance = it
                        }
                }
            }
        }

}