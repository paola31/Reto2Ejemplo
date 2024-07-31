
package com.example.logan2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.logo);

        // Cargar la animación
        Animation scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        logo.startAnimation(scaleUp);

        // Configurar el intent para ir a LoginActivity después de la animación
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000); // La duración de la animación es de 2000ms, por lo que el retraso debe ser igual.
    }
}

