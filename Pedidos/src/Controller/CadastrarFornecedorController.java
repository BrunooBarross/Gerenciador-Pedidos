/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.FornecedorDao;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.fornecedor;
import pedidos.CadastrarFornecedor;
import view.Principal;
/**
 * FXML Controller class
 *
 * @author GuiManu
 */
public class CadastrarFornecedorController implements Initializable {

    @FXML
    private TextField txrazao;

    @FXML
    private TextField txcnpj;

    @FXML
    private Button btcancelar;

    @FXML
    private Button btcadastrar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btcancelar.setOnMouseClicked((MouseEvent e)->{
            abreprincipal();
        
        });
        btcancelar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                abreprincipal();
            }
        
        });
        btcadastrar.setOnMouseClicked((MouseEvent e)->{
            cadastrarfornecedor();
        
        });
        btcadastrar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                cadastrarfornecedor();
            }
        
        });
       
                
        // TODO
    }    
    public void cadastrarfornecedor(){
        String razao = txrazao.getText();
        String cnpj = txcnpj.getText();
        if (razao.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Razão Social vazia");
            alert.show();
        }else{
        if (cnpj.matches("^[0-9]*$")){
            if(isCNPJ(cnpj)){
                fornecedor p = new fornecedor(razao,cnpj);
                FornecedorDao dao = new FornecedorDao();
                if(dao.add(p)){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Fornecedor cadastrado");
                    abreprincipal();
                    alert.show();
                    fecha();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Fornecedor não cadastrado");
                    alert.show();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("CNPJ invalido");
                alert.show(); 
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Apenas numeros no CNPJ");
            alert.show();
        }    
        }   
    }
        
    public void fecha(){
        CadastrarFornecedor.getStage().close();
    }
    public void abreprincipal(){
        Principal a = new Principal();
        fecha();
        try {
            a.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadastrarFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


  public boolean isCNPJ(String CNPJ) {
// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
    if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
        CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
        CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
        CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
        CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
       (CNPJ.length() != 14))
       return(false);

    char dig13, dig14;
    int sm, i, r, num, peso;

// "try" - protege o código para eventuais erros de conversao de tipo (int)
    try {
// Calculo do 1o. Digito Verificador
      sm = 0;
      peso = 2;
      for (i=11; i>=0; i--) {
// converte o i-ésimo caractere do CNPJ em um número:
// por exemplo, transforma o caractere '0' no inteiro 0
// (48 eh a posição de '0' na tabela ASCII)
        num = (int)(CNPJ.charAt(i) - 48);
        sm = sm + (num * peso);
        peso = peso + 1;
        if (peso == 10)
           peso = 2;
      }

      r = sm % 11;
      if ((r == 0) || (r == 1))
         dig13 = '0';
      else dig13 = (char)((11-r) + 48);

// Calculo do 2o. Digito Verificador
      sm = 0;
      peso = 2;
      for (i=12; i>=0; i--) {
        num = (int)(CNPJ.charAt(i)- 48);
        sm = sm + (num * peso);
        peso = peso + 1;
        if (peso == 10)
           peso = 2;
      }

      r = sm % 11;
      if ((r == 0) || (r == 1))
         dig14 = '0';
      else dig14 = (char)((11-r) + 48);

// Verifica se os dígitos calculados conferem com os dígitos informados.
      if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
         return(true);
      else return(false);
    } catch (InputMismatchException erro) {
        return(false);
    }
  }
    
}
