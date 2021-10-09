/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import JDBC.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import model.cliente;
import model.fornecedor;

/**
 *
 * @author GuiManu
 */

public class ClienteDao {
    private Connection con;
    
    public ClienteDao(){
        this.con = new ConnectionFactory().getConnection();
    }
    
    public boolean add(cliente p){
        String verifica = "SELECT COUNT(cpf) as existe FROM cliente WHERE cpf = ? LIMIT 1;";
        try {
            PreparedStatement stmt = con.prepareStatement(verifica);
            stmt.setString(1, p.getCpf());
            ResultSet rs = stmt.executeQuery();
             while(rs.next()){
                int result = rs.getInt("existe");
                if (result == 0){ 
                String sql = "INSERT INTO cliente(nome,cpf) VALUES (?,?);";

                    try {
                        PreparedStatement stm = con.prepareStatement(sql);
                        stm.setString(1, p.getNome());
                        stm.setString(2, p.getCpf());
                        stm.execute();
                        stm.close();
                        con.close();
                        return true;

                    } catch (SQLException ex) {
                        Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }
                }else{
                    Alert a = new Alert(Alert.AlertType.WARNING);
                a.setHeaderText("Cliente já cadastrado");
                a.show();
                return false;
                }
            }
             return false;
        } catch (SQLException ex) {
                Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);

                return false;
            }
            
        }
    
    
    public boolean update(cliente p){
        String sql = "UPDATE cliente SET nome = ?, cpf = ? WHERE idcliente = ?;";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCpf());
            stmt.setLong(3, p.getIdcliente());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean delete(cliente p){
        String sql = "DELETE FROM cliente WHERE idcliente = ?;";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, p.getIdcliente());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<cliente> getList(){
        List<cliente> cliente = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                cliente p = new cliente();
                p.setIdcliente(rs.getLong("idcliente"));
                p.setNome(rs.getString("nome"));
                p.setCpf(rs.getString("cpf"));
                cliente.add(p);
                
            }
            stmt.close();
            rs.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Erro Lista");
            return null;
        }
        return cliente;
    }
}
