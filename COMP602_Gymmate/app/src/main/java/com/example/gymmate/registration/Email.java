package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class Email extends AppCompatActivity {
    Button btn_previous, btn_next;
    EditText et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        btn_previous = findViewById(R.id.btn_previous);
        btn_next = findViewById(R.id.btn_next);
        et_email = findViewById(R.id.et_email);

        // Initially, disable the "Next" button
        btn_next.setEnabled(false);

        // Add a TextWatcher to the email EditText to listen for changes in text
        et_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Check if the entered text is a valid email format
                boolean isValidEmail = isEmailValid(charSequence.toString());

                // Enable or disable the "Next" button based on email format validity
                btn_next.setEnabled(isValidEmail);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not needed for this implementation
            }
        });

        // button listener
        btn_previous.setOnClickListener(view -> {
            Intent intent = new Intent(Email.this, MainActivity.class);
            startActivity(intent);
        });

        // button listener
        btn_next.setOnClickListener(view -> {
            String email = et_email.getText().toString();
            Intent intent = new Intent(Email.this, Name.class);
            intent.putExtra("email", email);
            startActivity(intent);
        });
    }

    // Method to validate email format using a simple regex pattern
    private boolean isEmailValid(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }
}
