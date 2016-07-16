package com.example.chris.todo;


import android.content.DialogInterface;
import android.view.View;

import java.io.*;
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
import android.widget.CompoundButton;
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
import android.widget.CompoundButton.OnCheckedChangeListener;


//custom listener to keep track of unlimited checkboxes
public class customListener implements OnCheckedChangeListener {

    //global variables
    int checkNum;
    Context c;

    //constructor
    public customListener(int checkNum, Context c){
        this.checkNum = checkNum;
        this.c = c;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {

        try {
            //opens reader stream
            BufferedReader in = new BufferedReader( new InputStreamReader( c.openFileInput("isChecked.txt")));
            String text;
            StringBuffer buf = new StringBuffer();
            text = in.readLine();
            in.close();

            //applies change
            char [] checkList = text.toCharArray();
            if( checkList[checkNum] == '1')
            {
                checkList[checkNum] = '0';
            }
            else
            {
                checkList[checkNum] = '1';
            }

            //writes file
            FileOutputStream outputStream;
            outputStream = c.openFileOutput("isChecked.txt", Context.MODE_PRIVATE);
            text = String.copyValueOf(checkList);
            outputStream.write(text.getBytes());
            outputStream.close();
        }
        catch (Exception e)
        {
            //to-do error handling
        }
    }

}
