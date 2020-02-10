package com.example.loancalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText carText;
    private EditText downText;
    private EditText aprText;
    private EditText monthText;
    private SeekBar loanBar;
    private TextView loanLabel;
    private RadioButton radioLoan;
    private RadioButton radioLease;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carText = findViewById(R.id.carText);
        downText = findViewById(R.id.downText);
        aprText = findViewById(R.id.aprText);
        loanLabel = findViewById(R.id.loanLabel);
        loanBar = findViewById(R.id.loanBar);
        monthText = findViewById(R.id.monthText);
        radioLoan = findViewById(R.id.radioLoan);
        radioLease = findViewById(R.id.radioLease);

        loanBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                loanLabel.setText("Loan length: " + progress + " month(s)");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }

    public void calcMonth(View v){

        String costInput = carText.getText().toString();
        String downInput = downText.getText().toString();
        String aprInput = aprText.getText().toString();

        double costVal = Double.parseDouble(costInput);
        double downVal = Double.parseDouble(downInput);
        double aprVal = Double.parseDouble(aprInput);

        int loanLength = loanBar.getProgress();

        aprVal = (aprVal/12)/100;

        if(radioLoan.isChecked()){

            costVal = costVal - downVal;
            double loanExp = Math.pow(1+aprVal,-loanLength);

            double monthVal = ((aprVal)*costVal)/(1- loanExp);

            monthText.setText(String.format("%.2f",monthVal));

        } else if(radioLease.isChecked()){
            costVal = (costVal/3)-downVal;
            loanLength = 36;
            loanLabel.setText("Lease length: 36 months");
            double loanExp = Math.pow(1+aprVal,-loanLength);

            double monthVal = ((aprVal)*costVal)/(1- loanExp);

            monthText.setText(String.format("%.2f",monthVal));

        } else{
            monthText.setText("Select Loan or Lease");

        }

    }

}
