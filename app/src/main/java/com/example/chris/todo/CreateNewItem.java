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
        //default create method
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_item);
        Intent intent = getIntent();

    }

    //button responce method, saves task to file and goes back to main activity
    public void createTask (View view)
    {

        Intent text = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.newTask);
        String message = editText.getText().toString();
        saveMessage(message);
        startActivity(text);
    }

    //method to save tasks to file taskList.txt
    public void saveMessage(String message)
    {
        //adds deliminator
        message = message+"//";
        //tries to find current file
        try{
            //opens an append stream
            FileOutputStream outputStream;
            outputStream = openFileOutput("taskList.txt", Context.MODE_APPEND);
            //writes task
            outputStream.write(message.getBytes());
            outputStream.close();


        }
        //if current file doesn't exist, a new one is created
        catch (IOException e){
            //try to create a new file
            try {
                //opens write stream
                FileOutputStream outputStream;
                outputStream = openFileOutput("taskList.txt", Context.MODE_PRIVATE);
                outputStream.write(message.getBytes());
                outputStream.close();
            }
            catch(IOException ex)
            {
                //todo - add error handling

            }

        }
    }

    }//end of file

