package controller.cashier.login;

import controller.cashier.CashierMainFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CashierLoginFormController {

    public TextField cashierEmailField;
    public PasswordField cashierPasswordField;

    CashierLoginController loginController = new CashierLoginController();

    public void btnLoginOnAction() throws SQLException {

        if (loginController.cashierLoginValidate(cashierEmailField.getText(),cashierPasswordField.getText())){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cashier/cashier_main.fxml"));
                Parent root = loader.load();

                CashierMainFormController dashboardController = loader.getController();
                dashboardController.setActiveCashierName(loginController.getCashierName());

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ((Stage) cashierEmailField.getScene().getWindow()).close();
        }else {
            //System.out.println("Error Login");
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        ((Stage) cashierEmailField.getScene().getWindow()).close();
    }
}
