package br.com.fiap.dao;

import br.com.fiap.model.Feedback;
import br.com.fiap.model.Usuario;
import br.com.fiap.model.Voucher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VoucherDao {

    //Método para insertir o voucher no banco de dados
    public void insert(Voucher v) throws Exception {
        ConnectionFactory c = new ConnectionFactory();
        String sql = "insert into t_voucher(valor)" +
                "values(?)";
        try(Connection con = c.getConexao();
            PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"id_voucher"});){

            pstmt.setDouble(1, v.getValor());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                v.setId(rs.getBigDecimal(1).longValue());
            }

        }
        catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    //Método para alterar o voucher
    public void altera(Voucher v) throws Exception {
        String sql = "update t_voucher set valor=?" +
                " where id_voucher=?";
        try(Connection con = new ConnectionFactory().getConexao();
            PreparedStatement pstmt = con.prepareStatement(sql);)
        {
            pstmt.setDouble(1, v.getValor());
            pstmt.setLong(2, v.getId());
            pstmt.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    //Método para recuperar por id o voucher
    public Voucher recuperaVoucherPorId(Long id) throws Exception {
        String sql = "select id_voucher, valor " +
                "from t_voucher where id_voucher=?";

        try(Connection con = new ConnectionFactory().getConexao();
            PreparedStatement pstmt = con.prepareStatement(sql);){

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Voucher v = new Voucher();
                v.setId(rs.getLong("id_voucher"));
                v.setValor(rs.getDouble("valor"));

                return v;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }

    //Método para pegar uma lista de vouchers do banco
    public List<Voucher> recupera() throws Exception {
        List<Voucher> lista = new ArrayList<>();
        String sql = "select id_voucher, valor from t_voucher order by valor";

        try (Connection con = new ConnectionFactory().getConexao();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Voucher v = new Voucher();
                v.setId(rs.getLong("id_voucher"));
                v.setValor(rs.getDouble("valor"));

                // Adiciona o voucher à lista
                lista.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return lista;
    }

    //Método para excluir o voucher do banco de dados
    public void deleta(long id) throws Exception {
        String del = "delete from t_voucher where id_voucher=?";
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
