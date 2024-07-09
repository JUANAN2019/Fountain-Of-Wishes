package com.jcja.fountain_wishes.modelo_3d;

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

import com.jcja.fountain_wishes.MainActivity;
import com.jcja.fountain_wishes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapterlista3D extends BaseAdapter {
    Context context;
    Activity activity;
    private ArrayList<Modelo3d> items;
    //ImageView imagen;
    String tituloText, descripcionText;
    Integer estado = -1;
    public Adapterlista3D(Context context, Activity activity, ArrayList<Modelo3d> items){
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
        return items.indexOf(position);
    }

    public void clear() {
        items.clear();
    }
    public void addAll(ArrayList<Modelo3d> obj3d) {
        for (int i = 0; i < obj3d.size(); i++) {
            items.add(obj3d.get(i));
            notifyDataSetChanged();
            Log.e("Modelos 3D", "contador "+i);
        }
    }
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        final Modelo3d lista3d = items.get(position);
        LayoutInflater inflter = LayoutInflater.from(viewGroup.getContext());


        if(view == null){
            view = inflter.inflate(R.layout.fragment_item, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        //TextView id = view.findViewById(R.id.item_number);
        //TextView titulo = view.findViewById(R.id.titulo);
        //TextView descripcion = view.findViewById(R.id.descripcion);
        //imagen = view.findViewById(R.id.imageView);
        System.out.println("Dirección url imagen: "+lista3d.getImg());


        Picasso.with(context).load(lista3d.getImg()).into(holder.imagen);
        //holder.id.setText(lista3d.getId());
        // reemplazar para traductor simultaneo con IA:
        //  - titulo
        //  - descripcion
        tituloText = lista3d.getTitulo();
        descripcionText = lista3d.getDescripcion();

        holder.titulo.setText(tituloText);
        holder.descripcion.setText(descripcionText);


        view.setOnLongClickListener(new View.OnLongClickListener(){
            @SuppressLint("UseCompatLoadingForDrawables")
            public boolean onLongClick(View v) {
                //Toast.makeText(context, "Posición: " +  getItem(Integer.parseInt(lista3d.getId())), Toast.LENGTH_SHORT).show();
                if (estado == -1){
                    if (estado == Integer.parseInt(lista3d.getId())){
                        v.setBackground(context.getResources().getDrawable(R.drawable.seleccion));
                        estado = -1;
                    }else{
                        v.setBackground(context.getResources().getDrawable(R.drawable.selecionado));
                        estado = Integer.parseInt(lista3d.getId());
                    }
                }else if (estado == Integer.parseInt(lista3d.getId())) {
                    v.setBackground(context.getResources().getDrawable(R.drawable.seleccion));
                    estado = -1;
                }
                seleccionar(v, estado);
                return true;
            }
        });
        return view;
    }
    private static class ViewHolder {
        final private TextView  titulo, descripcion;
        final private ImageView imagen;
        public ViewHolder(View v) {
            //id = v.findViewById(R.id.item_number);
            titulo = v.findViewById(R.id.titulo);
            descripcion = v.findViewById(R.id.descripcion);
            imagen = v.findViewById(R.id.imageView);
        }
    }
    public void seleccionar(View view, int select) {
        MainActivity mainActivity = (MainActivity) view.getContext();
        mainActivity.onFinishEditDialog(select);
    }
}
