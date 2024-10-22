package controller.formController.cashier;

import controller.modelController.CashierLoginController;
import controller.modelController.EmployeeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Employee;
import util.Encryptor;

import java.io.IOException;
import java.util.List;

public class CashierLoginFormController {

    public TextField cashierEmailField;

    public PasswordField cashierPasswordField;

    CashierLoginController loginController = new CashierLoginController();

    public void btnLoginOnAction() {
        List<Employee> list =  EmployeeController.getInstance().GetEmployee();
        for(Employee employee : list) {
            if (employee.getEmail().equals(cashierEmailField.getText()) && employee.getPassword().equals(new Encryptor().encryptString(cashierPasswordField.getText()))) {
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
            }
        }
        new Alert(Alert.AlertType.ERROR,"E-mail or Password is Incorrect").showAndWait();
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        ((Stage) cashierEmailField.getScene().getWindow()).close();
    }
}
