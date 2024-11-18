package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    public Connection getConexao() throws Exception{
        String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
        return DriverManager.getConnection(url, "rm556496", "fiap24");
    }
}
