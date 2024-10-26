package controller.formController.admin;

import controller.modelController.OrderController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;
import model.Order;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminHomeFormController implements Initializable {
    public Text adminWelcomeTxt;
    public TableView recentOrdersTable;
    public TableColumn orderIdCol;
    public TableColumn customerNameCol;
    public TableColumn amountCol;
    public TableColumn dateCol;
    @Setter
    private AdminMainFormController adminMainFormController;

    public void setAdminWelcomeTxt(String name){
        adminWelcomeTxt.setText("Welcome, "+name);
    }

    public void btnPlaceOrderFormOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadPlaceOrderForm();
        }
    }

    public void btnManageProductsFormOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadProductManagerForm();
        }
    }

    public void btnManageSuppliersFormOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadSupplierForm();
        }
    }

    public void btnGenerateReportsFormOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadViewReportsForm();
        }
    }

    public void btnManageCustomersFormOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadCustomerForm();
        }
    }

    public void btnManageEmployeesFormOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadEmployeeManagerForm();
        }
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) {
        ((Stage) adminWelcomeTxt.getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderIdCol.setCellValueFactory(new PropertyValueFactory<>("orid"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("custname"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        loadRecentOrders();
    }

    public void loadRecentOrders() {
        List<Order> recentOrders = OrderController.getInstance().getRecentOrders(10);
        recentOrdersTable.setItems(FXCollections.observableArrayList(recentOrders));
    }
}