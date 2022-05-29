package com.example.codigo.clases;

public class Podcast {
    private int id;
    private String titulo;
    private int anyo;

    public Podcast(int id, String titulo, int anyo) {
        this.id = id;
        this.titulo = titulo;
        this.anyo = anyo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }
}
