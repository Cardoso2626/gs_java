package br.com.fiap.model;

import java.util.Objects;

public class Feedback {
    private long id;
    private String critica;
    private int nota;
    private Usuario usuario;


    public Feedback(long id, String critica, int nota, Usuario usuario) {
        this.id = id;
        this.critica = critica;
        this.nota = nota;
        this.usuario = usuario;
    }

    public Feedback(){

    }



    //Método para ajudar na validação de business
    public boolean isCompleto(){
        if (critica.isEmpty() || nota < 0 || usuario == null) {
            return false;
        }
        return true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCritica() {
        return critica;
    }

    public void setCritica(String critica) {
        this.critica = critica;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
