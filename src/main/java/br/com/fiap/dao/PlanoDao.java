package br.com.fiap.dao;

import br.com.fiap.model.Feedback;
import br.com.fiap.model.Plano;
import br.com.fiap.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlanoDao {

    //Método para inserir o Plano no banco de dados
    public void insert(Plano p) throws Exception {
        ConnectionFactory c = new ConnectionFactory();
        String sql = "insert into t_plano(valor, descricao)" +
                "values(?, ?)";
        try(Connection con = c.getConexao();
            PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"id_plano"});){

            pstmt.setDouble(1, p.getValor());
            pstmt.setString(2, p.getDescricao());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                p.setId(rs.getBigDecimal(1).longValue());
            }

        }
        catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    //Método para alterar o plano
    public void altera(Plano p) throws Exception {
        String sql = "update t_plano set descricao=?, valor=?" +
                " where id_plano=?";
        try(Connection con = new ConnectionFactory().getConexao();
            PreparedStatement pstmt = con.prepareStatement(sql);)
        {
            pstmt.setString(1, p.getDescricao());
            pstmt.setDouble(2, p.getValor());
            pstmt.setLong(3, p.getId());
            pstmt.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    //Método para recuperar por id o plano
    public Plano recuperaPlanoPorId(Long id) throws Exception {
        String sql = "select id_plano, descricao, valor " +
                "from t_plano where id_plano=?";

        try(Connection con = new ConnectionFactory().getConexao();
            PreparedStatement pstmt = con.prepareStatement(sql);){

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Plano p = new Plano();
                p.setId(rs.getLong("id_plano"));
                p.setDescricao(rs.getString("descricao"));
                p.setValor(rs.getDouble("valor"));
                return p;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }


    //Método para pegar uma lista de planos do banco
    public List<Plano> recupera() throws Exception {
        List<Plano> lista = new ArrayList<>();
        String sql = "select id_plano, descricao, valor from t_plano order by descricao";

        try (Connection con = new ConnectionFactory().getConexao();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Plano p = new Plano();
                p.setId(rs.getLong("id_plano"));
                p.setDescricao(rs.getString("descricao"));
                p.setValor(rs.getDouble("valor"));

                // Adiciona o plano à lista
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return lista;
    }


    //Método para excluir o plano do banco de dados
    public void deleta(long id) throws Exception {
        String del = "delete from t_plano where id_plano=?";
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
