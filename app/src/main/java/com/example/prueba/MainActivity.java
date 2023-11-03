package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.prueba.Models.Permiso;
import com.example.prueba.Models.PermisoAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Permiso> lista = new ArrayList<>();
        lista.add(new Permiso("android.permission.READ_CONTACTS", "Leer contactos"));
        lista.add(new Permiso("android.permission.CALL_PHONE", "Realizar llamadas"));
        lista.add(new Permiso("android.permission.CAMERA", "Acceso a la cámara"));
        PermisoAdapter adapter= new PermisoAdapter(lista,this);
        RecyclerView rc = findViewById(R.id.rcPermiso);
        rc.setAdapter(adapter);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setHasFixedSize(true);
    }
}