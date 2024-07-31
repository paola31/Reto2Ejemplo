package com.example.logan2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ElectricityDataEntryActivity extends AppCompatActivity {

    private EditText editTextKilowatt, editTextMonth, editTextPrice;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity_data_entry);

        editTextKilowatt = findViewById(R.id.editTextKilowatt);
        editTextMonth = findViewById(R.id.editTextMonth);
        editTextPrice = findViewById(R.id.editTextPrice);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(v -> saveElectricityData());
    }

    private void saveElectricityData() {
        String kilowatt = editTextKilowatt.getText().toString();
        String month = editTextMonth.getText().toString();
        String price = editTextPrice.getText().toString();

        if (kilowatt.isEmpty() || month.isEmpty() || price.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Guardar los datos en SharedPreferences
        SharedPreferences preferences = getSharedPreferences("ElectricityData", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        int index = preferences.getInt("index", 0);
        editor.putString("kilowatt_" + index, kilowatt);
        editor.putString("month_" + index, month);
        editor.putString("price_" + index, price);
        editor.putInt("index", index + 1);
        editor.apply();

        Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show();
        finish();
    }
}

