package com.example.itube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.loginUsernameEditText);
        passwordEditText = findViewById(R.id.loginPasswordEditText);
        loginButton = findViewById(R.id.loginLoginButton);
        signupButton = findViewById(R.id.loginSignupButton);

        DatabaseHelper db = new DatabaseHelper(this);

        loginButton.setOnClickListener(view -> {
            String username, password;
            username = usernameEditText.getText().toString();
            password = passwordEditText.getText().toString();

            if (username.equals("") || password.equals("")) {
                Toast.makeText(this, "Enter username and password", Toast.LENGTH_SHORT).show();
            } else {
                boolean result = db.fetchUser(username, password);
                if (!result) {
                    Toast.makeText(this, "Incorrect login", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, HomeActivity.class);
                    intent.putExtra("user", username);
                    startActivity(intent);
                }
            }
        });

        signupButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        });
    }
}