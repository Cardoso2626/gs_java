package br.com.fiap.util;

import br.com.fiap.model.Habito;
import br.com.fiap.model.Usuario;

public class HabitoTo {
    private long id;
    private String descricao;
    private int qtdDia;
    private Usuario usuario;


    public Habito toHabito(){
        Habito h = new Habito();
        h.setQtdDia(qtdDia);
        h.setId(id);
        h.setDescricao(descricao);
        h.setUsuario(usuario);
        return h;
    }

    public HabitoTo(long id, String descricao, int qtdDia, Usuario usuario) {
        this.id = id;
        this.descricao = descricao;
        this.qtdDia = qtdDia;
        this.usuario = usuario;
    }



    public HabitoTo(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtdDia() {
        return qtdDia;
    }

    public void setQtdDia(int qtdDia) {
        this.qtdDia = qtdDia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
