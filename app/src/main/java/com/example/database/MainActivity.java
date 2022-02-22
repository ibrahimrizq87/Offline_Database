package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    helper myDb;
    EditText name;
ListView list;
    EditText supname;
    EditText marks;
    EditText ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new helper(this);
        marks = findViewById(R.id.txt2);
        ID = findViewById(R.id.txt4);
        name = findViewById(R.id.txt);
        supname = findViewById(R.id.txt3);
        list = findViewById(R.id.list);

    }

    public void click(View view) {

        String Name = name.getText().toString();
        String Supname = supname.getText().toString();
        String markes = marks.getText().toString();

        boolean result = myDb.insertData(Name,Supname,markes);
    if (result == true ){
        Toast.makeText(MainActivity.this,"OK, data saved",Toast.LENGTH_LONG).show();
    name.setText("");
        supname.setText("");
        marks.setText("");
        ShowData();
    }else {
        Toast.makeText(MainActivity.this,"NO",Toast.LENGTH_LONG).show();

    }
    }
public void ShowData(){
    ArrayList <String> Data = myDb.getAllrecord();
    ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,Data);
    list.setAdapter(arrayAdapter);
}

    public void click2(View view) {

        String Name = name.getText().toString();
        String Supname = supname.getText().toString();
        String markes = marks.getText().toString();
        String id = ID.getText().toString();

        boolean result = myDb.UPdateData(id,Name,Supname,markes);
        if (result == true ){
            Toast.makeText(MainActivity.this,"OK, data updated",Toast.LENGTH_LONG).show();
            name.setText("");
            supname.setText("");
            marks.setText("");
            ID.setText("");
            ShowData();
        }else {
            Toast.makeText(MainActivity.this,"NO",Toast.LENGTH_LONG).show();

        }
    }

    public void click3(View view) {
        String id = ID.getText().toString();
        Integer result =myDb.delet(id);
        if (result > 0 ){
            Toast.makeText(MainActivity.this,"OK delated",Toast.LENGTH_LONG).show();
            ShowData();
        }else{
            Toast.makeText(MainActivity.this,"NO",Toast.LENGTH_LONG).show();
        }
    }
}
