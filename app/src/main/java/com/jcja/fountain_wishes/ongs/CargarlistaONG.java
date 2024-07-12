package com.jcja.fountain_wishes.ongs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;


import com.jcja.fountain_wishes.R;
import com.jcja.fountain_wishes.app.AppConfig;

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

public class CargarlistaONG extends Fragment {
    ListView lv;
    AdapterlistaONG adapter;
    ArrayList<ModeloONG> listaONG = new ArrayList<>();
    ArrayList<ModeloONG> devuelto = new ArrayList<>();
    public CargarlistaONG() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootlistaOng = inflater.inflate(R.layout.fragment_list_ong, container, false);
        lv = rootlistaOng.findViewById(R.id.onglistado);
        adapter = new AdapterlistaONG(getContext(), getActivity(), devuelto);
        new connections().execute();
        lv.setAdapter(adapter);

        return rootlistaOng;
    }
    private void updateModelList(ArrayList<ModeloONG> models) {
        devuelto.clear();
        devuelto.addAll(models);
        adapter.notifyDataSetChanged();
    }
    private class connections extends AsyncTask<Void, Void, ArrayList<ModeloONG>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected ArrayList<ModeloONG> doInBackground(Void... params) {
            String url = AppConfig.BASE+AppConfig.JSONONGS;
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
                for (int i = 0; i < jsonArray.length(); i++) {
                    jobj = jsonArray.getJSONObject(i);
                    System.out.println("objeto: " + jobj.getInt("id"));

                    ModeloONG modelo3d = new ModeloONG(
                            jobj.getString("id"),
                            jobj.getString("titulo"),
                            jobj.getString("descripcion"),
                            jobj.getString("valoracion"),
                            jobj.getString("imagen")
                    );
                    listaONG.add(modelo3d);
                    System.out.println("<<<<----->>>>> Objeto " + listaONG);
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }finally {
                if(con != null){
                    con.disconnect();
                }
            }
            return listaONG;
        }
        protected void onPostExecute(ArrayList<ModeloONG> models) {
            updateModelList(models);
        }
    }
}



