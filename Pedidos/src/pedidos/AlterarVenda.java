/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos;

import Controller.AlteraProdutoController;
import Controller.AlterarVendaController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.produto;
import model.venda;

/**
 *
 * @author GuiManu
 */
public class AlterarVenda extends Application{
    private static Stage stage;//janela
    
    public AlterarVenda(venda p){
        AlterarVendaController.setP(p);
    }

    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AlterarVenda.fxml"));//carrega o FXML
        Scene scene = new Scene(root);//cena
        stage.setTitle("Alterar Venda");
        stage.setScene(scene);//coloca a cena em uma janela
        stage.show(); //abre a janela
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        AlterarVenda.stage = stage;
    }
    
}

