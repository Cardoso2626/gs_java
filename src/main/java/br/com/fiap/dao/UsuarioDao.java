package br.com.fiap.dao;

import br.com.fiap.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    //Método para inserir o Usuario no banco de dados
    public void insert(Usuario u) throws Exception {
        ConnectionFactory c = new ConnectionFactory();
        String sql = "insert into t_usuario(nome, idade, telefone, cpf)" +
                "values(?, ?, ?, ?)";
        try(Connection con = c.getConexao();
             PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"id_usuario"});){

            pstmt.setString(1, u.getNome());
            pstmt.setInt(2, u.getIdade());
            pstmt.setString(3, u.getTelefone());
            pstmt.setString(4, u.getCpf());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                u.setId(rs.getBigDecimal(1).longValue());
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


    //Método para alterar o usuario
    public void altera(Usuario u) throws Exception {
        String sql = "update t_usuario set nome=?, idade=?, telefone=?, cpf=?" +
                " where id_usuario=?";
        try(Connection con = new ConnectionFactory().getConexao();
            PreparedStatement pstmt = con.prepareStatement(sql);)
        {
            pstmt.setString(1, u.getNome());
            pstmt.setInt(2, u.getIdade());
            pstmt.setString(3, u.getTelefone());
            pstmt.setString(4, u.getCpf());
            pstmt.setLong(5, u.getId());
            pstmt.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    //Método para recuperar por id o usuario
    public Usuario recuperaUsuarioPorId(Long id) throws Exception {
        String sql = "select id_usuario, nome, idade, telefone, cpf " +
                "from t_usuario where id_usuario=?";

        try(Connection con = new ConnectionFactory().getConexao();
            PreparedStatement pstmt = con.prepareStatement(sql);){

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getLong("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setIdade(rs.getInt("idade"));
                u.setTelefone(rs.getString("telefone"));
                u.setTelefone(rs.getString("telefone"));
                u.setCpf(rs.getString("cpf"));
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }

    //Método para pegar uma lista de usuarios do banco
    public List<Usuario> recupera() throws Exception {
        List<Usuario> lista = new ArrayList<>();
        String sql = "select id_usuario, nome, idade, telefone, cpf from t_usuario order by nome";

        try (Connection con = new ConnectionFactory().getConexao();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getLong("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setIdade(rs.getInt("idade"));
                u.setTelefone(rs.getString("telefone"));
                u.setCpf(rs.getString("cpf"));

                // Adiciona o usuário à lista
                lista.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return lista;
    }


    //Método para excluir o usuario do banco de dados
    public void deleta(long id) throws Exception {
        String del = "delete from t_usuario where id_usuario=?";
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
