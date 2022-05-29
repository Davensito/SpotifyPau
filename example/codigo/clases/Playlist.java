package com.example.codigo.clases;

public class Playlist {
    private int id;
    private String titulo;

    public Playlist(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;

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

}
