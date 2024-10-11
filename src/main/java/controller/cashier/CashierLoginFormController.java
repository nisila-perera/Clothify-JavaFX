package controller.cashier;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CashierLoginFormController {

    public TextField cashierEmailField;
    public TextField cashierPasswordField;
    CashierLoginController loginController = new CashierLoginController();

    public void btnLoginOnAction() throws SQLException {

        if (loginController.cashierLoginValidate(cashierEmailField.getText(),cashierPasswordField.getText())){
            Stage stage = new Stage();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/cashier/cashier_main.fxml"))));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            System.out.println("Error Login");
        }

    }
}
