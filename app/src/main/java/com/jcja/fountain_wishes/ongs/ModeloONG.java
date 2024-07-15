package com.jcja.fountain_wishes.ongs;


import com.jcja.fountain_wishes.app.AppConfig;

public class ModeloONG {
    private String id, titulo, descripcion, img, valoracion;

    public ModeloONG(String id, String titulo, String descripcion, String valoracion, String img){
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.img = img;
        this.valoracion = valoracion;
    }
    public String getValoracion() {
        return valoracion;
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

    public String getImg() {
        return AppConfig.BASE+"modelo/"+img;
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
    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }
    public void setImg(String img) {
        this.img = img;
    }
}
