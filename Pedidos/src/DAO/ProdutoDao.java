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
import model.fornecedor;
import model.produto;

/**
 *
 * @author GuiManu
 */
public class ProdutoDao {
    private Connection con;
    
    public ProdutoDao(){
        this.con = new ConnectionFactory().getConnection();
    }
    
    public boolean add(produto p){
        String sql = "INSERT INTO produto(item,valorproduto,descricao,idfornecedor) VALUES (?,?,?,?);";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getItem());
            stmt.setDouble(2, p.getValorproduto());
            stmt.setString(3, p.getDescricao());
            stmt.setString(4, p.getIdfornecedor());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
  public boolean update(produto p){
        String sql = "UPDATE produto SET item = ?, valorproduto = ?, descricao = ?, idfornecedor = ? WHERE idproduto = ?;";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getItem());
            stmt.setDouble(2, p.getValorproduto());
            stmt.setString(3, p.getDescricao());
            stmt.setString(4, p.getIdfornecedor());
            stmt.setLong(5, p.getIdproduto());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean delete(produto p){
        String sql = "DELETE FROM produto WHERE idproduto = ?;";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, p.getIdproduto());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<produto> getList(){
        List<produto> produto = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                produto p = new produto();
                p.setIdproduto(rs.getLong("idproduto"));
                p.setItem(rs.getString("item"));
                p.setValorproduto(rs.getDouble("valorproduto"));
                p.setDescricao(rs.getString("descricao"));
                p.setIdfornecedor(rs.getString("idfornecedor"));
                produto.add(p);
                
            }
            stmt.close();
            rs.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Erro Lista");
            return null;
        }
        return produto;
    }
}
