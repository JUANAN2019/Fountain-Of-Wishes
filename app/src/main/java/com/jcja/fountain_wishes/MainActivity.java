package com.jcja.fountain_wishes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        ft.replace(R.id.listaong, listado3d).commit();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, Ong.class));
                Intent ong = new Intent(MainActivity.this, Ong.class);
                startActivity(ong);
                finish();

            }
        });
    }
}