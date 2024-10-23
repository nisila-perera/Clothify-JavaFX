package controller.formController.admin;

import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lombok.Setter;

public class AdminProductManagerFormController {
    public TextField searchProductField;
    public TableView productTable;
    public TextField supplierNameField;
    public TextField supplierAddressField;
    public TextField supplierContactField;
    public TextField supplierEmailField;
    public TextField supplierEmailField1;

    @Setter
    private AdminMainFormController adminMainFormController;

    public void btnSearchProductOnAction(ActionEvent actionEvent) {
    }

    public void btnSelectProductOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateProductOnAction(ActionEvent actionEvent) {
    }

    public void btnGenerateNewIdOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteProductOnAction(ActionEvent actionEvent) {
    }

    public void btnAddProductOnAction(ActionEvent actionEvent) {
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadAdminHomeForm();
        }
    }

}
