package com.example.sharedpreference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView lblUsuario;
    TextView lblContrasena;
    EditText txtUsuario;
    EditText txtContrasena;
    Button btnGuardar;
    Button btnCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtContrasena = (EditText) findViewById(R.id.txtContrasena);
        lblUsuario = (TextView) findViewById(R.id.lblUsuario);
        lblContrasena = (TextView) findViewById(R.id.lblContrasena);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnCargar = (Button) findViewById(R.id.btnCargar);


        cargarPreferencias();
    }

    public void onClick(View view){

        switch(view.getId()){

            case R.id.btnGuardar:
                guardarPreference();
                break;

            case R.id.btnCargar:
                Intent intent = new Intent(this,ConsultaPreferences.class);
                startActivity(intent);
                break;
        }

    }

    private void cargarPreferencias(){

        //Leer archivo
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        String user = preferences.getString("user","No existe la informacion");
        String cont = preferences.getString("pass","No existe la informacion");

        lblUsuario.setText("" +user);
        lblContrasena.setText(""+ cont);
    }


    private void guardarPreference(){

        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE); //Creo preferencias

        String usuario = txtUsuario.getText().toString();
        String contrasena = txtContrasena.getText().toString();

        //Editar el archivo
        SharedPreferences.Editor editor = preferences.edit(); //Editar sharedPreference
        editor.putString("user",usuario);
        editor.putString("pass",contrasena);

        lblUsuario.setText(usuario);
        lblContrasena.setText(contrasena);
        editor.commit();

        //Leer el archivo
        //cargarPreferencias();
    }
}
