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
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import model.fornecedor;

/**
 *
 * @author GuiManu
 */
public class FornecedorDao {
    private Connection con;
    
    public FornecedorDao(){
        this.con = new ConnectionFactory().getConnection();
    }
    
    public boolean add(fornecedor p){
        String verifica = "SELECT COUNT(cnpj) as existe FROM fornecedor WHERE cnpj = ? LIMIT 1;";
        try {
            PreparedStatement stmt = con.prepareStatement(verifica);
            stmt.setString(1, p.getCnpj());
            ResultSet rs = stmt.executeQuery();
             while(rs.next()){
                 int result = rs.getInt("existe");
                if (result == 0){
                    String sql = "INSERT INTO fornecedor(razao,cnpj) VALUES (?,?);";

                   try {
                       PreparedStatement stm = con.prepareStatement(sql);
                       stm.setString(1, p.getRazao());
                       stm.setString(2, p.getCnpj());
                       stm.execute();
                       stm.close();
                       con.close();
                       return true;

                   } catch (SQLException ex) {
                       Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
                       return false;
                   }
                } else {
                    Alert a = new Alert(Alert.AlertType.WARNING);
                   a.setHeaderText("Fornecedor já cadastrado");
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

    
    public boolean update(fornecedor p){
        String sql = "UPDATE fornecedor SET razao = ?, cnpj = ? WHERE idfornecedor = ?;";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getRazao());
            stmt.setString(2, p.getCnpj());
            stmt.setLong(3, p.getIdfornecedor());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean delete(fornecedor p){
        String sql = "DELETE FROM fornecedor WHERE idfornecedor = ?;";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, p.getIdfornecedor());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<fornecedor> getList(){
        List<fornecedor> fornecedor = new ArrayList<>();
        String sql = "SELECT * FROM fornecedor";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                fornecedor p = new fornecedor();
                p.setIdfornecedor(rs.getLong("idfornecedor"));
                p.setRazao(rs.getString("razao"));
                p.setCnpj(rs.getString("cnpj"));
                fornecedor.add(p);
                
            }
            stmt.close();
            rs.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Erro Lista");
            return null;
        }
        return fornecedor;
    }
}
