package controller.formController.admin;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;

public class AdminEmployeeManagerFormController {

    public TextField empNameTxt;
    public TextField empMobileTxt;
    public TextField empEmailTxt;
    public TextField empAddressTxt;
    public PasswordField empPasswordTxt;
    public TableView empTable;
    public TextField empSearchField;
    @Setter
    private AdminMainFormController adminMainFormController;

    public void btnSearchEmpOnAction(ActionEvent actionEvent) {
    }

    public void btnEmpSelectOnAction(ActionEvent actionEvent) {
    }

    public void btnAddEmpOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateEmpOnAction(ActionEvent actionEvent) {
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadAdminHomeForm();
        }
    }

    public void btnDeleteEmpOnAction(ActionEvent actionEvent) {
    }

    public void btnGenerateNewIdOnAction(ActionEvent actionEvent) {
    }
}
