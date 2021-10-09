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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.fornecedor;
import model.produto;
import pedidos.AlteraFornecedor;
import pedidos.AlteraProduto;
import pedidos.ListarFornecedor;
import pedidos.ListarProduto;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author GuiManu
 */
public class ListarProdutoController implements Initializable {

    
        @FXML
    private TableView<produto> tbproduto;

    @FXML
    private TableColumn<produto, Long> clmid;

    @FXML
    private TableColumn<produto, String> clmitem;

    @FXML
    private TableColumn<produto, Double> clmvalor;

    @FXML
    private TableColumn<produto, String> clmdescricao;

    @FXML
    private TableColumn<produto, String> clmcnpj;

    @FXML
    private Button btcancela;

    @FXML
    private Button btatualiza;

    @FXML
    private Button btdeleta;
    
    @FXML
    private Button btalterar;
    
    private produto selecionado;
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
        btdeleta.setOnMouseClicked((MouseEvent e)->{
            deletaproduto();
        });
        tbproduto.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selecionado = (produto) newValue;
                
            }
        });
            btalterar.setOnMouseClicked((MouseEvent e)->{
            if(selecionado!=null){
            AlteraProduto alt = new AlteraProduto (selecionado);
            try {
                alt.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(ListarFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }else{
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Produto Não Selecionado");
            a.show();
            }
        });
        btatualiza.setOnMouseClicked((MouseEvent e)->{
            initTable();
        });
    } 
   
    public void fecha(){
        ListarProduto.getStage().close();
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
        clmid.setCellValueFactory(new PropertyValueFactory("idproduto"));
        clmitem.setCellValueFactory(new PropertyValueFactory("item"));
        clmvalor.setCellValueFactory(new PropertyValueFactory("valorproduto"));
        clmdescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
        clmcnpj.setCellValueFactory(new PropertyValueFactory("idfornecedor"));
        
        tbproduto.setItems(atualizadaTabela());

    }
    
    public ObservableList<produto> atualizadaTabela(){
        ProdutoDao dao = new ProdutoDao();
        return FXCollections.observableArrayList(dao.getList());
    }
    
    public void deletaproduto(){
        if(selecionado != null){
            ProdutoDao dao = new ProdutoDao();
            dao.delete(selecionado);
            tbproduto.setItems(atualizadaTabela());
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Produto deletado");
            a.show();
            
        }else{
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Selecione um Produto");
            a.show();
        }
    }
    }    

