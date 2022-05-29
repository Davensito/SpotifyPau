package com.example.codigo.clases;

public class Album {
    private String id;
    private int anyo;
    private String titulo;

    public Album (String id, String titulo, int anyo) {
        this.id = id;
        this.anyo = anyo;
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
