package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Age extends AppCompatActivity {
    Button btn_previous, btn_next;
    EditText et_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);

        btn_previous = findViewById(R.id.btn_previous);
        btn_next = findViewById(R.id.btn_next);
        et_age = findViewById(R.id.et_age);

        // button listeners
        btn_previous.setOnClickListener(view -> {
            String enteredAge = et_age.getText().toString();

            String email = getIntent().getStringExtra("email");
            String name = getIntent().getStringExtra("name");
            String gender = getIntent().getStringExtra("gender");

            Intent intent = new Intent(Age.this, Gender.class);

            intent.putExtra("email", email);
            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("age", enteredAge);

            startActivity(intent);
        });

        btn_next.setOnClickListener(view -> {
            String enteredAge = et_age.getText().toString();

            String email = getIntent().getStringExtra("email");
            String name = getIntent().getStringExtra("name");
            String gender = getIntent().getStringExtra("gender");

            Intent intent = new Intent(Age.this, Height.class);

            intent.putExtra("email", email);
            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("age", enteredAge);

            startActivity(intent);
        });
    }
}