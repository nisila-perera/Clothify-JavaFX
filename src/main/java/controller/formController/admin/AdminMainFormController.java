package controller.formController.admin;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import lombok.Getter;

import java.io.IOException;
@Getter
public class AdminMainFormController {
    public Text activeAdminTxt;
    public BorderPane mainBorderPane;

    public void setActiveAdminName(String admin){
        activeAdminTxt.setText(admin);
        loadAdminHomeForm();
    }

    public void loadAdminHomeForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/admin_home.fxml"));
            AnchorPane content = loader.load();

            AdminHomeFormController adminHomeFormController = loader.getController();
            adminHomeFormController.setAdminMainFormController(this);
            adminHomeFormController.setAdminWelcomeTxt(activeAdminTxt.getText());

            mainBorderPane.setCenter(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadEmployeeManagerForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/admin_manage_employees.fxml"));
            AnchorPane content = loader.load();

            AdminEmployeeManagerFormController adminEmployeeManagerFormController = loader.getController();
            adminEmployeeManagerFormController.setAdminMainFormController(this);

            mainBorderPane.setCenter(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadCheckoutForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/admin_checkout.fxml"));
            AnchorPane content = loader.load();

            AdminCheckoutFormController adminCheckoutFormController = loader.getController();
            adminCheckoutFormController.setAdminMainFormController(this);

            mainBorderPane.setCenter(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadPlaceOrderForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/admin_place_order.fxml"));
            AnchorPane content = loader.load();

            AdminPlaceOrderFormController adminPlaceOrderFormController = loader.getController();
            adminPlaceOrderFormController.setAdminMainFormController(this);

            mainBorderPane.setCenter(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
