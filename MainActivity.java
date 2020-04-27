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
    RadioGroup rg;
    RadioButton rb;
    EditText weightText, heightText;
    int weight, height = 0;
    static double BMI;
    String textBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            weightText.setText(savedInstanceState.getString("savedWeight"));
            heightText.setText(savedInstanceState.getString("savedHeight"));
            textView.setText(savedInstanceState.getString("savedBMI"));
        }
    else {
            setContentView(R.layout.activity_main);
            textView = findViewById(R.id.textViewBMI);
            rg = findViewById(R.id.radioGroup);

            weightText = findViewById(R.id.textWeight);
            heightText = findViewById(R.id.textHeight);

        }
    }

    /**
     * Changes the hints to pounds/inches or kilograms/meters.
     * @param view Selected button in radio group.
     */
    public void rbClick(View view) {

        int radiobuttonid = rg.getCheckedRadioButtonId();
        rb = findViewById(radiobuttonid);
        // Toast.makeText(getBaseContext(), rb.getText(), Toast.LENGTH_LONG).show();
        if(R.id.rbEnglish == rb.getId()){
            weightText.setHint("Enter weight in pounds");
            heightText.setHint("Enter height in inches");
        }else{
            weightText.setHint("Enter weight in kilograms");
            heightText.setHint("Enter height in meters");

        }

    }

    /**
     * Calculates and displays BMI in the textview.
     * @param view Clicked button "CALCULATE BMI"
     */
    public void showBMI(View view) {
        String msg;
        
        String weightString = "" + weightText.getText().toString();
        weightString = weightString.replaceFirst("^0+(?!$)", "");

        String heightString = "" + heightText.getText().toString();
        heightString = heightString.replaceFirst("^0+(?!$)", "");

        if((weightString.equals("") || weightString.equals("0"))  &&
                ( heightString.equals("") ||  heightString.equals("0") ) ){
            msg = "Please enter a weight and height";
            Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();

        }else if(weightString.equals("") || weightString.equals("0")){
            msg = "Please enter a weight";
            Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();

        }else if(heightString.equals("") || heightString.equals("0") ){
            msg = "Please enter a height";
            Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
        }else{
            weight = Integer.valueOf(weightText.getText().toString());
            height = Integer.valueOf(heightText.getText().toString());
            if (R.id.rbEnglish == rb.getId())
                BMI = (weight * 703) / (height * height);
            else
                BMI = (weight) / (height * height);
            textBMI = (String.format("%.2f", BMI));
            textView.setText(textBMI);
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


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("savedWeight", weightText.getText().toString());
        savedInstanceState.putString("savedHeight", heightText.getText().toString());
        savedInstanceState.putString("savedBMI", textBMI);
        super.onSaveInstanceState(savedInstanceState);
    }

}

