
package com.abdul.uottafit_frontpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class ExercisesActivity extends AppCompatActivity {

    CheckBox checkBox1, checkBox2 , checkBox3 , checkBox4 , checkBox5 , checkBox6 , checkBox7 , checkBox8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        String exercises[]= new String[7];

        checkBox1 = (CheckBox) findViewById(R.id.checkBox);
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    Log.d("CheckBox1", "checked");
                } else {
                    Log.d("CheckBox1", "Unchecked");
                }
            }
            });


 //   public void onExercisesButtonClick(View view) {



        }

    }