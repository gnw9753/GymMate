package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Confirmation extends AppCompatActivity {
    TextView tv_emailcheck, tv_namecheck, tv_ageCheck, tv_genderCheck, tv_heightcheck, tv_weightcheck, tv_goalcheck, tv_dayscheck;
    Button btn_previous, btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        btn_previous = findViewById(R.id.btn_previous);
        btn_confirm = findViewById(R.id.btn_confirm);
        tv_emailcheck = findViewById(R.id.tv_emailcheck);
        tv_namecheck = findViewById(R.id.tv_namecheck);
        tv_genderCheck = findViewById(R.id.tv_genderCheck);
        tv_ageCheck = findViewById(R.id.tv_ageCheck);
        tv_heightcheck = findViewById(R.id.tv_heightcheck);
        tv_weightcheck = findViewById(R.id.tv_weightcheck);
        tv_goalcheck = findViewById(R.id.tv_goalcheck);
        tv_dayscheck = findViewById(R.id.tv_dayscheck);

        // Retrieve the data from the intent extra
        String email = getIntent().getStringExtra("email");
        if (email != null) {
            tv_emailcheck.setText("Your email: " + email);
        }
        String name = getIntent().getStringExtra("name");
        if (name != null) {
            tv_namecheck.setText("Your name: " + name);
        }
        String gender = getIntent().getStringExtra("gender");
        if (gender != null) {
            tv_genderCheck.setText("Your gender: " + gender);
        }
        String age = getIntent().getStringExtra("age");
        if (age != null) {
            tv_ageCheck.setText("Your age: " + age);
        }
        String height = getIntent().getStringExtra("height");
        if (height != null) {
            tv_heightcheck.setText("Your height: " + height + " cm");
        }
        String weight = getIntent().getStringExtra("weight");
        if (weight != null) {
            tv_weightcheck.setText("Your weight: " + weight + " kg");
        }
        String selectedGoals = getIntent().getStringExtra("selectedGoals");
        if (selectedGoals != null) {
            tv_goalcheck.setText("Your Goal: " + selectedGoals);
        }
        String days = getIntent().getStringExtra("daysList");
        if (days != null) {
                tv_dayscheck.setText("Workout days: " + days);
        }
        String booleandays = getIntent().getStringExtra("daysChecked");

        // button listenersmy
        btn_previous.setOnClickListener(view -> {
            Intent intent = new Intent(Confirmation.this, Days.class);

            intent.putExtra("email", email);
            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("age", age);
            intent.putExtra("height", height);
            intent.putExtra("weight", weight);
            intent.putExtra("selectedGoals", selectedGoals);
            intent.putExtra("daysList", days);

            startActivity(intent);

        });

        btn_confirm.setOnClickListener(view -> {
            try {
                UserModel userModel = new UserModel(-1,
                        email,
                        name,
                        gender,
                        Integer.parseInt(age),
                        Integer.parseInt(height),
                        Integer.parseInt(weight),
                        selectedGoals,
                        booleandays);
                Toast.makeText(Confirmation.this, userModel.toString(), Toast.LENGTH_LONG).show();
                DataBaseHelper dataBaseHelper = new DataBaseHelper(Confirmation.this);

                // Display the result of registration after confirm button is pressed
                if (dataBaseHelper.addOne(userModel)) {
                    Toast.makeText(Confirmation.this, "User successfully added", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Confirmation.this, "Error adding user", Toast.LENGTH_LONG).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(Confirmation.this, "Invalid input data", Toast.LENGTH_LONG).show();
            }

            Intent intent = new Intent(Confirmation.this, Calories.class);
            intent.putExtra("age", age); // Pass the age to the next intent
            intent.putExtra("gender", gender); // Pass the gender to the next intent
            intent.putExtra("weight", weight);  // Pass the weight to the next intent
            intent.putExtra("selectedGoals", selectedGoals); // Pass the goal to the next intent
            startActivity(intent);

        });
    }
}