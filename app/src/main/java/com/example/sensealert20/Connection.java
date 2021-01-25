package com.example.sensealert20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sensealert20.ui.Member;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Connection extends AppCompatActivity {
EditText Car_Manufacturer, Car_Model, Device_Number;
DatabaseReference myRef;
Member member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        Car_Manufacturer = (EditText)findViewById(R.id.editTextTextPersonName);
        Car_Model = (EditText)findViewById(R.id.editTextTextPersonName2);
        Device_Number = (EditText)findViewById(R.id.editTextTextPersonName3);
        member = new Member();
        myRef = FirebaseDatabase.getInstance().getReference().child("Member");

        getSupportActionBar().hide();

        Button button = findViewById(R.id.save_data_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                member.setManufacturer(Car_Manufacturer.getText().toString().trim());
                member.setModel(Car_Model.getText().toString().trim());
                member.setDeviceNum(Device_Number.getText().toString().trim());
                myRef.push().setValue(member);
                Toast.makeText(Connection.this,"Data saved to RealTimedatabase",Toast.LENGTH_LONG).show();

                //Intent button_btn = new Intent(Connection.this, MainActivity.class);
               // startActivity(button_btn);
            }
        });

        Button connect_btn = findViewById(R.id.button_1);
        connect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent button_btn = new Intent(Connection.this, MainActivity.class);
                startActivity(button_btn);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_top, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_one:

                Intent i = new Intent(this,info.class);
                startActivity(i);
                // Handle the camera action

                Toast.makeText(getApplicationContext(), "Ahh.. going", Toast.LENGTH_LONG).show();
                return false;

            case R.id.action_two:

                Intent r = new Intent(this,help.class);
                startActivity(r);
                Toast.makeText(getApplicationContext(), "Ahh.. going", Toast.LENGTH_LONG).show();
                return false;

            case R.id.action_three:

                Intent s = new Intent(this,info.class);
                startActivity(s);
                Toast.makeText(getApplicationContext(), "Ahh.. going", Toast.LENGTH_LONG).show();
                return false;

            case R.id.action_four:

                Intent d = new Intent(this,Login.class);
                startActivity(d);
                Toast.makeText(getApplicationContext(), "Ahh.. going", Toast.LENGTH_LONG).show();
                return false;

            case R.id.action_five:

                Intent f = new Intent(this,Connection.class);
                startActivity(f);
                Toast.makeText(getApplicationContext(), "Ahh.. going", Toast.LENGTH_LONG).show();
                return false;

            default:
                break;
        }

        return false;
    }
}