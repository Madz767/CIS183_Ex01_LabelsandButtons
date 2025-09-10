package com.example.cis183_ex01_labelsandbuttons;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    //Creating all variables here
    //Global creation
    //you cannot assign references to any GUI element outside of until setContent view has ran
    TextView tv_j_greeting;
    EditText et_j_name;
    Button btn_j_getName;
    TextView tv_j_errorMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //all code goes after setContentView()
        //make the connection between xml GUI elements and java
        tv_j_greeting = findViewById(R.id.tv_v_greeting);
        et_j_name = findViewById(R.id.et_v_name);
        btn_j_getName = findViewById(R.id.btn_v_getGreeting);
        tv_j_errorMessage = findViewById(R.id.tv_v_dataEntryError);

        //setGreetingMessage();
        //call listeners

        setOnClickListenerBegin();
    }

    //============================================================
    //                  ALL LISTENERS
    //============================================================

    public void setOnClickListenerBegin()
    {
        //this is just setting up the listener
        btn_j_getName.setOnClickListener(new View.OnClickListener()
        {
            //this is what we do when the btn_j_getName button was pressed
            @Override
            public void onClick(View v)
            {
                //Log.d("--Debug--","Begin button has been pressed!");
                if(inputValidation())
                {
                    //user entered a valid name
                    String message = createGreetingMessage();
                    setGreetingMessage(message);
                    tv_j_errorMessage.setVisibility(TextView.INVISIBLE);
                    btn_j_getName.setEnabled(false);
                    et_j_name.setEnabled(false);
                }
                else
                {
                    //user did not enter valid name
                    tv_j_errorMessage.setVisibility(TextView.VISIBLE);
                }
                clearNameTextBox();
            }
        });
    }

    public String createGreetingMessage()
    {
        String message = "Welcome ";
        String name = et_j_name.getText().toString();
        message += name;

        return message;
    }

    public void setGreetingMessage(String message)
    {
        //set the greeting message to whatever the user entered
        tv_j_greeting.setVisibility(TextView.VISIBLE);
        tv_j_greeting.setText(message);
    }

    public void clearNameTextBox()
    {
        et_j_name.setText("");
    }

    public boolean inputValidation()
    {
        boolean validUserInput = true;
        String name = et_j_name.getText().toString();

        if(name.isEmpty())
        {
            return false;
        }
        else if(containsNoAlphaNumeric(name))
        {
            //Log.d("ERROR: ","Names should not contain alpha text");
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean containsNoAlphaNumeric(String name)
    {
        String specials = "`~!@#$%^&*()-_=+[{]}|;:,<>.?/1234567890";
        for(int i = 0; i < name.length(); i++)
        {
            for (int j = 0; j < specials.length(); j++)
            {
                if(name.charAt(i)== specials.charAt(j))
                {
                    return true;
                }
            }
        }
        return false;

    }




    //only one greeting per run
}