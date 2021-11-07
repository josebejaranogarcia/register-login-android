package com.ilerna.pac_uf2_bejaranojose;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Logged extends AppCompatActivity {

    TextView tw_datos;
    TextView tw_saludo;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        tw_datos = findViewById(R.id.extras);
        tw_saludo=findViewById(R.id.greetings);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            UsuarioDTO user = (UsuarioDTO) extras.getSerializable("usuario");
            //  tw_datos.setText(user.toString());
            tw_datos.setText(String.format("%s: %s\n\n%s: %s\n\n%s: %s\n\n%s: %s\n\n%s: %s",
                    getString(R.string.name), user.getNombre(),
                    getString(R.string.surname), user.getApellido(),
                    getString(R.string.user), user.getUsuario(),
                    getString(R.string.password), user.getPass(),
                    getString(R.string.email), user.getEmail()));
            tw_saludo.setText(getString(R.string.hola)+ "\n" + user.getNombre());
        }
    }

    public void reproducir(View v){
        Intent intent=new Intent(Logged.this, Sound.class);
        startService(intent);
    }

    public void detener(View v){
        Intent intent=new Intent(Logged.this, Sound.class);
        stopService(intent);
    }




}


