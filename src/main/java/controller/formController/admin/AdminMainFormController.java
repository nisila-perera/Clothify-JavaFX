package controller.formController.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;
@Getter
public class AdminMainFormController {
    public Text activeAdminTxt;
    public BorderPane mainBorderPane;

    public AdminMainFormController getInstance(){
        return this;
    }

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

    public void loadViewReportsForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/admin_view_reports.fxml"));
            AnchorPane content = loader.load();

            AdminViewReportsFormController adminViewReportsFormController = loader.getController();
            adminViewReportsFormController.setAdminMainFormController(this);

            mainBorderPane.setCenter(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadCustomerForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/admin_customer_manager.fxml"));
            AnchorPane content = loader.load();

            AdminCustomerManagerFormController adminCustomerManagerFormController = loader.getController();
            adminCustomerManagerFormController.setAdminMainFormController(this);

            mainBorderPane.setCenter(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadProductManagerForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/admin_product_manager.fxml"));
            AnchorPane content = loader.load();

            AdminProductManagerFormController adminProductManagerFormController = loader.getController();
            adminProductManagerFormController.setAdminMainFormController(this);

            mainBorderPane.setCenter(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadSupplierForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/admin_supplier_manager.fxml"));
            AnchorPane content = loader.load();

            AdminSupplierManagerFormController adminSupplierManagerFormController = loader.getController();
            adminSupplierManagerFormController.setAdminMainFormController(this);

            mainBorderPane.setCenter(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadProfileSettingForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/admin_profile_settings.fxml"));
            AnchorPane content = loader.load();

            AdminProfileSettingsFormController adminProfileSettingsFormController = loader.getController();
            adminProfileSettingsFormController.setAdminMainFormController(this);

            mainBorderPane.setCenter(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadPasswordResetForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/admin_password_reset.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            ((Stage) activeAdminTxt.getScene().getWindow()).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnHomeOnAction(ActionEvent actionEvent) {
        loadAdminHomeForm();
    }

    public void btnLoadEditProfileOnScreen(MouseEvent mouseEvent) {
        loadProfileSettingForm();
    }
}
