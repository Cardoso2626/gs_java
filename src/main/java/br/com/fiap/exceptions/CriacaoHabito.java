package br.com.fiap.exceptions;

public class CriacaoHabito extends Exception{

    public CriacaoHabito(String msg){
        super(msg);
    }

    public CriacaoHabito(String msg, Throwable cause){
        super(msg, cause);
    }

}
