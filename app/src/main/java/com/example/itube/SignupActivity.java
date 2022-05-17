package com.example.itube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText fullNameEditText;
    EditText usernameEditText;
    EditText passwordEditText;
    EditText confirmPasswordEditText;
    Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullNameEditText = findViewById(R.id.signupFullNameEditText);
        usernameEditText = findViewById(R.id.signupUsernameEditText);
        passwordEditText = findViewById(R.id.signupPasswordEditText);
        confirmPasswordEditText = findViewById(R.id.signupConfirmPasswordEditText);
        createAccountButton = findViewById(R.id.signupCreateAccountButton);

        DatabaseHelper db = new DatabaseHelper(this);

        createAccountButton.setOnClickListener(view -> {
            String fullName, username, password, confirmPassword;
            fullName = fullNameEditText.getText().toString();
            username = usernameEditText.getText().toString();
            password = passwordEditText.getText().toString();
            confirmPassword = confirmPasswordEditText.getText().toString();

            if (fullName.equals("") || username.equals("") || password.equals("") || confirmPassword.equals("")) {
                Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                User user = new User(username, password, fullName);
                long result = db.insertUser(user);
                if (result >-1) {
                    Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Signup failed, error: " + result, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}