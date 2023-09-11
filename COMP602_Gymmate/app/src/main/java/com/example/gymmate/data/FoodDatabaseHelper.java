package com.example.gymmatekotlin;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class FoodDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "food.db";
    private static final int DATABASE_VERSION = 1;

    public FoodDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String FOOD_TABLE = "FOOD_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FOOD_NAME = "FOOD_NAME";
    public static final String COLUMN_FOOD_CALORIES = "CALORIES";
    public static final String COLUMN_FOOD_PORTION = "PORTION";
    public static final String COLUMN_FOOD_PROTEIN = "PROTEIN";
    public static final String COLUMN_FOOD_CARBS = "CARBS";
    public static final String COLUMN_FOOD_FAT = "FAT";

    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE " +
                    FOOD_TABLE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FOOD_NAME + " TEXT, " +
                    COLUMN_FOOD_CALORIES + " INT, " +
                    COLUMN_FOOD_PORTION + " TEXT, " +
                    COLUMN_FOOD_PROTEIN + " REAL, " +
                    COLUMN_FOOD_CARBS + " REAL, " +
                    COLUMN_FOOD_FAT + " REAL)";

    public List<FoodModel> getEveryfood() {
        List<FoodModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + FOOD_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int foodID = cursor.getInt(0);
                String foodName = cursor.getString(1);
                int foodCalories = cursor.getInt(2);
                String foodPortion = cursor.getString(3);
                float foodProtein = cursor.getFloat(4);
                float foodCarbs = cursor.getFloat(5);
                float foodFat = cursor.getFloat(6);

                FoodModel newFoodModel = new FoodModel(foodID, foodName, foodCalories, foodPortion, foodProtein, foodCarbs, foodFat);
                returnList.add(newFoodModel);

            }

            while (cursor.moveToNext());
        } else {
            // Do nothing
        }

        cursor.close();
        db.close();
        return returnList;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades, if needed.
    }
}
