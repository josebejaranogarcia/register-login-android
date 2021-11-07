package com.ilerna.pac_uf2_bejaranojose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    String nombre ,apellido, usuario, pass, email;;
  //  Button btn_signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
      //  btn_signUp = findViewById(R.id.signUp);
        //btn_signUp.setOnClickListener(this::signup);
    }

    /**
     * Metodo que se encarga de validar los campos y de realizar, en caso de que odo est'e correcto, el registro en la BD
     *
     * @info  El listener esta directamente en activity_registro.xml
     * android:onClick="signup"
     */
    public void signup(View v) {
        DatabaseDAO db = new DatabaseDAO(this, "pac_db", null, 1);
        //Se recoge lo que hay en los campos cada vez que llamamos al mto
        nombre= ((EditText)findViewById(R.id.nombre)).getText().toString();
        apellido= ((EditText)findViewById(R.id.apellido)).getText().toString();
        usuario= ((EditText)findViewById(R.id.usuario)).getText().toString();
        pass= ((EditText)findViewById(R.id.contrasenia)).getText().toString();
        email= ((EditText)findViewById(R.id.email)).getText().toString();

    //**********************  Validaciones de datos y registro  ***********************
    if(nombre.isEmpty() ||
        apellido.isEmpty()||
                usuario.isEmpty()||
                pass.isEmpty()||
                email.isEmpty())
        Toast.makeText(this, getString(R.string.rellenarCampos), Toast.LENGTH_SHORT).show();
  else
        if(!email.matches("^[\\-_A-Za-z0-9]{2,20}+(\\.[_A-Za-z0-9-]{2,20}+)*@[A-Za-z0-9-]{2,20}+\\.[A-Za-z]{2,5}+$"))
                Toast.makeText(this, getString(R.string.email_no_valido), Toast.LENGTH_SHORT).show();
        else if(!nombre.matches("^\\w{4,15}+")|| !apellido.matches("\\w{4,20}")||!usuario.matches("\\w{4,20}"))
                Toast.makeText(this, getString(R.string.check_fields), Toast.LENGTH_LONG).show();
        else if(!pass.matches("^[\\w\\W]{4,15}+"))
                Toast.makeText(this, getString(R.string.check_pass), Toast.LENGTH_SHORT).show();
        else if (db.insertData(nombre, apellido, usuario, pass, email)) {
                Toast.makeText(this, getString(R.string.regExito), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Registro.this, Login.class));
        } else
                Toast.makeText(this, getString(R.string.userExists), Toast.LENGTH_SHORT).show();
    }
}
