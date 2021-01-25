package com.example.sensealert20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

                Intent i = new Intent(this,ProfileInfo.class);
                startActivity(i);
                // Handle the camera action

                //Toast.makeText(getApplicationContext(), "Ahh.. going", Toast.LENGTH_LONG).show();
                return false;

            case R.id.action_two:

                Intent r = new Intent(this,help.class);
                startActivity(r);
                //Toast.makeText(getApplicationContext(), "Ahh.. going", Toast.LENGTH_LONG).show();
                return false;

            case R.id.action_three:

                Intent s = new Intent(this,info.class);
                startActivity(s);
                //Toast.makeText(getApplicationContext(), "Ahh.. going", Toast.LENGTH_LONG).show();
                return false;

            case R.id.action_four:

                Intent d = new Intent(this,Login.class);
                startActivity(d);
                //Toast.makeText(getApplicationContext(), "Ahh.. going", Toast.LENGTH_LONG).show();
                return false;

            case R.id.action_five:

                Intent f = new Intent(this,Connection.class);
                startActivity(f);
               // Toast.makeText(getApplicationContext(), "Ahh.. going", Toast.LENGTH_LONG).show();
                return false;

            default:
                break;
        }

        return false;
    }
}