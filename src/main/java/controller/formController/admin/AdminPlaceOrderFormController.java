package controller.formController.admin;

import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.Setter;

public class AdminPlaceOrderFormController {
    public TableView itemsTable;
    public TextField itemSearchField;
    public TextField customerSearchField;
    public TableView cartTable;
    public Text qtyTxt;
    @Setter
    private AdminMainFormController adminMainFormController;

    public void btnAddToOrderOnAction(ActionEvent actionEvent) {
    }

    public void btnCompleteOrderOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadCheckoutForm();
        }
    }

    public void btnCancelOrderOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadAdminHomeForm();
        }
    }

    public void btnPlusQtyOnAction(ActionEvent actionEvent) {
        int qty=Integer.parseInt(qtyTxt.getText());
        qty++;
        qtyTxt.setText(String.valueOf(qty));
    }

    public void btnMinusQtyOnAction(ActionEvent actionEvent) {
        if (Integer.parseInt(qtyTxt.getText())>0) {
            int qty = Integer.parseInt(qtyTxt.getText());
            qty--;
            qtyTxt.setText(String.valueOf(qty));
        }
    }

    public void btnSearchProductOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchCustomerOnAction(ActionEvent actionEvent) {
    }

    public void btnManageCustomersOnAction(ActionEvent actionEvent) {
    }
}
