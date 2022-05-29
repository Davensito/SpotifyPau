package com.example.codigo.clases;

public class Capitulo {
    private int id;
    private String titulo;
    private int duracion;

    public Capitulo(int id, String titulo, int duracion) {
        this.id = id;
        this.titulo = titulo;
        this.duracion = duracion;
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

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
