package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    static TextView display;
    String bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        display = findViewById(R.id.textView3);

        bmi = getIntent().getExtras().getString("passBMI");
        display(bmi);
    }

    public static void display(String bmi){
        double userBMI = Double.valueOf(bmi);

        if(userBMI < 18.5) {
            display.setText("UnderWeight");
        }else if( userBMI >= 18.5 && userBMI <=24.9) {
            display.setText("Normal");
        }else if( userBMI>= 25 && userBMI <=29.9) {
            display.setText("Overweight");
        }else {
            display.setText("Obese");
        }
    }
}
