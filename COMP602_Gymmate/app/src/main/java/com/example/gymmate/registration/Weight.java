package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Weight extends AppCompatActivity {
    Button btn_previous, btn_next;
    EditText et_weight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        btn_previous = findViewById(R.id. btn_previous);
        btn_next = findViewById(R.id.btn_next);
        et_weight = findViewById(R.id.et_weight);

        // button listeners
        btn_previous.setOnClickListener(view -> {
            String enteredWeight = et_weight.getText().toString();

            String email = getIntent().getStringExtra("email");
            String name = getIntent().getStringExtra("name");
            String gender = getIntent().getStringExtra("gender");
            String age = getIntent().getStringExtra("age");
            String height = getIntent().getStringExtra("height");

            Intent intent = new Intent(Weight.this, Height.class);

            intent.putExtra("email", email);
            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("age", age);
            intent.putExtra("height", height);
            intent.putExtra("weight", enteredWeight);

            startActivity(intent);
        });

        btn_next.setOnClickListener(view -> {
            String enteredWeight = et_weight.getText().toString();

            String email = getIntent().getStringExtra("email");
            String name = getIntent().getStringExtra("name");
            String gender = getIntent().getStringExtra("gender");
            String age = getIntent().getStringExtra("age");
            String height = getIntent().getStringExtra("height");

            Intent intent = new Intent(Weight.this, Goal.class);

            intent.putExtra("email", email);
            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("age", age);
            intent.putExtra("height", height);
            intent.putExtra("weight", enteredWeight);

            startActivity(intent);
        });
    }
}