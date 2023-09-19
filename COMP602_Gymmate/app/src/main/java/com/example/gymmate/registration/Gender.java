package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Gender extends AppCompatActivity {

    TextView tv_gender;
    Button btn_male, btn_female, btn_previous, btn_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        tv_gender = findViewById(R.id.tv_gender);
        btn_male = findViewById(R.id.btn_male);
        btn_female = findViewById(R.id.btn_female);
        btn_previous = findViewById(R.id.btn_previous);
        btn_next = findViewById(R.id.btn_next);

        // button listeners
        btn_male.setOnClickListener(view ->{
            tv_gender.setText("Male");
        });

        btn_female.setOnClickListener(view ->{
            tv_gender.setText("Female");
        });

        btn_previous.setOnClickListener(view -> {
            String enteredGender = tv_gender.getText().toString();

            String email = getIntent().getStringExtra("email");
            String name = getIntent().getStringExtra("name");

            Intent intent = new Intent(Gender.this, MainActivity.class);

            intent.putExtra("email", email);
            intent.putExtra("name", name);
            intent.putExtra("gender", enteredGender);

            startActivity(intent);
        });

        btn_next.setOnClickListener(view -> {
            String enteredGender = tv_gender.getText().toString();
            String email = getIntent().getStringExtra("email");
            String name = getIntent().getStringExtra("name");

            Intent intent = new Intent(Gender.this, Age.class);

            intent.putExtra("email", email);
            intent.putExtra("name", name);
            intent.putExtra("gender", enteredGender);

            startActivity(intent);
        });
    }
}