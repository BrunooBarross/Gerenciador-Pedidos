/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.FornecedorDao;
import DAO.ProdutoDao;
import java.net.URL;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.fornecedor;
import pedidos.AlteraFornecedor;
import pedidos.ListarFornecedor;
import view.Principal;

/**
 *
 * @author GuiManu
 */
public class ListarFornecedorController implements Initializable {

    @FXML
    private TableView<fornecedor> tbfornecedor;

    @FXML
    private TableColumn<fornecedor, Long> clmfornecedor;

    @FXML
    private TableColumn<fornecedor, String> clmrazao;

    @FXML
    private TableColumn<fornecedor, String> clmcnpj;

    @FXML
    private Button btcancela;

    @FXML
    private Button btatualiza;

    @FXML
    private Button btdelete; 
    
        @FXML
    private Button btalterar;
    
    private fornecedor selecionado;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initTable();
        
        btcancela.setOnMouseClicked((MouseEvent e)->{
            abreprincipal();
        
        });
        btcancela.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
              abreprincipal();  
            }
        
        });
        btdelete.setOnMouseClicked((MouseEvent e)->{
            deletafornecedor();
        });
        tbfornecedor.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selecionado = (fornecedor) newValue;
                
            }
        });
        btalterar.setOnMouseClicked((MouseEvent e)->{
            if(selecionado!=null){
            AlteraFornecedor alt = new AlteraFornecedor(selecionado);
            try {
                alt.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(ListarFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }else{
            Alert a = new Alert(AlertType.CONFIRMATION);
            a.setHeaderText("Fornecedor Não Selecionado");
            a.show();
            }
        });
        btatualiza.setOnMouseClicked((MouseEvent e)->{
            initTable();
        });
    } 
   
    public void fecha(){
        ListarFornecedor.getStage().close();
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
        clmfornecedor.setCellValueFactory(new PropertyValueFactory("idfornecedor"));
        clmrazao.setCellValueFactory(new PropertyValueFactory("razao"));
        clmcnpj.setCellValueFactory(new PropertyValueFactory("cnpj"));
        
        tbfornecedor.setItems(atualizadaTabela());
    }
    
    public ObservableList<fornecedor> atualizadaTabela(){
        FornecedorDao dao = new FornecedorDao();
        return FXCollections.observableArrayList(dao.getList());
    }
    
    public void deletafornecedor(){
        if(selecionado != null){
            FornecedorDao dao = new FornecedorDao();
            dao.delete(selecionado);
            tbfornecedor.setItems(atualizadaTabela());
            Alert a = new Alert(AlertType.CONFIRMATION);
            a.setHeaderText("Usuario deletado");
            a.show();
            
        }else{
            Alert a = new Alert(AlertType.WARNING);
            a.setHeaderText("Selecione um Usuario");
            a.show();
        }
    }
}
