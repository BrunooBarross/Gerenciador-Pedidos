/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pedidos.CadastraVenda;
import pedidos.CadastrarCliente;
import pedidos.CadastrarFornecedor;
import pedidos.CadastrarProduto;
import pedidos.ListaVenda;
import pedidos.ListarCliente;
import pedidos.ListarFornecedor;
import pedidos.ListarProduto;
import pedidos.ListarVenda;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author GuiManu
 */
public class PrincipalController implements Initializable {

    
    @FXML
    private Button btcadastrarfornecedor;

    @FXML
    private Button btcadastrarcliente;

    @FXML
    private Button btcadastrarproduto;

    @FXML
    private Button btcadastrarvenda;
    
    @FXML
    private Button btlistfor;
    
    
    @FXML
    private Button btlistpro;
    
    @FXML
    private Button btlistcli;

    @FXML
    private Button btsair;
    
    @FXML
    private Button btlistvenda;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btcadastrarfornecedor.setOnMouseClicked((MouseEvent e)->{
            abrefornecedor();
        });
        btcadastrarfornecedor.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                abrefornecedor();
            }
        });
        btcadastrarproduto.setOnMouseClicked((MouseEvent e)->{
            abreproduto();
        });
        btcadastrarproduto.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
               abreproduto();
            }
        });
        btcadastrarcliente.setOnMouseClicked((MouseEvent e)->{
            abrecliente();
        });
        btlistfor.setOnMouseClicked((MouseEvent e)->{
            abrelistarfornecedor();
        });
        btlistpro.setOnMouseClicked((MouseEvent e)->{
            abrelistarproduto();
        });
        btsair.setOnMouseClicked((MouseEvent e)->{
            fecha();
        });
        btlistcli.setOnMouseClicked((MouseEvent e)->{
            abrelistarcliente();
        });
        btcadastrarvenda.setOnMouseClicked((MouseEvent e)->{
            abrevenda();
        });
        btlistvenda.setOnMouseClicked((MouseEvent e)->{
            abrelistarvenda();
        }); 
        
        // TODO
    }
    
    public void abrefornecedor(){
        CadastrarFornecedor emp = new CadastrarFornecedor();
        fecha();
            try {
                emp.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void abrecliente(){
        CadastrarCliente emp = new CadastrarCliente();
        fecha();
            try {
                emp.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void abrevenda(){
        CadastraVenda emp = new CadastraVenda();
        fecha();
            try {
                emp.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void fecha(){
        Principal.getStage().close();
    }
    public void abreproduto(){
        CadastrarProduto emp = new CadastrarProduto();
        fecha();
            try {
                emp.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void abrelistarfornecedor(){
        ListarFornecedor emp = new ListarFornecedor();
        fecha();
        try {
            emp.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    public void abrelistarproduto(){
        ListarProduto emp = new ListarProduto();
        fecha();
        try {
            emp.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    public void abrelistarcliente(){
        ListarCliente emp = new ListarCliente();
        fecha();
        try {
            emp.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    private void abrelistarvenda() {
        ListaVenda emp = new ListaVenda();
        fecha();
        try {
            emp.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
