package com.example.sensealert20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getSupportActionBar().hide();

        Button login_btn = findViewById(R.id.btnlogin);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login_button = new Intent(StartupActivity.this, Login.class);
                startActivity(login_button);
            }
        });

        Button signup_btn = findViewById(R.id.btnsignup);
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup_button = new Intent(StartupActivity.this,Signup.class);
                startActivity(signup_button);
            }
        });

    }
    }

