package br.com.fiap.model;

public class Voucher {
    private long id;
    private double valor = 15.00;

    public Voucher(long id, double valor) {
        this.id = id;
        this.valor = valor;
    }
    public Voucher(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
