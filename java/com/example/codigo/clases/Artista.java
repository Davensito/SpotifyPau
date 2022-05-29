package com.example.codigo.clases;

import javafx.scene.image.ImageView;

public class Artista {
    private int id;
    private String name;
    private ImageView img;
    private String ruta;

    public Artista(int id, String name, ImageView img, String ruta){
        this.id = id;
        this.name = name;
        this.img = img;
        this.ruta = ruta;
    }
    public Artista(String name, ImageView img){
        this.name = name;
        this.img = img;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getId() {
        return id;
    }

    public ImageView getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }
}
