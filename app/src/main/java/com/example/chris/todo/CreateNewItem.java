package com.example.chris.todo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class CreateNewItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_item);
        Intent intent = getIntent();

    }

    public void createTask (View view)
    {
        Intent text = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.newTask);
        String message = editText.getText().toString();
        saveMessage(message);
        startActivity(text);
    }
    public void saveMessage(String message)
    {
        message = message+"//";
        try{


            FileOutputStream outputStream;
            outputStream = openFileOutput("taskList.txt", Context.MODE_APPEND);

            outputStream.write(message.getBytes());
            outputStream.close();


        }
        catch (IOException e){
            try {
                FileOutputStream outputStream;
                outputStream = openFileOutput("taskList.txt", Context.MODE_PRIVATE);
                outputStream.write(message.getBytes());
                outputStream.close();
            }
            catch(IOException ex)
            {


            }

        }
    }

    }

