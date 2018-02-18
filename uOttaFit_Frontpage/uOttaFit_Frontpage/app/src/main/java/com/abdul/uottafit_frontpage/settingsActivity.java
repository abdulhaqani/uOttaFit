package com.abdul.uottafit_frontpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class settingsActivity extends AppCompatActivity {

    Spinner dropdown;
    Spinner dropdown2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //get the spinner from the xml.
        dropdown = findViewById(R.id.spinner1);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                Log.d("uOttaHack","dropDown : " + item.toString());
                if (item.toString().equals("Easy")){
                    MainActivity.numReps=10;
                }
                if (item.toString().equals("Medium")){
                    MainActivity.numReps=15;
                }
                if (item.toString().equals("Hard")){
                    MainActivity.numReps=20;

                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        dropdown2 = findViewById(R.id.spinner3);
        dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                Log.d("uOttaHack","dropDown2 : " + item.toString());
                if (item.toString().equals("20 min")){
                    MainActivity.breakTime=20;
                }
                if (item.toString().equals("25 min")){
                    MainActivity.breakTime=25;
                }
                if (item.toString().equals("30 min")){
                    MainActivity.breakTime=30;

                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //create a list of items for the spinner.
        String[] items = new String[]{"Intensity", "Easy", "Medium", "Hard"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
//
//        Spinner dropdown1 = findViewById(R.id.spinner2);
//        //create a list of items for the spinner.
//        String[] items1 = new String[]{"pushups", "situps", "squats", "burpees", "sit-ups", "squat-jumps", "Single-Leg deadlifts"};
//        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
//        //There are multiple variations of this, but this is the basic variant.
//        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
//        //set the spinners adapter to the previously created one.
//        dropdown1.setAdapter(adapter1);

        dropdown2 = findViewById(R.id.spinner3);
        //create a list of items for the spinner.
        String[] items2 = new String[]{"Intenvals", "20 min", "25 min", "30 min"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        //set the spinners adapter to the previously created one.
        dropdown2.setAdapter(adapter2);
    }
    public void onExercisesButtonClick(View view) {

        Log.d("uOttaFit","onExercisesClicked");
        Intent myItent = new Intent(settingsActivity.this,ExercisesActivity.class);
        settingsActivity.this.startActivity(myItent);
    }



}
//
//        Log.d("uOttaFit","onIntensityClicked");
//        Intent myItent = new Intent(settingsActivity.this,IntensityActivity.class);
//        settingsActivity.this.startActivity(myItent);
//    }
//}
