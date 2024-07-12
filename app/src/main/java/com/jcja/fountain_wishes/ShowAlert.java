package com.jcja.fountain_wishes;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

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
        return view;
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
