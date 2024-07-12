package com.jcja.fountain_wishes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.jcja.fountain_wishes.app.MainSesion;
import com.jcja.fountain_wishes.ongs.CargarlistaONG;

import java.util.HashMap;

public class Ong extends AppCompatActivity {

    private Button next;
    private Integer enviarSeleccion;
    private MainSesion inicilite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.ong);
        next = findViewById(R.id.selectdos);
        ImageView imagenback = findViewById(R.id.imagenback);

        ImageView imagenmenu = findViewById(R.id.imagenmenu);
        inicilite = new MainSesion(getApplicationContext());
        HashMap<String, String> user = inicilite.getInitDetails();
        enviarSeleccion = Integer.valueOf(user.get("selectONG"));
        next.setEnabled(enviarSeleccion != -1);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        CargarlistaONG listadoOng = new CargarlistaONG();
        ft.replace(R.id.fragmentong, listadoOng).commit();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainong), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imagenback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainactivity = new Intent(Ong.this, MainActivity.class);
                startActivity(mainactivity);
                finish();
            }
        });
        // acción boton siguiente layout
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ong = new Intent(Ong.this, Ong.class);
                ong.putExtra("seleccion3D", "Valor3D");
                ong.putExtra("seleccionONG", "ValorONG");
                startActivity(ong);
                finish();
            }
        });
    }
    public void onFinishEditDialog(int seleccion) {
        System.out.println("Has seleccionado: "+ seleccion);
        enviarSeleccion = seleccion;
        //Toast.makeText(getApplicationContext(), "Has seleccionado el: " + seleccion, Toast.LENGTH_LONG).show();
        next.setEnabled(seleccion != -1);
    }
}
