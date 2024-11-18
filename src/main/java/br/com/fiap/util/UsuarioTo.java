package br.com.fiap.util;

import br.com.fiap.model.Usuario;

public class UsuarioTo {
    private long id;
    private String nome;
    private int idade;
    private String telefone;
    private String cpf;


    public UsuarioTo(long id, String nome, int idade, String telefone, String cpf) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public UsuarioTo(){

    }

    public Usuario toUsuario(){
        Usuario u = new Usuario();
        u.setId(id);
        u.setNome(nome);
        u.setTelefone(telefone);
        u.setIdade(idade);
        u.setCpf(cpf);
        return u;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
