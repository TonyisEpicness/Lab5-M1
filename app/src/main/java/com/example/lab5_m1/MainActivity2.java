package com.example.lab5_m1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SharedPreferences sharedPreferences = getSharedPreferences(
                "com.example.lab5_m1", Context.MODE_PRIVATE);
        TextView textView2 = (TextView) findViewById(R.id.welcomeText);
        Intent intent = getIntent();
        String s = "";
        if(!sharedPreferences.getString("username", "").equals("")) {
            s = sharedPreferences.getString("username", "");
        }else {
            s = intent.getStringExtra("message");
        }
        textView2.setText("Welcome " + s + "!");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                SharedPreferences sharedPreferences = getSharedPreferences(
                        "com.example.lab5_m1", Context.MODE_PRIVATE);
                sharedPreferences.edit().remove("username").apply();
                toActivity1();
                return true;
            case R.id.addNote:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void toActivity1() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}