package com.example.logan2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class activity_category extends AppCompatActivity {

    private ImageButton buttonWater, buttonElectricity, buttonDisplayData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        buttonWater = findViewById(R.id.buttonWater);
        buttonElectricity = findViewById(R.id.buttonElectricity);
        buttonDisplayData = findViewById(R.id.buttonDisplayData);

        buttonWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_category.this, activity_water_data_entry.class);
                startActivity(intent);
            }
        });

        buttonElectricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_category.this, ElectricityDataEntryActivity.class);
                startActivity(intent);
            }
        });

        buttonDisplayData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_category.this, DisplayDataActivity.class);
                startActivity(intent);
            }
        });
    }
}
