package com.example.sensealert20;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Make sure this is before calling super.onCreate

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
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
                //Toast.makeText(getApplicationContext(), "Ahh.. going", Toast.LENGTH_LONG).show();
                return false;

                default:
                break;
        }

        return false;
    }

}