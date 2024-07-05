package com.jcja.fountain_wishes.ongs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.jcja.fountain_wishes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterlistaONG extends BaseAdapter {
    Context context;
    Activity activity;
    private ArrayList<ModeloONG> items;
    ImageView imagen;
    public adapterlistaONG(Context context, Activity activity, ArrayList<ModeloONG> items){
        this.context = context;
        this.activity = activity;
        this.items = items;

    }
    @Override
    public int getCount() {
        return items.size();
    }
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.indexOf(getItem(position));
    }

    public void clear() {
        items.clear();
    }
    public void addAll(ArrayList<ModeloONG> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
            notifyDataSetChanged();
            Log.e("Modelos 3D", "contador "+i);
        }
    }
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final ModeloONG lista3d = items.get(position);
        LayoutInflater inflter = LayoutInflater.from(viewGroup.getContext());

        view = inflter.inflate(R.layout.fragment_item_ong, viewGroup, false);
        TextView id = view.findViewById(R.id.item_number);
        TextView titulo = view.findViewById(R.id.titulo);
        TextView descripcion = view.findViewById(R.id.descripcion);
        imagen = view.findViewById(R.id.imageView);
        System.out.println("Dirección url imagen: "+lista3d.getImg());


        Picasso.with(context).load(lista3d.getImg()).into(imagen);
        id.setText(lista3d.getId());
        titulo.setText(lista3d.getTitulo());
        //descripcion.setText(lista3d.getDescripcion());

        return view;
    }

}
