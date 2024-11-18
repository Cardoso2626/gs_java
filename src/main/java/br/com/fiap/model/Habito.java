package br.com.fiap.model;

public class Habito {
    private long id;
    private String descricao;
    private int qtdDia;
    private Usuario usuario;

    public Habito(long id, String descricao, int qtdDia, Usuario usuario) {
        this.id = id;
        this.descricao = descricao;
        this.qtdDia = qtdDia;
    }



    public Habito(){

    }

    //Método para ajudar na validação de business
    public boolean isCompleto(){
        if (descricao.isEmpty() || qtdDia <= 0 || usuario == null) {
            return false;
        }
        return true;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
}
