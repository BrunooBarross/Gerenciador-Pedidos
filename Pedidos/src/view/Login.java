/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pedidos.Pedidos;
/**
 * FXML Controller class
 *
 * @author GuiManu
 */
public class Login implements Initializable {

    
    @FXML
    private Button btsair;

    @FXML
    private Button btentrar;

    @FXML
    private TextField txusuario;

    @FXML
    private PasswordField txsenha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       btentrar.setOnMouseClicked((MouseEvent e)->{
           logar();
       });
       btentrar.setOnKeyPressed((KeyEvent e)->{
           if(e.getCode() == KeyCode.ENTER){
                logar();
            }
       });
       
       btsair.setOnMouseClicked((MouseEvent e)->{
           fecha();
       });
       btsair.setOnKeyPressed((KeyEvent e )->{
            if(e.getCode() == KeyCode.ENTER){
                fecha();
            }
       });
       
       txsenha.setOnKeyPressed((KeyEvent e)->{
           if(e.getCode() == KeyCode.ENTER){
                logar();
            }
       });
       
    }    
    
    public void fecha(){
        Pedidos.getStage().close();
    }
    public void logar(){
        if(txusuario.getText().equals("root") && txsenha.getText().equals("1234")){
               Principal p = new Principal();
               fecha();
               try {
                   p.start(new Stage());
               } catch (Exception ex) {
                   Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else{
               Alert alert = new Alert(AlertType.ERROR);
               alert.setTitle("Erro");
               alert.setHeaderText("Login Inválido");
               alert.setContentText("O Erro aconteceu devido ao usuario ser iniválido");
               alert.show();
           }
    }
}
