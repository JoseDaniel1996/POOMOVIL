package com.example.actividad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText txtNombre;
    private EditText txtClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btEnviar(View view){ //Creamos el Intent
       Intent intent = new Intent(MainActivity.this, Contactos.class);
        startActivity(intent);
        }
}
