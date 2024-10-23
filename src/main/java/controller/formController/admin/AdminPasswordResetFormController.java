package controller.formController.admin;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;

public class AdminPasswordResetFormController {
    public TextField emailTxt;
    public TextField otpTxt;
    public PasswordField newPasswordTxt;
    public PasswordField newPasswordConfrimTxt;

    @Setter
    private AdminMainFormController adminMainFormController;

    public void btnUpdatePasswordOnAction(ActionEvent actionEvent) {
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        ((Stage) emailTxt.getScene().getWindow()).close();
    }

    public void btnVerifyOnAction(ActionEvent actionEvent) {
    }

    public void btnSendOnAction(ActionEvent actionEvent) {
    }
}
