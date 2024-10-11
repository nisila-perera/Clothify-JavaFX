package controller.cashier;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class CashierMainFormController implements Initializable {

    @FXML
    private BorderPane mainBorderPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPane("/view/cashier/cashier_home.fxml");
    }

    private void loadPane(String path){
        try {
            AnchorPane content = FXMLLoader.load(getClass().getResource(path));
            mainBorderPane.setCenter(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
