package br.com.fiap.dao;

import br.com.fiap.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompraDao {

    //Método para inserir Compra no banco de dados
    public void insert(Compra co) throws Exception {
        ConnectionFactory c = new ConnectionFactory();
        String sql = "insert into t_compra(id_voucher, valor, id_plano)" +
                "values(?, ?, ?)";
        try(Connection con = c.getConexao();
            PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"id_compra"});){

            pstmt.setLong(1, co.getVoucher().getId());
            pstmt.setDouble(2, co.getValor());
            pstmt.setLong(3, co.getPlano().getId());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                co.setId(rs.getBigDecimal(1).longValue());
            }

        }
        catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    //Método para alterar a compra
    public void altera(Compra co) throws Exception {
        String sql = "update t_compra set id_voucher=?, valor=?, id_plano=?" +
                " where id_compra=?";
        try(Connection con = new ConnectionFactory().getConexao();
            PreparedStatement pstmt = con.prepareStatement(sql);)
        {
            pstmt.setLong(1, co.getVoucher().getId());
            pstmt.setDouble(2, co.getValor());
            pstmt.setLong(3, co.getPlano().getId());
            pstmt.setLong(4, co.getId());
            pstmt.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    //Método para recuperar por id a compra
    public Compra recuperaCompraPorId(Long id) throws Exception {
        String sql = "select id_compra, id_voucher, valor, id_plano " +
                "from t_compra where id_compra=?";

        try(Connection con = new ConnectionFactory().getConexao();
            PreparedStatement pstmt = con.prepareStatement(sql);){

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Compra co = new Compra();
                co.setId(rs.getLong("id_compra"));

                VoucherDao voucherDao = new VoucherDao();
                long idVoucher = rs.getLong("id_voucher");
                Voucher voucher = voucherDao.recuperaVoucherPorId(idVoucher);
                co.setVoucher(voucher);

                co.setValor(rs.getDouble("valor"));

                PlanoDao planoDao = new PlanoDao();
                long idPlano = rs.getLong("id_plano");
                Plano plano = planoDao.recuperaPlanoPorId(idPlano);
                co.setPlano(plano);

                return co;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }

    //Método para pegar uma lista de compras do banco
    public List<Compra> recupera() throws Exception {
        List<Compra> lista = new ArrayList<>();
        String sql = "select id_compra, id_voucher, valor, id_plano from t_compra order by id_voucher";

        try (Connection con = new ConnectionFactory().getConexao();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Compra c = new Compra();
                c.setId(rs.getLong("id_compra"));

                // Recupera o voucher associado
                VoucherDao voucherDao = new VoucherDao();
                long idVoucher = rs.getLong("id_voucher");
                Voucher voucher = voucherDao.recuperaVoucherPorId(idVoucher);
                c.setVoucher(voucher);

                // Configura o valor da compra
                c.setValor(rs.getDouble("valor"));

                // Recupera o plano associado
                PlanoDao planoDao = new PlanoDao();
                long idPlano = rs.getLong("id_plano");
                Plano plano = planoDao.recuperaPlanoPorId(idPlano);
                c.setPlano(plano);

                // Adiciona a compra à lista
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return lista;
    }

    //Método para excluir a Compra do banco de dados
    public void deleta(long id) throws Exception {
        String del = "delete from t_compra where id_compra=?";
        try(Connection con = new ConnectionFactory().getConexao();
            PreparedStatement pstmt = con.prepareStatement(del);) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }




}
