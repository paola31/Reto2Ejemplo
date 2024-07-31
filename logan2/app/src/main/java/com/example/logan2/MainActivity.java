package com.example.logan2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textViewDate;
    private ImageButton buttonWater, buttonElectricity, buttonDisplayData, buttonDailyTips;

    private String[] dailyTips = {
            "Ahorra energía apagando las luces que no necesitas.",
            "Desconecta los electrodomésticos que no estás usando.",
            "Utiliza bombillas LED para reducir el consumo de energía.",
            "Apaga tu computadora cuando no la estés usando.",
            "Aprovecha la luz natural durante el día.",
            "Mantén el refrigerador bien cerrado para ahorrar energía.",
            "Usa el aire acondicionado con moderación.",
            "Lava tu ropa con agua fría para ahorrar energía.",
            "Revisa tus aparatos eléctricos para evitar fugas de energía.",
            "Plancha tu ropa en una sola sesión para ahorrar energía."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDate = findViewById(R.id.textViewDate);
        buttonWater = findViewById(R.id.buttonWater);
        buttonElectricity = findViewById(R.id.buttonElectricity);
        buttonDisplayData = findViewById(R.id.buttonDisplayData);
        buttonDailyTips = findViewById(R.id.buttonDailyTips);

        // Inicializar los SharedPreferences
        SharedPreferences preferences = getSharedPreferences("DailyTips", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Verificar si los consejos ya están guardados
        boolean isInitialized = preferences.getBoolean("isInitialized", false);

        if (!isInitialized) {
            // Guardar los consejos en SharedPreferences
            for (int i = 0; i < dailyTips.length; i++) {
                editor.putString("tip_" + i, dailyTips[i]);
            }
            editor.putBoolean("isInitialized", true);
            editor.apply();
        }

        // Obtener la fecha actual en español de Colombia
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy", new Locale("es", "CO"));
        String currentDate = sdf.format(new Date());
        textViewDate.setText(currentDate);

        buttonWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_water_data_entry.class);
                startActivity(intent);
            }
        });

        buttonElectricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ElectricityDataEntryActivity.class);
                startActivity(intent);
            }
        });

        buttonDisplayData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayDataActivity.class);
                startActivity(intent);
            }
        });

        buttonDailyTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la fecha guardada en SharedPreferences
                String savedDate = preferences.getString("savedDate", "");

                // Comparar la fecha actual con la guardada
                if (!currentDate.equals(savedDate)) {
                    // Si es un nuevo día, guardar un nuevo consejo
                    Random random = new Random();
                    int randomIndex = random.nextInt(10);
                    String newTip = dailyTips[randomIndex];
                    editor.putString("currentTip", newTip);
                    editor.putString("savedDate", currentDate);
                    editor.apply();
                }

                Intent intent = new Intent(MainActivity.this, DailyTipsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_item1) {
            Intent intent = new Intent(MainActivity.this, StockOMaticAdventures.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_item2) {
            Intent intent = new Intent(MainActivity.this, activity_category.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_logout) {
            // Restablecer la fecha guardada para generar un nuevo consejo al iniciar sesión de nuevo
            SharedPreferences preferences = getSharedPreferences("DailyTips", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("savedDate", "");
            editor.apply();

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}


