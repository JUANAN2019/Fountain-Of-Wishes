package com.jcja.fountain_wishes.modelo_3d;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;


import com.jcja.fountain_wishes.R;
import com.jcja.fountain_wishes.app.AppConfig;
import com.jcja.fountain_wishes.app.NetStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Cargarlista3d extends Fragment {
    ListView lv;
    Adapterlista3D adapter;
    ArrayList<Modelo3d> lista3D = new ArrayList<>();
    ArrayList<Modelo3d> devuelto = new ArrayList<>();
    public Cargarlista3d() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootlista3d = inflater.inflate(R.layout.fragment_list, container, false);
        lv = rootlista3d.findViewById(R.id.listafuentes);
        // Comprobar si hay internet
        if (NetStatus.isNetworkAvailable(requireContext())){
            new connections().execute();
        }
        adapter = new Adapterlista3D(getContext(), getActivity(), devuelto);
        lv.setAdapter(adapter);
        return rootlista3d;
    }
    private void updateModelList(ArrayList<Modelo3d> models) {
        devuelto.clear();
        devuelto.addAll(models);
        adapter.notifyDataSetChanged();
    }
    private class connections extends AsyncTask<Void, Void, ArrayList<Modelo3d>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected ArrayList<Modelo3d> doInBackground(Void... params) {
            String url = AppConfig.BASE+AppConfig.JSON3D;
            HttpURLConnection con = null;
            try {
                JSONArray jsonArray = null;
                URL obj = new URL(url);
                con = (HttpURLConnection) obj.openConnection();
                con.connect();
                InputStream is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String linea = "";
                while ((linea = br.readLine()) != null){
                    sb.append(linea);
                }
                jsonArray = new JSONArray(sb.toString());
                JSONObject jobj;
                if(jsonArray != null){
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jobj = jsonArray.getJSONObject(i);
                        System.out.println("objeto: "+jobj.getInt("id"));

                        Modelo3d modelo3d = new Modelo3d(
                                jobj.getString("id"),
                                jobj.getString("titulo"),
                                jobj.getString("descripcion"),
                                jobj.getString("modelo"),
                                jobj.getString("imagen")
                        );
                        lista3D.add(modelo3d);
                        System.out.println("<<<<----->>>>> Objeto "+lista3D);
                    }
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } finally {
                if(con != null){
                    con.disconnect();
                }
            }
            return lista3D;
        }
        @Override
        protected void onPostExecute(ArrayList<Modelo3d> models) {
            updateModelList(models);
        }
    }
}


