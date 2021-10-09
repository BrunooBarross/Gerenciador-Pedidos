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
import model.produto;
import model.venda;
import pedidos.AlteraProduto;
import pedidos.AlterarVenda;
import pedidos.ListarProduto;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author GuiManu
 */
public class ListarVendaController implements Initializable {

    @FXML
    private TableView<venda> tbvenda;
    
    @FXML
    private TableColumn<venda, Long> clmid;

    @FXML
    private TableColumn<venda, String> clmcliente;

    @FXML
    private TableColumn<venda, String> clmempresa;

    @FXML
    private TableColumn<venda, Long> clmidproduto;

    @FXML
    private TableColumn<venda, Double> clmvalor;

    @FXML
    private TableColumn<venda, String> clmestado;

    @FXML
    private Button btcancelar;

    @FXML
    private Button btatualiza;

    @FXML
    private Button btalterar;
    
    private venda selecionado;
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
        tbvenda.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selecionado = (venda) newValue;
                
            }
        });
            btalterar.setOnMouseClicked((MouseEvent e)->{
            if(selecionado!=null){
            AlterarVenda alt = new AlterarVenda (selecionado);
            try {
                alt.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(ListarVendaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }else{
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Venda Não Selecionada");
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
        clmid.setCellValueFactory(new PropertyValueFactory("idveneda"));
        clmcliente.setCellValueFactory(new PropertyValueFactory("cpf"));
        clmempresa.setCellValueFactory(new PropertyValueFactory("cnpj"));
        clmidproduto.setCellValueFactory(new PropertyValueFactory("idproduto"));
        clmvalor.setCellValueFactory(new PropertyValueFactory("valorvenda"));
        clmestado.setCellValueFactory(new PropertyValueFactory("estado"));
        
        tbvenda.setItems(atualizadaTabela());

    }
    
    public ObservableList<venda> atualizadaTabela(){
        VendaDao dao = new VendaDao();
        return FXCollections.observableArrayList(dao.getList());
    }
    
    
    }
       

