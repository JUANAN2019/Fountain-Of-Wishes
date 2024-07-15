package com.jcja.fountain_wishes.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


import com.jcja.fountain_wishes.modelo_3d.Modelo3d;
import com.jcja.fountain_wishes.ongs.ModeloONG;

import java.util.ArrayList;
import java.util.HashMap;

public class MainSesion {
    private static String TAG = MainSesion.class.getSimpleName();
    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "InicializacionAndroid";
    private static final String KEY_IS_LOGGED_IN = "isInitIn";
    public MainSesion(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void outInit(){
        setInit(false, false, -1, null, -1, null);
    }
    public void setInit(boolean isInitIn, boolean netStatus, int select3d, ArrayList<Modelo3d> modelo3D,
                         int selectONG, ArrayList<ModeloONG> modeloONG) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isInitIn);
        editor.putBoolean("netStatus", netStatus);
        editor.putInt("select3D", select3d);
        editor.putString("modelo3D", String.valueOf(modelo3D));
        editor.putInt("selectONG", selectONG);
        editor.putString("modeloONG", String.valueOf(modeloONG));
        // commit changes
        editor.commit();
    }
    public HashMap<String, String> getInitDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put("netStatus", String.valueOf(pref.getBoolean("netStatus", false)));
        user.put("select3D", String.valueOf(pref.getInt("select3D", -1)));
        user.put("modelo3D", pref.getString("modelo3D", null));
        user.put("selectONG", String.valueOf(pref.getInt("selectONG", -1)));
        user.put("modeloONG", pref.getString("modeloONG", null));

        return user;
    }
    public boolean isInitIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }
}
