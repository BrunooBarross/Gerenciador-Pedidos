/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GuiManu
 */
public class ConnectionFactory {
    public Connection getConnection(){
        try {
            String nomeUsuario = "postgres";
            String senhaUsuario = "123";
            String enderecoServidor = "localhost";
            String nomeBanco = "lojapedidos";
            
            return DriverManager.getConnection("jdbc:postgresql://"+enderecoServidor+"/"+nomeBanco, nomeUsuario, senhaUsuario);
        
        } catch (SQLException ex) {
            System.out.println("Erro, não abriu conexão");
            throw new RuntimeException(ex);
        }
    }
}
