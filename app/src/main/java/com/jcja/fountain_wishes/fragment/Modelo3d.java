package com.jcja.fountain_wishes.fragment;

import com.jcja.fountain_wishes.app.AppConfig;

public class Modelo3d {
    private String id, titulo, descripcion, modelo, img;
    public Modelo3d(){}
    public Modelo3d(String id, String titulo, String descripcion, String modelo, String img){
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.modelo = modelo;
        this.img = img;
    }
    public String getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getModelo() {
        return modelo;
    }
    public String getImg() {
        return AppConfig.base+"modelo/"+img;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setImg(String img) {
        this.img = img;
    }
}
