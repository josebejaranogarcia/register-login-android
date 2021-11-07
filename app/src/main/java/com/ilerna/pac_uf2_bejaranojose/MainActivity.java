package com.ilerna.pac_uf2_bejaranojose;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 *Clase que establece el comportamiento del MainActivity
 * y de sus vistas
 *
 * @author Jose F. Bejarano Garcia
 *  @apiNote  Java 8
 * @version 1.0
 * @since 2020
  */

public class MainActivity extends AppCompatActivity {

    Button btn_login, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Botones y escuchadores
        btn_login=findViewById(R.id.login);
        btn_login.setOnClickListener(this::loguearse);
        /*
        También se podía llamar a la función loguearse mediante una expresión lamba (a partir del JDK 8):
        btn_login.setOnClickListener( v ->  {  loguearse(v); })
        Es más fácil hacerlo en el XML con android:OnClick="nombre_del_mto"
         */
        btn_register=findViewById(R.id.register);
        btn_register.setOnClickListener(this::register);
    }

    public  void loguearse(View v){
        //Creamos el intent que nos llevara a la Activity del login y lo iniciamos
        Intent intent= new Intent(MainActivity.this, Login.class);
        startActivity(intent);
    }

    public  void register(View v){
        Intent intent=new Intent(MainActivity.this, Registro.class);
        startActivity(intent);
    }

}
