package com.jcja.fountain_wishes;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.jcja.fountain_wishes.app.LanguageManager;
import com.jcja.fountain_wishes.app.MainSesion;
import com.jcja.fountain_wishes.app.NetStatus;
import com.jcja.fountain_wishes.modelo_3d.Cargarlista3d;

import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private boolean doubleBackToExitPressedOnce = false;
    private Button select;
    private Integer enviarSeleccion;
    private MainSesion inicilite;
    HashMap<String, String> user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        select = findViewById(R.id.select);



        LanguageManager.translateTextOnScreen(this, R.id.select, "es");


        ImageView imagenback = findViewById(R.id.imagenback);
        imagenback.setVisibility(View.GONE);
        ImageView imagenmenu = findViewById(R.id.imagenmenu);
        inicilite = new MainSesion(getApplicationContext());
        inicilite.outInit();
        if (!inicilite.isInitIn()) {
            // muestra venta emergente de bienvenida
            AlertDialog.Builder alertDialogB = new AlertDialog.Builder(this);
            LayoutInflater inflaterB = getLayoutInflater();
            View dialogViewB = inflaterB.inflate(R.layout.bienvenida, null);
            alertDialogB.setCancelable(false);
            alertDialogB.setView(dialogViewB);
            alertDialogB.setPositiveButton(R.string.empezar,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inicilite.setInit(true, NetStatus.isNetworkAvailable(getApplicationContext()), -1,null, -1, null);
                            dialog.dismiss();
                            inicializar();
                        }
                    });
            alertDialogB.create();
            alertDialogB.show();
            // iniciar carga
            loadElement();

            LanguageManager.translateTextOnScreen(this, R.id.select, "es");
        }else{
            inicializar();
        }

        imagenmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogFragment newFragment = new ShowAlert();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(android.R.id.content, newFragment, "Tipo genero").commit();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // acción boton siguiente layout
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ong = new Intent(MainActivity.this, Ong.class);
                ong.putExtra("seleccion", enviarSeleccion);
                startActivity(ong);
                finish();
            }
        });
    }
    public void inicializar(){
        HashMap<String, String> user = inicilite.getInitDetails();
        System.out.println("Detos ----->>>>>: "+ user.get("modelo3D"));
        enviarSeleccion = Integer.valueOf(Objects.requireNonNull(user.get("select3D")));
        select.setEnabled(enviarSeleccion != -1);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        if(Boolean.parseBoolean(user.get("netStatus"))){
            Cargarlista3d listado3d = new Cargarlista3d();

            ft.replace(R.id.lista3d, listado3d).commit();
        }else{
            Notnet notnet = new Notnet();
            ft.replace(R.id.cargarnet, notnet).commit();
        }
    }



    public void loadElement(){
        System.out.println("Cargar elemento AQUÍ");
    }
    public void onFinishEditDialog(int seleccion) {
        System.out.println("Has seleccionado: "+ seleccion);
        enviarSeleccion = seleccion;
        //Toast.makeText(getApplicationContext(), "Has seleccionado el: " + seleccion, Toast.LENGTH_LONG).show();
        select.setEnabled(seleccion != -1);
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed(); // salida de la app
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.exit, Toast.LENGTH_SHORT).show();
        // Restaurar 2000 milisegundos para restaurar y pedir 2 nuevos back
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }}, 2000); // 2 segundo y cambia
    }

}