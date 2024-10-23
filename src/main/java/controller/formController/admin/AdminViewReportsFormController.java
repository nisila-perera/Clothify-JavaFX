package controller.formController.admin;

import javafx.event.ActionEvent;

public class AdminViewReportsFormController {

    private AdminMainFormController adminMainFormController;

    public void setAdminMainFormController(AdminMainFormController adminMainFormController) {
        this.adminMainFormController = adminMainFormController;
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadAdminHomeForm();
        }
    }
}
