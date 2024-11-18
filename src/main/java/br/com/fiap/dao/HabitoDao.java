package br.com.fiap.dao;

import br.com.fiap.exceptions.CriacaoHabito;
import br.com.fiap.model.Feedback;
import br.com.fiap.model.Habito;
import br.com.fiap.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HabitoDao {
    //Método para inserir Habito no banco de dados
    public void insert(Habito h) throws CriacaoHabito {
        ConnectionFactory c = new ConnectionFactory();
        String sql = "insert into t_habito(descricao, qtdDia, id_usuario)" +
                "values(?, ?, ?)";
        try(Connection con = c.getConexao();
            PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"id_habito"});){

            pstmt.setString(1, h.getDescricao());
            pstmt.setInt(2, h.getQtdDia());
            pstmt.setLong(3, h.getUsuario().getId());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                h.setId(rs.getBigDecimal(1).longValue());
            }

        }
        catch(Exception e) {
            throw new CriacaoHabito("Erro ao inserir o hábito no banco de dados: ", e);
        }
    }


    //Método para alterar o habito
    public void altera(Habito h) throws Exception {
        String sql = "update t_habito set descricao=?, qtdDia=?, id_usuario=?" +
                " where id_habito=?";
        try(Connection con = new ConnectionFactory().getConexao();
            PreparedStatement pstmt = con.prepareStatement(sql);)
        {
            pstmt.setString(1, h.getDescricao());
            pstmt.setInt(2, h.getQtdDia());
            pstmt.setLong(3, h.getUsuario().getId());
            pstmt.setLong(4, h.getId());
            pstmt.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    //Método para recuperar por id o Habito
    public Habito recuperaHabitoPorId(Long id) throws Exception {
        String sql = "select id_habito, descricao, qtdDia, id_usuario " +
                "from t_habito where id_habito=?";

        try(Connection con = new ConnectionFactory().getConexao();
            PreparedStatement pstmt = con.prepareStatement(sql);){

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Habito h = new Habito();
                h.setId(rs.getLong("id_habito"));
                h.setDescricao(rs.getString("descricao"));
                h.setQtdDia(rs.getInt("qtdDia"));

                UsuarioDao usuarioDao = new UsuarioDao();
                long idUsuario = rs.getLong("id_usuario");
                Usuario usuario = usuarioDao.recuperaUsuarioPorId(idUsuario);
                h.setUsuario(usuario);

                return h;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }


    //Método para pegar uma lista de hábitos do banco
    public List<Habito> recupera() throws Exception {
        List<Habito> lista = new ArrayList<>();
        String sql = "select id_habito, descricao, qtdDia, id_usuario from t_habito order by descricao";

        try (Connection con = new ConnectionFactory().getConexao();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Habito h = new Habito();
                h.setId(rs.getLong("id_habito"));
                h.setDescricao(rs.getString("descricao"));
                h.setQtdDia(rs.getInt("qtdDia"));

                UsuarioDao usuarioDao = new UsuarioDao();
                long idUsuario = rs.getLong("id_usuario");
                Usuario usuario = usuarioDao.recuperaUsuarioPorId(idUsuario);
                h.setUsuario(usuario);

                lista.add(h);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return lista;
    }

    //Método para excluir o Habito do banco de dados
    public void deleta(long id) throws Exception {
        String del = "delete from t_habito where id_habito=?";
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
