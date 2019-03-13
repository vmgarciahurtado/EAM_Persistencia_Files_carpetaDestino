package com.example.victor.files_carpetadestino;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    EditText campoNombre,campoInformacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        campoNombre = findViewById(R.id.campoNombre);
        campoInformacion = findViewById(R.id.campoInfo);
        //requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE}, 100);
    }


    public void onclick(View view) {
        switch (view.getId()){

            case R.id.btnCargar:
                cargar();
                break;

            case R.id.btnGuardar:

                guardar();
                break;

            case R.id.btnSalir:
                finish();
                break;
        }
    }

    private void cargar() {
        String nombreArchivo = campoNombre.getText().toString();
        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),nombreArchivo);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader lecura = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(lecura);
            String linea = br.readLine();
            String todo = "";

            while (linea != null){
                todo = todo + linea + "";
                linea = br.readLine();
            }

            br.close();
            lecura.close();
            campoInformacion.setText(todo);

        }catch (Exception e){

        }

    }

    private void guardar() {
        String nombreArchivo = campoNombre.getText().toString();
        String mensaje = campoInformacion.getText().toString();

        try {
            File file  = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),nombreArchivo);
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));
            writer.write(mensaje);
            writer.flush();
            writer.close();

            Toast.makeText(this, "Datos guardados con exito", Toast.LENGTH_SHORT).show();
            campoNombre.setText(null);
            campoInformacion.setText(null);

        }catch (Exception e){
            Toast.makeText(this, "Error creando el archivo", Toast.LENGTH_SHORT).show();
        }

    }
}
