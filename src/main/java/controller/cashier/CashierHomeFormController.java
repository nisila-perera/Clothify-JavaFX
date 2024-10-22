package controller.cashier;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CashierHomeFormController {

    public Text cashierWelcomeTxt;
    private CashierMainFormController cashierMainFormController;

    public void setCashierMainFormController(CashierMainFormController controller) {
        this.cashierMainFormController = controller;
    }

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
}
