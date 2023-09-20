package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Name extends AppCompatActivity {

    Button btn_previous, btn_next;
    EditText et_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        btn_previous = findViewById(R.id.btn_previous);
        btn_next = findViewById(R.id.btn_next);
        et_name = findViewById(R.id.et_name);

        // button listeners
        btn_previous.setOnClickListener(view -> {
            Intent intent = new Intent(Name.this, Email.class);
            startActivity(intent);
        });

        btn_next.setOnClickListener(view -> {
            String name = et_name.getText().toString();
            String email = getIntent().getStringExtra("email");
            Intent intent = new Intent(Name.this, Gender.class);
            intent.putExtra("email", email);
            intent.putExtra("name", name);
            startActivity(intent);
        });

    }
}