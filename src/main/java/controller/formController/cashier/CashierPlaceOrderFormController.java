package controller.formController.cashier;

import javafx.event.ActionEvent;

public class CashierPlaceOrderFormController {
    private CashierMainFormController cashierMainFormController;

    public void setCashierMainFormController(CashierMainFormController controller) {
        this.cashierMainFormController = controller;
    }

    public void btnCompleteOrderOnAction(ActionEvent actionEvent) {
        this.cashierMainFormController.loadCashierCheckoutForm();
    }

    public void btnCancelOrderOnAction(ActionEvent actionEvent) {
        this.cashierMainFormController.loadCashierHomeForm();
    }

    public void btnManageCustomersOnAction(ActionEvent actionEvent) {
        cashierMainFormController.loadCashierCustomerManagerForm();
    }
}
