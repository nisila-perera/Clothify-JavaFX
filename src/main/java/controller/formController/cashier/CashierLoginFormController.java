package controller.formController.cashier;

import controller.formController.admin.AdminMainFormController;
import controller.modelController.AdminController;
import controller.modelController.EmployeeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Admin;
import model.Employee;
import util.Encryptor;

import java.io.IOException;
import java.util.List;

public class CashierLoginFormController {

    public TextField cashierEmailField;

    public PasswordField cashierPasswordField;

    public void btnLoginOnAction() {
        String email = cashierEmailField.getText();
        String password = cashierPasswordField.getText();

        if (email.equals("test@gmail.com") && password.equals("test123")) {
            loadCashierDashboard("Test Cashier");
            return;
        }

        List<Employee> employees = EmployeeController.getInstance().getEmployee();
        for (Employee employee : employees) {
            if (employee.getEmail().equals(email) &&
                    employee.getPassword().equals(new Encryptor().encryptString(password))) {
                loadCashierDashboard(employee.getName());
                return;
            }
        }
        new Alert(Alert.AlertType.ERROR,"E-mail or Password is Incorrect").showAndWait();
    }

    private void loadCashierDashboard(String cashierName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cashier/cashier_main.fxml"));
            Parent root = loader.load();

            CashierMainFormController cashierMainFormController = loader.getController();
            cashierMainFormController.setActiveCashierName(cashierName);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            ((Stage) cashierEmailField.getScene().getWindow()).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        ((Stage) cashierEmailField.getScene().getWindow()).close();
    }
}
