package com.jcja.fountain_wishes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jcja.fountain_wishes.modelo_3d.cargarlista3d;

public class MainActivity extends AppCompatActivity  {
    private Bundle args = new Bundle();
    private Button select;
    private Integer enviarSeleccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        select = findViewById(R.id.select);
        //select.setOnClickListener(this);
        // cargar una lista en un fragmento
        args.putInt("numero", 1);
        args.putString("nombre", "dato");
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        cargarlista3d listado3d = new cargarlista3d();
        listado3d.setArguments(args);
        ft.replace(R.id.lista3d, listado3d).commit();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // muestra venta emergente de bienvenida
        AlertDialog.Builder alertDialogB = new AlertDialog.Builder(this);
        LayoutInflater inflaterB = getLayoutInflater();
        View dialogViewB = inflaterB.inflate(R.layout.bienvenida, null);
        alertDialogB.setView(dialogViewB);
        alertDialogB.setCancelable(false);
        alertDialogB.setPositiveButton(R.string.empezar, (DialogInterface dialog, int which)->dialog.dismiss());
        alertDialogB.create().show();

        // acción boton siguiente layout
        select.setOnClickListener(v -> {
            if (enviarSeleccion != -1) { // Check if an item is selected
                Intent intent = new Intent(MainActivity.this, Ong.class);
                intent.putExtra("seleccion", enviarSeleccion);
                startActivity(intent);
                finish();
            }
        });
    }
    public void onFinishEditDialog(int seleccion) {
        System.out.println("Has seleccionado: "+ seleccion);
        enviarSeleccion = seleccion;
        //Toast.makeText(getApplicationContext(), "Has seleccionado el: " + seleccion, Toast.LENGTH_LONG).show();
        select.setEnabled(seleccion != -1);
    }
}