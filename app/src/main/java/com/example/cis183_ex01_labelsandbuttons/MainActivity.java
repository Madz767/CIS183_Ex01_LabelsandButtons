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
        btn_j_getName = findViewById((R.id.btn_v_getGreeting));

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
                String message = createGreetingMessage();
                setGreetingMessage(message);
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
    //textbox needs to be cleared after button has been pressed
    //only show message if name was entered
    //validate name
    //3 error message:
        //names do not contain numbers/special characters/whitespace characters
        //name was entered
    //only one greeting per run
}