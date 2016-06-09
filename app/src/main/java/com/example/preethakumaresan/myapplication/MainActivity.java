package com.example.preethakumaresan.myapplication;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private RelativeLayout myLayout;
    public static final String MyPREFERENCES = "MyPrefs" ;

    Button incrButt;
    private int red;
    private int blue;
    private int green;
    private int count = 0;
    TextView countView;

    Button resetButt;

    private void resetCount(){
        count = 0;
    }



    private void colorSwitch() {

        Random rgbGen = new Random();

        red = rgbGen.nextInt(256);
        green = rgbGen.nextInt(256);
        blue = rgbGen.nextInt(256);

        changeFontColor();

        myLayout.setBackgroundColor(Color.rgb(red,green,blue));

    }

    private void changeFontColor()
    {
        if (((red*299)+(green*587)+(blue*114))/1000>128)
            countView.setTextColor(Color.BLACK);
        else
            countView.setTextColor(Color.WHITE);
    }



    private void incrementCount() {
        countView = (TextView) findViewById(R.id.countView);
        count += 1;
        countView.setText(String.valueOf(count));
    }
   SharedPreferences sharedpreferences;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences shared = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        count = shared.getInt("count",count);

        myLayout = (RelativeLayout) findViewById(R.id.layoutRel);

        myLayout.setBackgroundColor(Color.rgb(red,green,blue));


        countView = (TextView) findViewById(R.id.countView);

        incrButt = (Button) findViewById(R.id.incrButt);
        resetButt = (Button) findViewById(R.id.resetButt);

       
        changeFontColor();

        incrButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorSwitch();
                incrementCount();
            }
        });
        resetButt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                resetCount();
                countView.setText("0");
                myLayout.setBackgroundColor(Color.rgb(red, green, blue));
            }
        });

    }


       @Override
       protected void onResume(){
           super.onResume();
           countView.setText(String.valueOf(count));
       }

       @Override
       protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("count",count);


        }
        
        @Override
       protected void onStop() {
          super.onStop();
           SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
           SharedPreferences.Editor editor = sharedpreferences.edit();
           editor.putInt("count",count);
           editor.apply();
           Toast.makeText(MainActivity.this,"It has been saved!!", Toast.LENGTH_SHORT).show();
           countView.setText(String.valueOf(count));
       }

        @Override
        protected void onRestoreInstanceState(Bundle savedInstanceState){
            super.onRestoreInstanceState(savedInstanceState);
            count= savedInstanceState.getInt("count");
            countView.setText(String.valueOf(count));
        }
}



