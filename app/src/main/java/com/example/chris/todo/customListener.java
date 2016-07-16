package com.example.chris.todo;



import android.content.Context;
import android.widget.CompoundButton;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
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
