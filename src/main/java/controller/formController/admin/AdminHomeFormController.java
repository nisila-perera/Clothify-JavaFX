package controller.formController.admin;

import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;

public class AdminHomeFormController {
    public Text adminWelcomeTxt;
    @Setter
    private AdminMainFormController adminMainFormController;

    public void setAdminWelcomeTxt(String name){
        adminWelcomeTxt.setText("Welcome, "+name);
    }

    public void btnPlaceOrderFormOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadPlaceOrderForm();
        }
    }

    public void btnManageProductsFormOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadProductManagerForm();
        }
    }

    public void btnManageSuppliersFormOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadSupplierForm();
        }
    }

    public void btnGenerateReportsFormOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadViewReportsForm();
        }
    }

    public void btnManageCustomersFormOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadCustomerForm();
        }
    }

    public void btnManageEmployeesFormOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadEmployeeManagerForm();
        }
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) {
        ((Stage) adminWelcomeTxt.getScene().getWindow()).close();
    }
}