package controller.cashier.order;

import controller.cashier.CashierMainFormController;
import javafx.event.ActionEvent;

public class CashierCheckoutFormController {
    private CashierMainFormController cashierMainFormController;

    public void setCashierMainFormController(CashierMainFormController controller) {
        this.cashierMainFormController = controller;
    }

    public void btnCompleteOrderOnAction(ActionEvent actionEvent) {
    }

    public void btnCancelOrderOnAction(ActionEvent actionEvent) {
        this.cashierMainFormController.loadPlaceOrderForm();
    }
}
