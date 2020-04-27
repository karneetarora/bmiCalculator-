package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

public class SecondActivity extends AppCompatActivity {

    static TextView display;
    String bmi;
   static ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        display = findViewById(R.id.textView3);
        imageView = findViewById(R.id.imageView);

        bmi = getIntent().getExtras().getString("passBMI");
        display(bmi);
    }

    public static void display(String bmi){
        double userBMI = Double.valueOf(bmi);

        if(userBMI < 18.5) {
            display.setText("You are: UnderWeight");
            imageView.setImageResource(R.drawable.underweight);
        }else if( userBMI >= 18.5 && userBMI <=24.9) {
            display.setText("You are: Normal");
            imageView.setImageResource(R.drawable.normal);
        }else if( userBMI>= 25 && userBMI <=29.9) {
            display.setText("You are: Overweight");
            imageView.setImageResource(R.drawable.over);
        }else {
            display.setText("You are: Obese");
            imageView.setImageResource(R.drawable.obese);
        }
    }


}
