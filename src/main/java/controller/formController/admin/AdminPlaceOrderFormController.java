package controller.formController.admin;

import controller.modelController.CustomerController;
import controller.modelController.ProductController;
import controller.modelController.SupplierController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import lombok.Setter;
import model.Customer;
import model.Product;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AdminPlaceOrderFormController implements Initializable {
    public TableView<Product> itemsTable;
    public TextField itemSearchField;
    public TextField customerSearchField;
    public TableView cartTable;
    public Text qtyTxt;
    public TableColumn cartItemNameCol;
    public TableColumn cartQtyCol;
    public TableColumn cartPriceCol;
    public TableColumn cartTotalCol;
    public Text totalTxt;
    public ComboBox customerCmb;
    public TableColumn itemNameCol;
    public TableColumn itemQtyCol;
    public TableColumn itemDiscountCol;
    public TableColumn itemPrice;
    private Product product;
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
        String mobileNumber = customerSearchField.getText(); // Get text from the mobile number text field

        ObservableList<String> filteredCustomers = FXCollections.observableArrayList(
                CustomerController.getInstance()
                        .getCustomer()
                        .stream()
                        .filter(customer -> customer.getPhone().contains(mobileNumber))
                        .map(customer -> customer.getName())
                        .collect(Collectors.toList())
        );

        customerCmb.setItems(filteredCustomers);
    }

    public void btnManageCustomersOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadCustomerForm();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        itemNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemQtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        itemDiscountCol.setCellValueFactory(new PropertyValueFactory<>("discount"));
        itemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        itemsTable.getSelectionModel().selectedItemProperty().addListener(((observableValue, o, t1) ->{
            if(t1!=null){
                product = t1;
            }
        }));

        ObservableList<String> customers = FXCollections.observableArrayList(
                CustomerController.getInstance()
                        .getCustomer()
                        .stream()
                        .map(supplier -> supplier.getName())
                        .collect(Collectors.toList())
        );

        customerCmb.setItems(customers);
        loadProductsTable();
    }

    private void loadProductsTable(){
        List<Product> products = ProductController.getInstance().getProduct();
        System.out.println("Loading products: " + products.size());
        itemsTable.setItems(FXCollections.observableArrayList(products));
    }
}

