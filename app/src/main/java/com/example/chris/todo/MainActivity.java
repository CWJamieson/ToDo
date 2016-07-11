package com.example.chris.todo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //default oncreate code
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //bottom right button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                create();


            }
        });
        //trys to read in list of tasks from local storage
        try{
            //opens reader stream
            BufferedReader in = new BufferedReader(new InputStreamReader(openFileInput("taskList.txt")));
            String text;
            StringBuffer buf = new StringBuffer();
            //creates a long buffer containing all tasks, deliminated by //
            while((text = in.readLine()) != null)
            {
                buf.append(text+"\n");
            }

            //close in stream
            in.close();
            CheckBox chk;
            String read;
            //tokenize string
            StringTokenizer st = new StringTokenizer(buf.toString(), "//");
            read="";

            //creates untracked checkboxes with found tasks, skips last token because it will
            //always be blank due to how it is deliminated
            if(st.hasMoreElements())
            {
                read = st.nextElement()+"";
                read = read.trim();
            }
            while(st.hasMoreElements())
            {

                    //todo - track checkboxes
                    chk = new CheckBox(this);
                    chk.setText(read);
                    LinearLayout layout = (LinearLayout) findViewById(R.id.main);
                    layout.addView(chk);

                read = st.nextElement()+"";
                read = read.trim();
            }


        }
        //on error create a single checkbox instructing the use to create their own
        catch (Exception e)
        {
            CheckBox chk;
            chk = new CheckBox(this);
            chk.setText("Create a task");
            LinearLayout layout = (LinearLayout) findViewById(R.id.main);
            layout.addView(chk);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //creates a new task, by opening the createNewItem activity
    public void create()
    {
        Intent newItem = new Intent(this, CreateNewItem.class);

        startActivity(newItem);

    }
    //erases the tasklist file removing all saved tasks
    public void clear(View view)
    {
        try {
            FileOutputStream outputStream;
            outputStream = openFileOutput("taskList.txt", Context.MODE_PRIVATE);
            outputStream.close();
            create();
        }
        catch(IOException ex)
        {
            //unprotected catch (todo - add error handling)

        }

    }
}//end of file
