package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignIn extends AppCompatActivity {

    private Button btn_sign_in, btn_new_account;
    private EditText et_email;
    private CheckBox checkBox;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        btn_sign_in = findViewById(R.id.btn_sign_in);
        btn_new_account = findViewById(R.id.btn_new_account);
        et_email = findViewById(R.id.et_email);
        checkBox = findViewById(R.id.checkBox);

        preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);

        // Check if the user has previously checked the CheckBox
        boolean rememberLogin = preferences.getBoolean("remember_login", false);
        if (rememberLogin) {
            String savedEmail = preferences.getString("user_email", "");
            if (!savedEmail.isEmpty()) {
                et_email.setText(savedEmail);
                checkBox.setChecked(true);
            }
        }

        // Button listeners
        btn_sign_in.setOnClickListener(view -> {
            // Get the entered email from the EditText
            String enteredEmail = et_email.getText().toString().trim();

            // Check if the email exists in the database
            UserModel user = getUserByEmail(enteredEmail);

            if (user != null) {
                // If a matching user is found, remember the login if CheckBox is checked
                if (checkBox.isChecked()) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("remember_login", true);
                    editor.putString("user_email", enteredEmail);
                    editor.apply();
                }

                // Pass the user data to the Calories activity
                Intent intent = new Intent(SignIn.this, Calories.class);
                intent.putExtra("user", user);
                startActivity(intent);
            } else {
                Toast.makeText(SignIn.this, "Email not found", Toast.LENGTH_SHORT).show();
            }
        });

        btn_new_account.setOnClickListener(view -> {
            Intent intent = new Intent(SignIn.this, Email.class); // Move from Signin class to Name class
            startActivity(intent);
        });
    }

    private UserModel getUserByEmail(String email) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        return dataBaseHelper.getUserByEmail(email);
    }
}
