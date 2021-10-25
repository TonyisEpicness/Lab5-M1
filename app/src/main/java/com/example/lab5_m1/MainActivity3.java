package com.example.lab5_m1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity {
    public DBHelper dbHelper;
    public String content;
    int noteid = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();
        noteid = intent.getIntExtra("noteid", -1);
        if(noteid != -1) {
            Note note = MainActivity2.notes.get(noteid);
            String noteContent = note.getContent();
            EditText editText = (EditText) findViewById(R.id.notes);
            editText.setText(noteContent);
        }
    }

    private void toActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void clickFunction3(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(
                "com.example.lab5_m1", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String title;
        EditText textView2 = (EditText) findViewById(R.id.notes);
        content = textView2.getText().toString();

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);
        dbHelper = new DBHelper(sqLiteDatabase);
        if (noteid == -1) {
            title = "NOTE_" + (MainActivity2.notes.size() + 1);
            dbHelper.saveNotes(date, username, title, content);
        } else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNotes(date, username, title, content);
        }

        toActivity2();
    }
}