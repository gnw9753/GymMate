package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Days extends AppCompatActivity {

    CheckBox cb_monday, cb_tuesday, cb_wednesday, cb_thursday, cb_friday, cb_saturday, cb_sunday;
    boolean[] daysChecked;
    List<String> checkedDaysList;
    Button btn_previous, btn_next;

    private void updateCheckedDaysList(String day, boolean isChecked) {
        if (isChecked) {
            checkedDaysList.add(day);
        } else {
            checkedDaysList.remove(day);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);
        cb_monday = findViewById(R.id.cb_monday);
        cb_tuesday = findViewById(R.id.cb_tuesday);
        cb_wednesday = findViewById(R.id.cb_wednesday);
        cb_thursday = findViewById(R.id.cb_thursday);
        cb_friday = findViewById(R.id.cb_friday);
        cb_saturday = findViewById(R.id.cb_saturday);
        cb_sunday = findViewById(R.id.cb_sunday);
        btn_previous = findViewById(R.id.btn_previous);
        btn_next = findViewById(R.id.btn_next);

        // Initialize the boolean array for days
        daysChecked = new boolean[7];
        checkedDaysList = new ArrayList<>();

        // Set up CheckBox listeners to update the boolean array
        cb_monday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            daysChecked[0] = isChecked;
            updateCheckedDaysList("Monday", isChecked);
        });
        cb_tuesday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            daysChecked[1] = isChecked;
            updateCheckedDaysList("Tuesday", isChecked);
        });
        cb_wednesday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            daysChecked[2] = isChecked;
            updateCheckedDaysList("Wednesday", isChecked);
        });
        cb_thursday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            daysChecked[3] = isChecked;
            updateCheckedDaysList("Thursday", isChecked);
        });
        cb_friday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            daysChecked[4] = isChecked;
            updateCheckedDaysList("Friday", isChecked);
        });
        cb_saturday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            daysChecked[5] = isChecked;
            updateCheckedDaysList("Saturday", isChecked);
        });
        cb_sunday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            daysChecked[6] = isChecked;
            updateCheckedDaysList("Sunday", isChecked);
        });


        btn_previous.setOnClickListener(view -> {
            String enteredDays = Arrays.toString(daysChecked);

            String email = getIntent().getStringExtra("email");
            String name = getIntent().getStringExtra("name");
            String gender = getIntent().getStringExtra("gender");
            String age = getIntent().getStringExtra("age");
            String height = getIntent().getStringExtra("height");
            String weight = getIntent().getStringExtra("weight");
            String selectedGoals = getIntent().getStringExtra("selectedGoals");

            Intent intent = new Intent(Days.this, Goal.class);

            intent.putExtra("email", email);
            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("age", age);
            intent.putExtra("height", height);
            intent.putExtra("weight", weight);
            intent.putExtra("selectedGoals", selectedGoals);
            intent.putExtra("daysChecked", enteredDays); // Include the selected days

            startActivity(intent);
        });

        btn_next.setOnClickListener(view -> {
            String enteredDays = Arrays.toString(daysChecked);
            String daysList = checkedDaysList.toString();

            String email = getIntent().getStringExtra("email");
            String name = getIntent().getStringExtra("name");
            String gender = getIntent().getStringExtra("gender");
            String age = getIntent().getStringExtra("age");
            String height = getIntent().getStringExtra("height");
            String weight = getIntent().getStringExtra("weight");
            String selectedGoals = getIntent().getStringExtra("selectedGoals");

            Intent intent = new Intent(Days.this, Confirmation.class);

            intent.putExtra("email", email);
            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("age", age);
            intent.putExtra("height", height);
            intent.putExtra("weight", weight);
            intent.putExtra("selectedGoals", selectedGoals);
            intent.putExtra("daysChecked", enteredDays);
            intent.putExtra("daysList", daysList);

            startActivity(intent);
        });



    }
}