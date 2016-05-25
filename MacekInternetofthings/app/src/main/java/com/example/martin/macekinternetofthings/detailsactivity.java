package com.example.martin.macekinternetofthings;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class detailsactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_details);
        final EditText test = (EditText) findViewById(R.id.aut_perioda);
        //Button off = (Button) findViewById(R.id.button3);
        final TextView testtxt = (TextView) findViewById(R.id.textView22);
        final Switch sw2 = (Switch) findViewById(R.id.aut_sw2);

        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    sw2.setText("léto");

                } else {
                    sw2.setText("zima");
                }




            }

        });





    }



}
