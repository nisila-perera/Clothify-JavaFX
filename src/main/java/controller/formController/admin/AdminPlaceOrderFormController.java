package controller.formController.admin;

import com.jfoenix.controls.JFXButton;
import controller.modelController.CustomerController;
import controller.modelController.OrderController;
import controller.modelController.ProductController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import lombok.Setter;
import model.Order;
import model.OrderDetails;
import model.Product;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AdminPlaceOrderFormController implements Initializable {
    public TableView<Product> itemsTable;
    public Text qtyTxt;
    public TextField itemSearchField;
    public TextField customerSearchField;
    public TableView<OrderDetails> cartTable;
    public TableColumn<OrderDetails, String> cartItemNameCol;
    public TableColumn<OrderDetails, Integer> cartQtyCol;
    public TableColumn<OrderDetails, Double> cartPriceCol;
    public TableColumn<OrderDetails, Double> cartTotalCol;
    public Text totalTxt;
    public ComboBox customerCmb;
    public TableColumn itemNameCol;
    public TableColumn itemQtyCol;
    public TableColumn itemDiscountCol;
    public TableColumn itemPrice;
    public Text orderIdTxt;
    public JFXButton addCustomerBtn;
    public Text pleaseSelectTxt;
    public Text customerNameTxt;
    private Product product;
    private Product selectedProduct;
    private OrderDetails selectedCartItem;
    @Setter
    private AdminMainFormController adminMainFormController;
    private List<OrderDetails> cartList = OrderController.getInstance().getCart();

    public void btnAddToOrderOnAction(ActionEvent actionEvent) {
        if (selectedProduct == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a product first").showAndWait();
            return;
        }

        String qtyText = qtyTxt.getText();
        if (qtyText == null || qtyText.isEmpty() || qtyText.equals("0")) {
            new Alert(Alert.AlertType.ERROR, "Please enter a valid quantity").showAndWait();
            return;
        }

        int quantity = Integer.parseInt(qtyText);
        if (quantity > selectedProduct.getQty()) {
            new Alert(Alert.AlertType.ERROR, "Not enough stock available").showAndWait();
            return;
        }

        OrderDetails newItem = new OrderDetails(
                selectedProduct.getId(),
                selectedProduct.getName(),
                orderIdTxt.getText(),
                quantity,
                selectedProduct.getPrice()
        );

        double totalPrice = quantity * selectedProduct.getPrice();
        double discount = (totalPrice/100) * selectedProduct.getDiscount();
        newItem.setPrice(totalPrice);
        newItem.setDiscount(discount);

        cartList.add(newItem);

        cartTable.setItems(FXCollections.observableArrayList(cartList));
        cartTable.refresh();

        OrderController.getInstance().setTotal(totalPrice-discount);
        totalTxt.setText(OrderController.getInstance().getTotal());

        selectedProduct = null;
        qtyTxt.setText("0");
        itemsTable.getSelectionModel().clearSelection();
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
        String mobileNumber = customerSearchField.getText();

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

        OrderController.getInstance().getCart(0);
        cartList = OrderController.getInstance().getCart();

        orderIdTxt.setText(OrderController.getInstance().generateId());

        customerNameTxt.setText("Guest Order");
        pleaseSelectTxt.setVisible(true);
        addCustomerBtn.setDisable(false);
        customerCmb.setValue(null);

        itemNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemQtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        itemDiscountCol.setCellValueFactory(new PropertyValueFactory<>("discount"));
        itemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        cartItemNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        cartQtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        cartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        cartTotalCol.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getPrice() * cellData.getValue().getQty()));

        itemsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedProduct = newSelection;
                qtyTxt.setText("1");
            }
        });

        cartTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedCartItem = newSelection;
        });


        ObservableList<String> customers = FXCollections.observableArrayList(
                CustomerController.getInstance()
                        .getCustomer()
                        .stream()
                        .map(customer -> customer.getName())
                        .collect(Collectors.toList())
        );

        customerCmb.setItems(customers);
        loadProductsTable();
    }



    private void loadProductsTable() {
        List<Product> products = ProductController.getInstance().getProduct();
        System.out.println("Loading products: " + products.size());
        itemsTable.setItems(FXCollections.observableArrayList(products));
    }

    public void btnCheckoutOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadCheckoutForm();
        }
    }

    public void cmbOnAction(ActionEvent actionEvent) {
        if (customerCmb.getValue()!=null||customerCmb.getValue().toString().equals("")) {
            customerNameTxt.setText(customerCmb.getValue().toString());
            pleaseSelectTxt.setVisible(false);
            addCustomerBtn.setDisable(true);
            OrderController.getInstance().setSelectedCustomerName(customerCmb.getValue().toString());
        }
    }
}

