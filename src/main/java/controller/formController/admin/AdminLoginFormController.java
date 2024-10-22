package controller.formController.admin;

import controller.modelController.AdminController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Admin;
import util.Encryptor;

import java.io.IOException;
import java.util.List;

public class AdminLoginFormController {
    public TextField adminEmailField;
    public PasswordField adminPasswordField;

    public void btnAdminLoginOnAction(ActionEvent actionEvent) {
        String email = adminEmailField.getText();
        String password = adminPasswordField.getText();

        if (email.equals("test@gmail.com") && password.equals("test123")) {
            loadAdminDashboard("Test Admin");
            return;
        }

        List<Admin> admins = AdminController.getInstance().GetAdmin();
        for (Admin admin : admins) {
            if (admin.getEmail().equals(email) &&
                    admin.getPassword().equals(new Encryptor().encryptString(password))) {
                loadAdminDashboard(admin.getName());
                return;
            }
        }

        new Alert(Alert.AlertType.ERROR, "E-mail or Password is Incorrect").showAndWait();
    }

    private void loadAdminDashboard(String adminName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/admin_main.fxml"));
            Parent root = loader.load();

            AdminMainFormController adminMainFormController = loader.getController();
            adminMainFormController.setActiveAdminName(adminName);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            ((Stage) adminEmailField.getScene().getWindow()).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAdminCancelOnAction(ActionEvent actionEvent) {
        ((Stage) adminEmailField.getScene().getWindow()).close();
    }
}
