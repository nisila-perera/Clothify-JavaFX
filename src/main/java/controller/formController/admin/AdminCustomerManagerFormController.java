package controller.formController.admin;

import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.Setter;

public class AdminCustomerManagerFormController {
    public TextField searchCustomerField;
    public TableView customerTable;
    public TextField customerNameField;
    public TextField customerMobileField;
    public TextField customerEmailField;
    public TextField customerAddressField;
    public Text customerIdTxt;

    @Setter
    private AdminMainFormController adminMainFormController;

    public void btnSearchCustomerOnAction(ActionEvent actionEvent) {
    }

    public void btnSelectCustomerOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateCustomerOnAction(ActionEvent actionEvent) {
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadAdminHomeForm();
        }
    }

    public void btnAddCustomerAndContinueOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteCustomerOnAction(ActionEvent actionEvent) {
    }

    public void btnGenerateNewIdOnAction(ActionEvent actionEvent) {
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
    }
}
