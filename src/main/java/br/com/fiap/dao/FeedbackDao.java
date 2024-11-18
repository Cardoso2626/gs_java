package br.com.fiap.dao;

import br.com.fiap.model.Feedback;
import br.com.fiap.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDao {

    //Método para inserir Feedback no banco de dados
    public void insert(Feedback f) throws Exception {
        ConnectionFactory c = new ConnectionFactory();
        String sql = "insert into t_feedback(critica, nota, id_usuario)" +
                "values(?, ?, ?)";
        try(Connection con = c.getConexao();
            PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"id_feedback"});){

            pstmt.setString(1, f.getCritica());
            pstmt.setInt(2, f.getNota());
            pstmt.setLong(3, f.getUsuario().getId());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                f.setId(rs.getBigDecimal(1).longValue());
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


    //Método para alterar o feedback
    public void altera(Feedback f) throws Exception {
        String sql = "update t_feedback set critica=?, nota=?, id_usuario=?" +
                " where id_feedback=?";
        try(Connection con = new ConnectionFactory().getConexao();
            PreparedStatement pstmt = con.prepareStatement(sql);)
        {
            pstmt.setString(1, f.getCritica());
            pstmt.setInt(2, f.getNota());
            pstmt.setLong(3, f.getUsuario().getId());
            pstmt.setLong(4, f.getId());
            pstmt.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    //Método para recuperar por id o feedback
    public Feedback recuperaFeedbackPorId(Long id) throws Exception {
        String sql = "select id_feedback, critica, nota, id_usuario " +
                "from t_feedback where id_feedback=?";

        try(Connection con = new ConnectionFactory().getConexao();
            PreparedStatement pstmt = con.prepareStatement(sql);){

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Feedback f = new Feedback();
                f.setId(rs.getLong("id_feedback"));
                f.setCritica(rs.getString("critica"));
                f.setNota(rs.getInt("nota"));

                UsuarioDao usuarioDao = new UsuarioDao();

                long idUsuario = rs.getLong("id_usuario");
                Usuario usuario = usuarioDao.recuperaUsuarioPorId(idUsuario);
                f.setUsuario(usuario);

                return f;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }

    //Método para pegar uma lista de feedbacks do banco
    public List<Feedback> recupera() throws Exception {
        List<Feedback> lista = new ArrayList<>();
        String sql = "select id_feedback, critica, nota, id_usuario from t_feedback order by critica";

        try (Connection con = new ConnectionFactory().getConexao();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback();
                f.setId(rs.getLong("id_feedback"));
                f.setCritica(rs.getString("critica"));
                f.setNota(rs.getInt("nota"));

                // Recupera o usuário associado
                UsuarioDao usuarioDao = new UsuarioDao();
                long idUsuario = rs.getLong("id_usuario");
                Usuario usuario = usuarioDao.recuperaUsuarioPorId(idUsuario);
                f.setUsuario(usuario);

                // Adiciona o feedback à lista
                lista.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return lista;
    }

    //Método para excluir o feedback do banco de dados
    public void deleta(long id) throws Exception {
        String del = "delete from t_feedback where id_feedback=?";
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
