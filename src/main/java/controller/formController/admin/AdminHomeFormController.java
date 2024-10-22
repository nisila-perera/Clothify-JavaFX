package controller.formController.admin;

import javafx.event.ActionEvent;
import javafx.scene.text.Text;
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
    }

    public void btnManageSuppliersFormOnAction(ActionEvent actionEvent) {
    }

    public void btnGenerateReportsFormOnAction(ActionEvent actionEvent) {
    }

    public void btnManageCustomersFormOnAction(ActionEvent actionEvent) {
    }

    public void btnManageEmployeesFormOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadEmployeeManagerForm();
        }
    }
}