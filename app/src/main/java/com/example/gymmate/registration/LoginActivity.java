package com.example.gymmate.registration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gymmate.MainActivity;
import com.example.gymmate.R;
import com.example.gymmate.data.AppStorage;
import com.example.gymmate.data.DataBaseHelper;
import com.example.gymmate.data.UserModel;

public class LoginActivity extends AppCompatActivity {

    Button btn_sign_in, btn_new_account;
    EditText et_email;
    CheckBox checkBox;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                SharedPreferences.Editor editor1=preferences.edit();
                editor1.putString("userEmail",user.getEmail());
                editor1.apply();
                AppStorage.user=user;
                if (checkBox.isChecked()) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("remember_login", true);
                    editor.putString("user_email", enteredEmail);
                    editor.apply();
                }

                // Pass the user data to the Calories activity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                user=new UserModel(1,"test@test.com","test","male",20,180,90,"goals","20");
                UserModel dbUser=new DataBaseHelper(getApplication()).getUserByEmail(user.getEmail());
                if(dbUser==null)
                {
                    new DataBaseHelper(getApplication()).addOne(user);
                }
                SharedPreferences.Editor editor1=preferences.edit();
                editor1.putString("userEmail",user.getEmail());
                editor1.apply();
                AppStorage.user=user;
                //Toast.makeText(MainActivity.this, "Email not found", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_new_account.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, Email.class); // Move from MainActivity class to Name class
            startActivity(intent);
        });
    }

    private UserModel getUserByEmail(String email) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        return dataBaseHelper.getUserByEmail(email);
    }
}
