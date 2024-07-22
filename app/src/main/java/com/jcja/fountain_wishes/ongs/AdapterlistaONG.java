package com.jcja.fountain_wishes.ongs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.jcja.fountain_wishes.Ong;
import com.jcja.fountain_wishes.R;
import com.jcja.fountain_wishes.app.LanguageManager;
import com.jcja.fountain_wishes.app.MainSesion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class AdapterlistaONG extends BaseAdapter {
    Context context;
    Activity activity;
    String tituloText, descripcionText;
    Integer estado = -1;
    private ArrayList<ModeloONG> items;
    private MainSesion inicilite;
    public AdapterlistaONG(Context context, Activity activity, ArrayList<ModeloONG> items){
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
    public void addAll(ArrayList<ModeloONG> ong) {
        for (int i = 0; i < ong.size(); i++) {
            items.add(ong.get(i));
            notifyDataSetChanged();
        }
    }
    @SuppressLint({"ViewHolder", "StringFormatMatches", "UseCompatLoadingForDrawables"})
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        final ModeloONG lista = items.get(position);
        LayoutInflater inflter = LayoutInflater.from(viewGroup.getContext());
        inicilite = new MainSesion(context);
        HashMap<String, String> user = inicilite.getInitDetails();
        estado = Integer.parseInt(Objects.requireNonNull(user.get("selectONG")));

        if(view == null){
            view = inflter.inflate(R.layout.fragment_item_ong, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        if(estado!=-1 && estado == Integer.parseInt(lista.getId())){
            view.setBackground(context.getResources().getDrawable(R.drawable.selecionado));
        }
        System.out.println("Dirección url imagen: "+lista.getImg());

        tituloText = lista.getTitulo();
        descripcionText = lista.getDescripcion();


        holder.titulo.setText(tituloText);
        holder.descripcion.setText(descripcionText);
        holder.imagen.setContentDescription(tituloText);
        Picasso.with(context).load(lista.getImg()).into(holder.imagen);
        holder.valoracion.setText(context.getString(R.string.valoracion, Integer.parseInt(lista.getValoracion())));

        view.setOnLongClickListener(new View.OnLongClickListener(){
            @SuppressLint("UseCompatLoadingForDrawables")
            public boolean onLongClick(View v) {
                //Toast.makeText(context, "Posición: " +  getItem(Integer.parseInt(lista3d.getId())), Toast.LENGTH_SHORT).show();
                if (estado == -1){
                    if (estado == Integer.parseInt(lista.getId())){
                        v.setBackground(context.getResources().getDrawable(R.drawable.seleccion));
                        estado = -1;
                    }else{
                        v.setBackground(context.getResources().getDrawable(R.drawable.selecionado));
                        estado = Integer.parseInt(lista.getId());
                    }
                }else if (estado == Integer.parseInt(lista.getId())) {
                    v.setBackground(context.getResources().getDrawable(R.drawable.seleccion));
                    estado = -1;
                }
                inicilite.setInit(true, Boolean.parseBoolean(user.get("netStatus")), Integer.parseInt(user.get("select3D")),null, estado, null);
                seleccionar(v, estado);
                return true;
            }
        });

        return view;
    }
    private static class ViewHolder {
        final private TextView  titulo, descripcion, valoracion;
        final private ImageView imagen;
        public ViewHolder(View v) {
            //id = v.findViewById(R.id.item_number);
            titulo = v.findViewById(R.id.tituloong);
            valoracion = v.findViewById(R.id.valoracion);
            descripcion = v.findViewById(R.id.descripcionong);
            imagen = v.findViewById(R.id.imageView);

        }
    }
    public void seleccionar(View view, int select) {
        Ong activityOng = (Ong) view.getContext();

        activityOng.onFinishEditDialog(select);
    }

}

