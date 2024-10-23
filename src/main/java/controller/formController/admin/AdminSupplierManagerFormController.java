package controller.formController.admin;

import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lombok.Setter;

public class AdminSupplierManagerFormController {
    public TextField searchSupplierField;
    public TableView supplierTable;
    public TextField supplierNameField;
    public TextField supplierAddressField;
    public TextField supplierContactField;
    public TextField supplierEmailField;
    @Setter
    private AdminMainFormController adminMainFormController;


    public void btnCancelOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadAdminHomeForm();
        }
    }

    public void btnGenerateNewIdOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchSupplierOnAction(ActionEvent actionEvent) {
    }

    public void btnSelectSupplierOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateSupllierOnAction(ActionEvent actionEvent) {
    }

    public void btnAddSupplierOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteSupplierOnAction(ActionEvent actionEvent) {

    }
}
