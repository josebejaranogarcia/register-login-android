package com.ilerna.pac_uf2_bejaranojose;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class Development extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_development);
    }

    /**
     * Metodos internos de control y pruebas en desarrollo
     * No tiene funcionalidad en la aplicacion
     */
    public void verTodos(View V){
        DatabaseDAO db= new DatabaseDAO(this, "pac_db", null, 1);
        db.showAll();
    }
    public void borrar(View V){
        DatabaseDAO db= new DatabaseDAO(this, "pac_db", null, 1);
        db.delete();
    }
}
