package com.example.sensealert20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sensealert20.ui.Profile;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileInfo extends AppCompatActivity {
    EditText First_name, Last_name, Phone_number;
    CheckBox Child, Pet, None;

    DatabaseReference myRef;
    Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);

        First_name = findViewById(R.id.editTextFirstName);
        Last_name = findViewById(R.id.editTextLastName);
        Phone_number = findViewById(R.id.editTextPhoneNumber);
        Child = findViewById(R.id.checkBoxChild);
        Pet = findViewById(R.id.checkBoxPet);
        None = findViewById(R.id.checkBoxNone);

        profile = new Profile();
        myRef = FirebaseDatabase.getInstance().getReference().child("Profile");

        ImageButton button = findViewById(R.id.submit_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile.setFirstName(First_name.getText().toString().trim());
                profile.setLastName(Last_name.getText().toString().trim());
                profile.setPhoneNumber(Phone_number.getText().toString().trim());

                if (Child.isChecked()) {
                    profile.setUseofApp("User has child or infant");
                } else if (Pet.isChecked()) {
                    profile.setUseofApp("User has pet");
                } else if (None.isChecked()) {
                    profile.setUseofApp("User has undisclosed use of app");
                }

                myRef.push().setValue(profile);
                Toast.makeText(ProfileInfo.this,"Profile Information saved",Toast.LENGTH_LONG).show();
            }
        });

        ImageButton close_button = findViewById(R.id.close_btn);
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileInfo.this, MainActivity.class));
            }
        });
    }
}






























