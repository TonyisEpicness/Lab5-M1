package com.example.lab5_m1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public void clickFunction(View view) {
        EditText username = (EditText) findViewById(R.id.usernameField);
        String s = username.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences(
                "com.example.lab5_m1", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", s).apply();
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("message", s);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(
                "com.example.lab5_m1", Context.MODE_PRIVATE);
        if(!sharedPreferences.getString("username", "").equals("")) {
            Intent intent = new Intent(this, MainActivity2.class);
            intent.putExtra("message", sharedPreferences.getString("username", ""));
            startActivity(intent);
        }else {
            setContentView(R.layout.activity_main);
        }
    }

}