/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos;

import DAO.FornecedorDao;
import JDBC.ConnectionFactory;
import java.util.List;
import model.fornecedor;

/**
 *
 * @author GuiManu
 */
public class teste {
    
    public static void main(String[] args){
        
        FornecedorDao dao = new FornecedorDao();
        List<fornecedor> fornecedor = dao.getList();
        if(fornecedor != null){
            for(int x=0; x<fornecedor.size();x++){
                fornecedor.get(x).mostrafornecedor();
                
                System.out.println("-----------------------------");
            }
        }else{
            System.out.println("Lista Nula");
        }
        
        
    }
    
}
