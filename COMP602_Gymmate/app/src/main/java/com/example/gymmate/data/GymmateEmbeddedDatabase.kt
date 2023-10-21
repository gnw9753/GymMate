package com.example.gymmate.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gymmate.data.exercisedata.Exercise
import com.example.gymmate.data.exercisedata.ExerciseDao
import com.example.gymmate.data.fooddata.FoodConsumptionDao
import com.example.gymmate.data.fooddata.FoodConsumptionEntity
import com.example.gymmate.data.logindata.LoginEntity
import com.example.gymmate.data.logindata.LoginEntityDao
import com.example.gymmate.data.userdata.UserEntity
import com.example.gymmate.data.userdata.UserEntityDao
import com.example.gymmate.data.weightdata.WeightDao
import com.example.gymmate.data.weightdata.WeightEntity

@Database(
    entities = [Exercise::class, UserEntity::class, LoginEntity::class, FoodConsumptionEntity::class, WeightEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GymmateEmbeddedDatabase : RoomDatabase(){
        abstract fun exerciseDao(): ExerciseDao
        abstract fun userEntityDao(): UserEntityDao
        abstract fun loginEntityDao(): LoginEntityDao
        abstract fun foodConsumptionDao(): FoodConsumptionDao
        abstract fun weightDao(): WeightDao

        companion object {
            @Volatile
            private var Instance: GymmateEmbeddedDatabase? = null
            fun getDatabase(context: Context): GymmateEmbeddedDatabase {
                return Instance ?: synchronized(this) {
                    Room.databaseBuilder(context, GymmateEmbeddedDatabase::class.java, "gymmate_database")
                        .build()
                        .also {
                            Instance = it
                        }
                }
            }
        }
}