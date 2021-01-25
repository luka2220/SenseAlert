package com.example.sensealert20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    EditText uEmail, uPassword;
    ImageButton Signup_button;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().hide();

        uEmail = findViewById(R.id.username);
        uPassword = findViewById(R.id.password);
        Signup_button = findViewById(R.id.signup_btn);
        mAuth = FirebaseAuth.getInstance();

     /*   if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), Connection.class));
            finish();
        } */

        Signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = uEmail.getText().toString().trim();
                String password = uPassword.getText().toString().trim();

                // Errors
                if (TextUtils.isEmpty(email)) {
                    uEmail.setError("Email required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    uPassword.setError("Password required");
                    return;
                }

                if (password.length() < 6) {
                    uPassword.setError("Password must be more than 6 characters");
                    return;
                }

                // Registering the user in firebase
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Signup.this, "User created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Connection.class));

                        } else {
                            Toast.makeText(Signup.this, "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });

            }
        });

    }

}

// Old code
//Intent signup_button = new Intent(Signup.this,Connection.class);
// startActivity(signup_button);
