/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ClienteDao;
import DAO.FornecedorDao;
import DAO.ProdutoDao;
import DAO.VendaDao;
import java.net.URL;
import java.util.Date;
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
import model.produto;
import model.venda;
import pedidos.CadastraVenda;
import pedidos.CadastrarProduto;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author GuiManu
 */
public class CadastrarVendaController implements Initializable {

        @FXML
    private TableView<cliente> tbcliente;

    @FXML
    private TableColumn<cliente, Long> clmidcliente;

    @FXML
    private TableColumn<cliente, String> clmnome;

    @FXML
    private TableColumn<cliente, String> clmcpf;

    @FXML
    private TableView<produto> tbproduto;

    @FXML
    private TableColumn<produto, Long> clmidproduto;

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
    private Button btcadastrar;
    
    private cliente selecionado;
    
    private produto seleciona;
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
        btcadastrar.setOnMouseClicked((MouseEvent e)->{
            cadastrarvenda();
        
        });
        btcadastrar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                cadastrarvenda();
            }
        
        });
        
        tbcliente.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selecionado = (cliente) newValue;
                
            }
        });
        tbproduto.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                seleciona = (produto) newValue;
                
            }
        });
    }    
    public void cadastrarvenda(){
        if(selecionado != null){
            if(seleciona != null){
            String cpf = tbcliente.getSelectionModel().getSelectedItem().getCpf();
            Double valorvenda = Double.parseDouble(tbproduto.getSelectionModel().getSelectedItem().getValorproduto().toString());
            String cnpj = tbproduto.getSelectionModel().getSelectedItem().getIdfornecedor();
            Long idproduto = tbproduto.getSelectionModel().getSelectedItem().getIdproduto();
            String estado = "Efetuada";
            venda p = new venda(cpf,cnpj,idproduto,valorvenda,estado);
            VendaDao dao = new VendaDao();
            if(dao.add(p)){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Venda cadastrada");
                    abreprincipal();
                    alert.show();
                    fecha();
                }else{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Venda não cadastrada");
                    alert.show();
                }
            }else{
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setHeaderText("Selecione um produto");
                a.show();
            }
           
        }else{
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Selecione um Cliente");
            a.show();
        }
        
        
    }
    public void fecha(){
        CadastraVenda.getStage().close();
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
        clmidcliente.setCellValueFactory(new PropertyValueFactory("idcliente"));
        clmnome.setCellValueFactory(new PropertyValueFactory("nome"));
        clmcpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        
        tbcliente.setItems(atualizadaTabela());
        
        clmidproduto.setCellValueFactory(new PropertyValueFactory("idproduto"));
        clmitem.setCellValueFactory(new PropertyValueFactory("item"));
        clmvalor.setCellValueFactory(new PropertyValueFactory("valorproduto"));
        clmdescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
        clmcnpj.setCellValueFactory(new PropertyValueFactory("idfornecedor"));
        
        tbproduto.setItems(atualizadaTabelap());
    }
    
    public ObservableList<cliente> atualizadaTabela(){
        ClienteDao dao = new ClienteDao();
        return FXCollections.observableArrayList(dao.getList());
    }
    public ObservableList<produto> atualizadaTabelap(){
        ProdutoDao dao = new ProdutoDao();
        return FXCollections.observableArrayList(dao.getList());
    }
}
