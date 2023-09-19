package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    //  User table
    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_USER_EMAIL = "USER_EMAIL";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_GENDER = "USER_GENDER";
    public static final String COLUMN_USER_AGE = "USER_AGE";
    public static final String COLUMN_USER_HEIGHT = "USER_HEIGHT";
    public static final String COLUMN_USER_WEIGHT = "USER_WEIGHT";
    public static final String COLUMN_USER_GOAL = "USER_GOAL";
    public static final String COLUMN_USER_DAYS = "USER_DAYS";
    private static final String CREATE_USER_TABLE_QUERY =
            "CREATE TABLE IF NOT EXISTS " +
                    USER_TABLE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USER_EMAIL + " TEXT UNIQUE, " +
                    COLUMN_USER_NAME + " TEXT, " +
                    COLUMN_USER_GENDER + " TEXT, " +
                    COLUMN_USER_AGE + " INT, " +
                    COLUMN_USER_HEIGHT + " INT, " +
                    COLUMN_USER_WEIGHT + " REAL, " +
                    COLUMN_USER_GOAL + " TEXT, " +
                    COLUMN_USER_DAYS + " TEXT)";
    //  Food table
    public static final String FOOD_TABLE = "FOOD_TABLE";
    public static final String COLUMN_FOOD_ID = "ID";
    public static final String COLUMN_FOOD_NAME = "FOOD_NAME";
    public static final String COLUMN_FOOD_CALORIES = "CALORIES";
    public static final String COLUMN_FOOD_PORTION = "PORTION";
    public static final String COLUMN_FOOD_PROTEIN = "PROTEIN";
    public static final String COLUMN_FOOD_CARBS = "CARBS";
    public static final String COLUMN_FOOD_FAT = "FAT";
    private static final String CREATE_FOOD_TABLE_QUERY =
            "CREATE TABLE IF NOT EXISTS " +
                    FOOD_TABLE + " (" +
                    COLUMN_FOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FOOD_NAME + " TEXT, " +
                    COLUMN_FOOD_CALORIES + " INT, " +
                    COLUMN_FOOD_PORTION + " TEXT, " +
                    COLUMN_FOOD_PROTEIN + " REAL, " +
                    COLUMN_FOOD_CARBS + " REAL, " +
                    COLUMN_FOOD_FAT + " REAL)";
    //    Exercise table
    public static final String EXERCISE_TABLE = "EXERCISE_TABLE";
    public static final String COLUMN_EXERCISE_ID = "ID";
    public static final String COLUMN_EXERCISE_EMAIL = "USER_EMAIL";
    public static final String COLUMN_EXERCISE_DAY = "EXERCISE_DAY";
    public static final String COLUMN_EXERCISE_MUSCLE = "EXERCISE_MUSCLE";
    public static final String COLUMN_EXERCISE_NAME = "EXERCISE_NAME";
    public static final String COLUMN_EXERCISE_LEVEL = "EXERCISE_LEVEL";
    public static final String COLUMN_EXERCISE_PART = "EXERCISE_PART";
    public static final String COLUMN_EXERCISE_TYPE = "EXERCISE_TYPE";
    public static final String COLUMN_EXERCISE_MODALITY = "USER_DAYS";
    public static final String COLUMN_EXERCISE_JOINT = "EXERCISE_JOINT";
    private static final String CREATE_EXERCISE_TABLE_QUERY =
            "CREATE TABLE IF NOT EXISTS " +
                    EXERCISE_TABLE + " (" +
                    COLUMN_EXERCISE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EXERCISE_EMAIL + " TEXT, " +
                    COLUMN_EXERCISE_DAY + " TEXT, " +
                    COLUMN_EXERCISE_MUSCLE + " TEXT, " +
                    COLUMN_EXERCISE_NAME + " TEXT, " +
                    COLUMN_EXERCISE_LEVEL + " TEXT, " +
                    COLUMN_EXERCISE_PART + " TEXT, " +
                    COLUMN_EXERCISE_TYPE + " TEXT, " +
                    COLUMN_EXERCISE_MODALITY + " TEXT, " +
                    COLUMN_EXERCISE_JOINT + " TEXT)";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "gymmate.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE_QUERY);
        db.execSQL(CREATE_FOOD_TABLE_QUERY);
        db.execSQL(CREATE_EXERCISE_TABLE_QUERY);
    }


    // this is called if the database version number changes. It prevents previous users app from breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // Add user information into DB
    public boolean addOne(UserModel userModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_EMAIL, userModel.getEmail());
        cv.put(COLUMN_USER_NAME, userModel.getName());
        cv.put(COLUMN_USER_GENDER, userModel.getGender());
        cv.put(COLUMN_USER_AGE, userModel.getAge());
        cv.put(COLUMN_USER_HEIGHT, userModel.getHeight());
        cv.put(COLUMN_USER_WEIGHT, userModel.getWeight());
        cv.put(COLUMN_USER_GOAL, userModel.getGoal());
        cv.put(COLUMN_USER_DAYS, userModel.getDays());

        long insert = db.insert(USER_TABLE, null, cv);
        return insert != -1;
    }

    public UserModel getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        UserModel user = null;

        // Define the columns you want to retrieve
        String[] projection = {
                COLUMN_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_GENDER,
                COLUMN_USER_AGE,
                COLUMN_USER_HEIGHT,
                COLUMN_USER_WEIGHT,
                COLUMN_USER_GOAL,
                COLUMN_USER_DAYS
        };

        // Define the selection criteria (WHERE clause)
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};

        // Perform the query to retrieve the user by email
        Cursor cursor = db.query(
                USER_TABLE,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            // If a matching user is found, create a UserModel object
            user = new UserModel(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_EMAIL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_GENDER)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_AGE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_HEIGHT)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_WEIGHT)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_GOAL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_DAYS))
            );
        }

        cursor.close();
        return user; // Return the UserModel or null if no matching user is found
    }

    public boolean updateUserWeight(String email, int newWeight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_WEIGHT, newWeight);

        // Define the WHERE clause to update the user with the matching email
        String whereClause = COLUMN_USER_EMAIL + " = ?";
        String[] whereArgs = {email};

        int rowsUpdated = db.update(USER_TABLE, values, whereClause, whereArgs);
        return rowsUpdated > 0;
    }

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
    public List<ExerciseModel> getExerciseListByEmail(String email) {
        List<ExerciseModel> exerciseList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        // Define the columns you want to retrieve
        String[] projection = {
                COLUMN_EXERCISE_EMAIL,
                COLUMN_EXERCISE_DAY,
                COLUMN_EXERCISE_MUSCLE,
                COLUMN_EXERCISE_NAME,
                COLUMN_EXERCISE_LEVEL,
                COLUMN_EXERCISE_PART,
                COLUMN_EXERCISE_TYPE,
                COLUMN_EXERCISE_MODALITY,
                COLUMN_EXERCISE_JOINT
        };

        // Define the selection criteria (WHERE clause)
        String selection = COLUMN_EXERCISE_EMAIL + " = ?";
        String[] selectionArgs = {email};

        // Perform the query to retrieve exercises by email
        Cursor cursor = db.query(
                EXERCISE_TABLE,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                String exerciseEmail = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_EMAIL));
                String day = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_DAY));
                String muscle = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_MUSCLE));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_NAME));
                String level = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_LEVEL));
                String part = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_PART));
                String type = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_TYPE));
                String modality = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_MODALITY));
                String joint = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_JOINT));

                ExerciseModel exerciseModel = new ExerciseModel(exerciseEmail, day, muscle, name, level, part, type, modality, joint);
                exerciseList.add(exerciseModel);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return exerciseList;
    }
}