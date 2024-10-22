package controller.cashier.customer;

import controller.cashier.CashierMainFormController;
import javafx.event.ActionEvent;

public class CashierCustomerManagerFormController {
    private CashierMainFormController cashierMainFormController;

    public void setCashierMainFormController(CashierMainFormController controller) {
        this.cashierMainFormController = controller;
    }

    public void btnCancelOrderOnAction(ActionEvent actionEvent) {
        cashierMainFormController.loadCashierHomeForm();
    }

    public void btnAddCustomerAndContinueOnAction(ActionEvent actionEvent) {
        cashierMainFormController.loadPlaceOrderForm();
        //Pass selected customer to the form
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
        //Add the logic to add customer
    }

    public void btnSelectCustomerOnAction(ActionEvent actionEvent) {
        //Add the logic to load the select customer
    }

    public void btnUpdateCustomerOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchCustomerOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteCustomerOnAction(ActionEvent actionEvent) {
    }
}
