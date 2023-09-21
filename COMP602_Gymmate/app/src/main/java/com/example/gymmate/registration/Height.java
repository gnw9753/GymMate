package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Height extends AppCompatActivity {
    Button btn_previous, btn_next;
    EditText et_height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);

        btn_previous = findViewById(R.id.btn_previous);
        btn_next = findViewById(R.id.btn_next);
        et_height = findViewById(R.id.et_height);

        // button listeners
        btn_previous.setOnClickListener(view -> {
            String enteredHeight = et_height.getText().toString();

            String email = getIntent().getStringExtra("email");
            String name = getIntent().getStringExtra("name");
            String gender = getIntent().getStringExtra("gender");
            String age = getIntent().getStringExtra("age");

            Intent intent = new Intent(Height.this, Age.class);

            intent.putExtra("email", email);
            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("age", age);
            intent.putExtra("height", enteredHeight);

            startActivity(intent);
        });

        btn_next.setOnClickListener(view -> {
            String enteredHeight = et_height.getText().toString();

            String email = getIntent().getStringExtra("email");
            String name = getIntent().getStringExtra("name");
            String gender = getIntent().getStringExtra("gender");
            String age = getIntent().getStringExtra("age");


            Intent intent = new Intent(Height.this, Weight.class);

            intent.putExtra("email", email);
            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("age", age);
            intent.putExtra("height", enteredHeight);

            startActivity(intent);
        });
    }
}