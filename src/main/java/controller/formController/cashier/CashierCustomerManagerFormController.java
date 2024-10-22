package controller.formController.cashier;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lombok.Setter;

public class CashierCustomerManagerFormController {
    public TextField itemSearchField;
    public TextField itemSearchField1;
    public TextField itemSearchField11;
    public TextField itemSearchField111;
    public TextField itemSearchField1111;
    @Setter
    private CashierMainFormController cashierMainFormController;

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
