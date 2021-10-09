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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.fornecedor;
import model.produto;
import pedidos.AlteraFornecedor;
import pedidos.AlteraProduto;

/**
 * FXML Controller class
 *
 * @author GuiManu
 */
public class AlteraProdutoController implements Initializable {

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
    
    @FXML
    private Label lbid;
    
    private fornecedor selecionado;
    
        private static produto p;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initProduto();
        initTable();
        
        btcancelar.setOnMouseClicked((MouseEvent e)->{
            fecha();
        });
        
        btcadastrar.setOnMouseClicked((MouseEvent e)->{
            atualiza();
        });
         tbfornecedor.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selecionado = (fornecedor) newValue;
                
            }
        });
        
    }    
    public void initProduto(){
        lbid.setText(p.getIdproduto().toString());
        txitem.setText(p.getItem());
        txvalorproduto.setText(p.getValorproduto().toString());
        txdescricao.setText(p.getDescricao());
        
    }

    
    public static produto getP() {
        return p;
    }

    public static void setP(produto p) {
        AlteraProdutoController.p = p;
    }

    public void fecha(){
        AlteraProduto.getStage().close();
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
    public void atualiza(){
        if(selecionado != null){
            
            if(txvalorproduto.getText().isEmpty() || txitem.getText().isEmpty() || txdescricao.getText().isEmpty() || txdescricao.getText().replaceAll("\\s","").length()==0 || txitem.getText().replaceAll("\\s","").length()==0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Preencha todos os campos");
                    alert.show();
            
            }else{
                
                    
        Long idproduto = Long.parseLong(lbid.getText());
        String item = txitem.getText(), descricao = txdescricao.getText();
        Double valorproduto = Double.parseDouble(txvalorproduto.getText().replaceAll(",", ".").replaceAll("[^0-9.]", ""));
        String idfornecedor = tbfornecedor.getSelectionModel().getSelectedItem().getCnpj().toString();
        
        
        produto p = new produto(idproduto,item,valorproduto,descricao,idfornecedor);
        ProdutoDao dao = new ProdutoDao();
        if(dao.update(p)){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Produto Alterado");
                    alert.show();
                    fecha();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Produto não Alterado");
                    alert.show();
                }
                   
            }
        }else{
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Selecione um Fornecedor");
            a.show();
        }
            
    }
    
}
