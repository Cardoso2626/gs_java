package br.com.fiap.model;

public class Compra{
    private long id;
    private Voucher voucher;
    private double valor;
    private Plano plano;

    public Compra(Voucher voucher, double valor, Plano plano, long id) {
        this.voucher = voucher;
        this.valor = valor;
        this.plano = plano;
        this.id = id;
    }

    public Compra(){

    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        valor = valor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
