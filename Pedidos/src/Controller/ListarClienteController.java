/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ClienteDao;
import DAO.FornecedorDao;
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
import model.cliente;
import model.fornecedor;
import pedidos.AlteraFornecedor;
import pedidos.AlterarCliente;
import pedidos.ListarCliente;
import pedidos.ListarFornecedor;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author GuiManu
 */
public class ListarClienteController implements Initializable {

    
    @FXML
    private TableView<cliente> tbcliente;

    @FXML
    private TableColumn<cliente, Long> clmid;

    @FXML
    private TableColumn<cliente, String> clmnome;

    @FXML
    private TableColumn<cliente, String> clmcpf;

    @FXML
    private Button btcancela;

    @FXML
    private Button btdelete;

    @FXML
    private Button btatualiza;
    
    private cliente selecionado;
    
    
    @FXML
    private Button btalterar;
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
            deletacliente();
        });
        tbcliente.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selecionado = (cliente) newValue;
                
            }
        });
            btalterar.setOnMouseClicked((MouseEvent e)->{
            if(selecionado!=null){
            AlterarCliente alt = new AlterarCliente(selecionado);
            try {
                alt.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(ListarClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }else{
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Cliente Não Selecionado");
            a.show();
            }
        });
            btatualiza.setOnMouseClicked((MouseEvent e)->{
            initTable();
        });
    }    
    public void fecha(){
        ListarCliente.getStage().close();
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
        clmid.setCellValueFactory(new PropertyValueFactory("idcliente"));
        clmnome.setCellValueFactory(new PropertyValueFactory("nome"));
        clmcpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        
        tbcliente.setItems(atualizadaTabela());
    }
    
    public ObservableList<cliente> atualizadaTabela(){
        ClienteDao dao = new ClienteDao();
        return FXCollections.observableArrayList(dao.getList());
    }
    
    public void deletacliente(){
        if(selecionado != null){
            ClienteDao dao = new ClienteDao();
            dao.delete(selecionado);
            tbcliente.setItems(atualizadaTabela());
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Usuario deletado");
            a.show();
            
        }else{
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Selecione um Usuario");
            a.show();
        }
    }
    
}
