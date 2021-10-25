package com.example.lab5_m1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainActivity2 extends AppCompatActivity {
    public static ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String s = "";
        SharedPreferences sharedPreferences = getSharedPreferences(
                "com.example.lab5_m1", Context.MODE_PRIVATE);
        TextView textView2 = (TextView) findViewById(R.id.welcomeText);
        Intent intent = getIntent();

        if(!sharedPreferences.getString("username", "").equals("")) {
            s = sharedPreferences.getString("username", "");
        }else {
            s = intent.getStringExtra("message");
        }
        textView2.setText("Welcome " + s + "!");

        ////////////////////////////////////////////////////

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        MainActivity2.notes = dbHelper.readNotes(s);

        ArrayList<String> displayNotes = new ArrayList<>();
        for (Note note: notes) {
            displayNotes.add(String.format("Title:%s\nDate:%s", note.getTitle(), note.getDate()));
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
        ListView listView = (ListView) findViewById(R.id.noteList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                intent.putExtra("noteid", position);
                startActivity(intent);
            }
        });


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
                toActivity3(-1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void toActivity1() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void toActivity3(int noteid) {
        Intent intent = new Intent(this, MainActivity3.class);
        intent.putExtra("noteid", noteid);
        startActivity(intent);
    }
}