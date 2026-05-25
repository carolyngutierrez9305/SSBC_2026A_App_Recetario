package com.example.apprecetario_ssbc_2026a;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class PantallaSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_splash);

        //Mostar la pantalla de Splash antes de ir a la pantalla principal
        new Handler(android.os.Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(PantallaSplash.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 3000);

    }
}
