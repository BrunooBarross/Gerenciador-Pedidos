/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.FornecedorDao;
import DAO.ProdutoDao;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.fornecedor;
import model.produto;
import pedidos.CadastrarFornecedor;
import pedidos.CadastrarProduto;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author GuiManu
 */
public class CadastrarProdutoController implements Initializable {

    @FXML
    private TextField txitem;

    @FXML
    private TextField txvalorproduto;

    @FXML
    private TextField txdescricao;

    @FXML
    private TableView<fornecedor> tbfornecedor;
    
    @FXML
    private TableColumn<fornecedor, Long> clmid;

    @FXML
    private TableColumn<fornecedor, String> clmrazao;

    @FXML
    private TableColumn<fornecedor, String> clmcnpj;

    @FXML
    private Button btcancelar;

    @FXML
    private Button btcadastrar;
    
    @FXML
    private Button btlistarfornecedor;
    
    private fornecedor selecionado;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initTable();
        
        btcancelar.setOnMouseClicked((MouseEvent e)->{
            abreprincipal();
        
        });
        btcancelar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                abreprincipal();
            }
        
        });
        btcadastrar.setOnMouseClicked((MouseEvent e)->{
            cadastrarproduto();
        
        });
        btcadastrar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                cadastrarproduto();
            }
        
        });
        
        tbfornecedor.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selecionado = (fornecedor) newValue;
                
            }
        });
    } 
    public void cadastrarproduto(){
        if(selecionado != null){
            if(txvalorproduto.getText().isEmpty() || txitem.getText().isEmpty() || txdescricao.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Preencha todos os campos");
                    alert.show();
            
            }else{
                
                    
                    
                    String item = txitem.getText();
            Double valorproduto = Double.parseDouble(txvalorproduto.getText());
            String descricao = txdescricao.getText();
            String idfornecedor = tbfornecedor.getSelectionModel().getSelectedItem().getCnpj();
            
            produto p = new produto(item,valorproduto,descricao,idfornecedor);
            ProdutoDao dao = new ProdutoDao();
            if(dao.add(p)){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Produto cadastrado");
                    abreprincipal();
                    alert.show();
                    fecha();
                }else{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Produto não cadastrado");
                    alert.show();
                }
            }
        }else{
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Selecione um Fornecedor");
            a.show();
        }
        
        
    }
    public void fecha(){
        CadastrarProduto.getStage().close();
    }
    public void abreprincipal(){
        Principal a = new Principal();
        fecha();
        try {
            a.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadastrarProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initTable(){
        clmid.setCellValueFactory(new PropertyValueFactory("idfornecedor"));
        clmrazao.setCellValueFactory(new PropertyValueFactory("razao"));
        clmcnpj.setCellValueFactory(new PropertyValueFactory("cnpj"));
        
        tbfornecedor.setItems(atualizadaTabela());
    }
    
    public ObservableList<fornecedor> atualizadaTabela(){
        FornecedorDao dao = new FornecedorDao();
        return FXCollections.observableArrayList(dao.getList());
    }
}
