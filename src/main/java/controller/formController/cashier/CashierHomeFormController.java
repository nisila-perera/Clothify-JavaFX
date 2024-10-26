package controller.formController.cashier;

import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;

public class CashierHomeFormController {

    public Text cashierWelcomeTxt;
    @Setter
    private CashierMainFormController cashierMainFormController;

    public void btnPlaceOrderFormOnAction(ActionEvent actionEvent) {
        if (cashierMainFormController != null) {
            cashierMainFormController.loadPlaceOrderForm();
        }
    }

    public void setCashierWelcomeTxt(String name){
        cashierWelcomeTxt.setText(name);
    }

    public void btnManageCustomersOnAction(ActionEvent actionEvent) {
        cashierMainFormController.loadCashierCustomerManagerForm();
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) {
        ((Stage) cashierWelcomeTxt.getScene().getWindow()).close();
    }
}
