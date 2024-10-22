package controller.formController.admin;

import javafx.event.ActionEvent;

public class AdminCheckoutFormController {
    private AdminMainFormController adminMainFormController;

    public void setAdminMainFormController(AdminMainFormController adminMainFormController) {
        this.adminMainFormController = adminMainFormController;
    }

    public void btnCompleteOrderOnAction(ActionEvent actionEvent) {
    }

    public void btnCancelOrderOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadPlaceOrderForm();
        }
    }
}
