package controller.formController.cashier;

import com.jfoenix.controls.JFXButton;
import entity.EmployeeEntity;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;
import repository.custom.EmployeeDao;
import repository.custom.impl.EmployeeDaoImpl;
import util.EmailUtil;
import util.Encryptor;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class CashierPasswordResetFormController implements Initializable {
    public TextField emailTxt;
    public TextField otpTxt;
    public PasswordField newPasswordTxt;
    public PasswordField newPasswordConfrimTxt;
    public JFXButton verifyBtn;
    public JFXButton updateBtn;

    @Setter
    private CashierPasswordResetFormController cashierPasswordResetFormController;

    private String generatedOTP;
    private EmployeeEntity currentEmployee;
    private final EmployeeDao employeeDao = new EmployeeDaoImpl();

    public void btnSendOnAction(ActionEvent actionEvent) {
        String email = emailTxt.getText().trim();

        if (email.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter email address");
            return;
        }

        for (EmployeeEntity employee : employeeDao.getAll()) {
            if (employee.getEmail().equals(email)) {
                currentEmployee = employee;
                break;
            }
        }

        if (currentEmployee == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "No account found with this email");
            return;
        }

        generatedOTP = generateOTP();

        if (EmailUtil.sendOTPEmail(email, generatedOTP)) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "OTP has been sent to your email");
            otpTxt.setDisable(false);
            verifyBtn.setDisable(false);
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to send OTP");
        }
    }

    public void btnVerifyOnAction(ActionEvent actionEvent) {
        String enteredOTP = otpTxt.getText().trim();

        if (enteredOTP.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter OTP");
            return;
        }

        if (enteredOTP.equals(generatedOTP)) {
            newPasswordTxt.setDisable(false);
            newPasswordConfrimTxt.setDisable(false);
            showAlert(Alert.AlertType.INFORMATION, "Success", "OTP verified successfully");
            newPasswordTxt.setDisable(false);
            newPasswordConfrimTxt.setDisable(false);
            updateBtn.setDisable(false);
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid OTP");
        }
    }

    public void btnUpdatePasswordOnAction(ActionEvent actionEvent) {
        String newPass = new Encryptor().encryptString(newPasswordTxt.getText());
        String confirmPass = new Encryptor().encryptString(newPasswordConfrimTxt.getText());

        if (newPass.isEmpty() || confirmPass.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill all fields");
            return;
        }

        if (!newPass.equals(confirmPass)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Passwords don't match");
            return;
        }

        currentEmployee.setPassword(newPass);
        if (employeeDao.update(currentEmployee)) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Password updated successfully");
            ((Stage) emailTxt.getScene().getWindow()).close();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update password");
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        ((Stage) emailTxt.getScene().getWindow()).close();
    }

    private String generateOTP() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(999999));
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        verifyBtn.setDisable(true);
        newPasswordTxt.setDisable(true);
        newPasswordConfrimTxt.setDisable(true);
        otpTxt.setDisable(true);
        updateBtn.setDisable(true);
    }
}
