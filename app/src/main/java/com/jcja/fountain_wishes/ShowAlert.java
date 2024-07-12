package com.jcja.fountain_wishes;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.jcja.fountain_wishes.app.AppConfig;
import com.squareup.picasso.Picasso;

public class ShowAlert extends DialogFragment {
    public ShowAlert(){

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.mensaje, container, false);
        PackageInfo pInfo = null;
        try {
            pInfo = getActivity(). getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        TextView version = view.findViewById(R.id.textView2);
        version.setText(pInfo.versionName);

        TextView jaText = view.findViewById(R.id.textView4);
        TextView jcText = view.findViewById(R.id.textView5);
        TextView iaText = view.findViewById(R.id.textView6);
        ImageView jaIma = view.findViewById(R.id.jaimag);
        ImageView jcIma = view.findViewById(R.id.jcimag);
        ImageView iaIma = view.findViewById(R.id.iaimag);

        Picasso.with(getContext()).load(AppConfig.JAIMAGE).into(jaIma);
        Picasso.with(getContext()).load(AppConfig.JCIMAGE).into(jcIma);
        Picasso.with(getContext()).load(AppConfig.IAIMAGE).into(iaIma);
        Button acepta = view.findViewById(R.id.buttonAcepta);
        Button valorar = view.findViewById(R.id.valora);
        acepta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        PackageInfo finalPInfo = pInfo;
        valorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWeb(AppConfig.VALORA + finalPInfo.packageName);
            }
        });
        jaText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWeb(AppConfig.JAURL);
            }
        });
        jcText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWeb(AppConfig.JCURL);
            }
        });
        iaText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWeb(AppConfig.IAURL);
            }
        });
        return view;
    }
    private  void openWeb(String url){
        System.out.println(">>>>> hacemos >>>>>>: "+ url);
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        // mejorar
        startActivity(intent);

    }
    private void onMensaje(Context context){
        AlertDialog.Builder alertDialogB = new AlertDialog.Builder(context);
        LayoutInflater inflaterB = getLayoutInflater();
        View dialogViewB = inflaterB.inflate(R.layout.bienvenida, null);
        alertDialogB.setCancelable(false);
        alertDialogB.setView(dialogViewB);
        alertDialogB.setPositiveButton(R.string.empezar,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });
        alertDialogB.create();
        alertDialogB.show();
    }
}
