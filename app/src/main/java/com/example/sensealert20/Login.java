package com.example.sensealert20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    SharedPreferences mPreferences;
    SharedPreferences.Editor mEditor;

    EditText uEmail, uPassword;
    ImageButton Login_button;
    CheckBox mCheckBox;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        uEmail = findViewById(R.id.username);
        uPassword = findViewById(R.id.password);
        Login_button = findViewById(R.id.login_btn2);
        mCheckBox = (CheckBox)findViewById(R.id.remember_login);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        checkSharedPreferences();
        mAuth = FirebaseAuth.getInstance();

        Login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save the checkbox preference
                if (mCheckBox.isChecked()) {
                    //set a checkbox when the application starts
                    mEditor.putString(getString(R.string.checkbox),"True");
                    mEditor.commit();

                    //save email
                    String email = uEmail.getText().toString();
                    mEditor.putString(getString(R.string.email),email);
                    mEditor.commit();

                    //save password
                    String password = uPassword.getText().toString();
                    mEditor.putString(getString(R.string.password),password);
                    mEditor.commit();
                }
                else{
                    //set a checkbox when the application starts
                    mEditor.putString(getString(R.string.checkbox),"False");
                    mEditor.commit();

                    //save email
                    mEditor.putString(getString(R.string.email),"");
                    mEditor.commit();

                    //save password
                    mEditor.putString(getString(R.string.password),"");
                    mEditor.commit();

                }

                // authenticating user
                String email = uEmail.getText().toString().trim();
                String password = uPassword.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Logged in", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Login.this, "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }

    private void checkSharedPreferences(){
        String email = mPreferences.getString(getString(R.string.email),"");
        String password = mPreferences.getString(getString(R.string.password),"");
        String checkbox = mPreferences.getString(getString(R.string.checkbox),"False");

        uEmail.setText(email);
        uPassword.setText(password);

        if(checkbox.equals("True")){
            mCheckBox.setChecked(true);
        }
        else{
            mCheckBox.setChecked(false);
        }
    }

}