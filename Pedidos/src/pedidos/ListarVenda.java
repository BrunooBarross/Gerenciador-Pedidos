/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author GuiManu
 */
public class ListarVenda extends Application {
    private static Stage stage;//janela

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("pedidos.ListarVenda.start()");
        Parent root = FXMLLoader.load(getClass().getResource("/view/ListarVenda.fxml"));//carrega o FXML
        Scene scene = new Scene(root);//cena
        stage.setTitle("Listar Produto");
        stage.setScene(scene);//coloca a cena em uma janela
        stage.show(); //abre a janela
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ListarVenda.stage = stage;
    }
}