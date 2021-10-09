/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ProdutoDao;
import DAO.VendaDao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.produto;
import model.venda;
import pedidos.AlteraProduto;
import pedidos.AlterarVenda;

/**
 * FXML Controller class
 *
 * @author GuiManu
 */
public class AlterarVendaController implements Initializable {

    @FXML
    private TextField lbestado;

    @FXML
    private Button btalterar;
    
    private static venda p;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        initVenda();
        btalterar.setOnMouseClicked((MouseEvent e)->{
            atualiza();
        });
    }    
    
    public void initVenda(){
        lbestado.setText(p.getEstado().toString());
        
    }
    
    public static venda getP() {
        return p;
    }

    public static void setP(venda p) {
        AlterarVendaController.p = p;
    }

    public void fecha(){
        AlterarVenda.getStage().close();
    }
    public void atualiza(){
        

                
                    
        String estado = lbestado.getText();
        String cpf = p.getCnpj().toString();
        System.out.println(cpf);
        venda p = new venda(cpf,estado);
        VendaDao dao = new VendaDao();
        if(dao.update(p)){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Estado Alterado");
                    alert.show();
                    fecha();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Estado não Alterado");
                    alert.show();
                }
                   
            }
        
        
        
          
    
}
