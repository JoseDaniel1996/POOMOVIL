package com.example.listview_clase;

public class Noticias {
    private String titulo;

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    private String subtitulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Noticias(String titulo, String subtitulo) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
    }
}
