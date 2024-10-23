package controller.formController.admin;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import lombok.Setter;

public class AdminProfileSettingsFormController {
    @Setter
    private AdminMainFormController adminMainFormController;

    public void btnUpdateProfileOnAction(ActionEvent actionEvent) {
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadAdminHomeForm();
        }
    }

    public void btnPasswordResetOnAction(MouseEvent mouseEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadPasswordResetForm();
        }
    }
}
