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
import model.venda;

/**
 *
 * @author GuiManu
 */
    
public class VendaDao {
    private Connection con;
    
    public VendaDao(){
        this.con = new ConnectionFactory().getConnection();
    }
    
    public boolean add(venda p){
        String sql = "INSERT INTO venda(cpf,cnpj,idproduto,valorvenda,estado) VALUES (?,?,?,?,?);";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getCpf());
            stmt.setString(2, p.getCnpj());
            stmt.setLong(3, p.getIdproduto());
            stmt.setDouble(4, p.getValorvenda());
            stmt.setString(5, p.getEstado());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(VendaDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    public boolean update(venda p){
        String sql = "UPDATE venda SET estado = ? WHERE idfornecedor = ?;";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getEstado());
            stmt.setString(2, p.getCpf());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(VendaDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    
    public List<venda> getList(){
        List<venda> venda = new ArrayList<>();
        String sql = "SELECT * FROM venda";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                venda p = new venda();
                p.setCpf(rs.getString("cpf"));
                p.setCnpj(rs.getString("cnpj"));
                p.setIdproduto(rs.getLong("idproduto"));
                p.setValorvenda(rs.getDouble("valorvenda"));
                p.setEstado(rs.getString("estado"));
                venda.add(p);
                
            }
            stmt.close();
            rs.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Erro Lista");
            return null;
        }
        return venda;
    }
}
