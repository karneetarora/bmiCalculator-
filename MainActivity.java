package com.example.bmicalculator;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    RadioGroup radioGroup;
    EditText weightText, heightText;
    int idd, weight, height = 0;
    static double BMI;
    String textBMI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView =findViewById(R.id.textViewBMI);
        radioGroup = findViewById(R.id.radioGroup);

        weightText = findViewById(R.id.textWeight);
        heightText = findViewById(R.id.textHeight);

        radioGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idd = radioGroup.getCheckedRadioButtonId();
                if(idd == R.id.rbEnglish) {
                    weightText.setHint("Enter Weight in Pounds");
                    heightText.setHint("Enter Height in Inches");
                }
                else {
                    weightText.setHint("Enter Weight in Kilograms");
                    heightText.setHint("Enter Height in Meters");
                }

            }
        });
    }

    /**
     * Changes the hints to pounds/inches or kilograms/meters.
     * @param view Selected button in radio group.
     */
    public void radioClicked(View view) {
        // change everything to whatever was selected: "hint"
        // take it into consideration when calculating

        if(idd == R.id.rbEnglish) {
            weightText.setHint("Enter Weight in Pounds");
            heightText.setHint("Enter Height in Inches");
        }
        else {
            weightText.setHint("Enter Weight in Kilograms");
            heightText.setHint("Enter Height in Meters");
        }
    }

    /**
     * Calculates and displays BMI in the textview.
     * @param view Clicked button "CALCULATE BMI"
     */
    public void showBMI(View view) {
        weight = Integer.valueOf(weightText.getText().toString());
        height = Integer.valueOf(heightText.getText().toString());
        if(weight != 0 && height != 0) {
            if (idd == R.id.rbEnglish)
                BMI = (weight * 703) / (height * height);
            else
                BMI = (weight) / (height * height);
            textBMI = (String.format("%.2f", BMI));
            textView.setText(textBMI);
        }
        else{
            if(weightText.getText().equals(null) || weight == 0 )
                Toast.makeText(getApplicationContext(), "Please enter a Weight", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Please enter a Height", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Transition to action that shows advice according to the calculated BMI.
     * @param view Clicked button "GET ADVICE"
     */
    public void showAdvice(View view) {
        if(BMI > 0) {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("passBMI", textBMI);
            startActivity(intent);
            finish();
        }
    }


}

