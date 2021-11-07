package com.ilerna.pac_uf2_bejaranojose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Clase que establece el comportamiento de activity_login
 *   y de sus vistas
 *
 * @author Jose F Bejarano
 * @version 1.0
 * @since 2020
 */
public class Login extends AppCompatActivity {

    String user, pass;
    Button btn_signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_signIn = findViewById(R.id.signIn);
        btn_signIn.setOnClickListener(this::signin);
    }

    public void signin(View v) {
        user= ((EditText) findViewById(R.id.user2)).getText().toString();
        pass= ((EditText) findViewById(R.id.contrasenia2)).getText().toString();
        //Comprobamos que los campos no estan vacios
        if(!user.isEmpty() && !pass.isEmpty()) {
                DatabaseDAO db= new DatabaseDAO(this, "pac_db", null, 1);
                UsuarioDTO user_dto = db.login(user, pass) ;
                //Si user_dto viene null, es que el usuario o la contraseña no son correctos
                if(user_dto!=null) {
                    //Llamamos a la siguiente actividad porque el logueo fue correcto
                        Toast.makeText(this, getString(R.string.logExito), Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(Login.this, Logged.class);
                        //Para poder enviar el objeto  user_dto, la clasde UsuarioNuevo debe  implementar Serializable
                        intent.putExtra("usuario", user_dto);
                        startActivity(intent);
                }else
                         Toast.makeText(this, getString(R.string.noValido), Toast.LENGTH_LONG).show();
        }else
                Toast.makeText(this, getString(R.string.rellenarCampos), Toast.LENGTH_LONG).show();
    }
    public void goDevelopment(View v) { startActivity(new Intent(Login.this, Development.class)); }
}