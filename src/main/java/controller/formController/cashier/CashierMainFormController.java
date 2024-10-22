package controller.formController.cashier;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class CashierMainFormController {

    public Text activeCashierTxt;

    @FXML
    private BorderPane mainBorderPane;


    public void setActiveCashierName(String employee){
        activeCashierTxt.setText(employee);
        loadCashierHomeForm();
    }

    public void loadPane(String path){
        try {
            AnchorPane content = FXMLLoader.load(getClass().getResource(path));
            mainBorderPane.setCenter(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadCashierHomeForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cashier/cashier_home.fxml"));
            AnchorPane content = loader.load();

            CashierHomeFormController cashierHomeController = loader.getController();
            cashierHomeController.setCashierMainFormController(this);
            cashierHomeController.setCashierWelcomeTxt(activeCashierTxt.getText());

            mainBorderPane.setCenter(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void loadPlaceOrderForm(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cashier/cashier_place_order.fxml"));
            AnchorPane content = loader.load();

            CashierPlaceOrderFormController cashierPlaceOrderFormController = loader.getController();
            cashierPlaceOrderFormController.setCashierMainFormController(this);

            mainBorderPane.setCenter(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadCashierCustomerManagerForm(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cashier/cashier_customer_manager.fxml"));
            AnchorPane content = loader.load();

            CashierCustomerManagerFormController cashierCustomerManagerFormController = loader.getController();
            cashierCustomerManagerFormController.setCashierMainFormController(this);

            mainBorderPane.setCenter(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadCashierCheckoutForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cashier/cashier_checkout.fxml"));
            AnchorPane content = loader.load();

            CashierCheckoutFormController cashierCheckoutFormController = loader.getController();
            cashierCheckoutFormController.setCashierMainFormController(this);

            mainBorderPane.setCenter(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
