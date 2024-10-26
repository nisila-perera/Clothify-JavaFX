package controller.formController.admin;

import controller.modelController.OrderController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Order;
import model.OrderDetails;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class AdminCheckoutFormController implements Initializable {
    public Text orderIdTxt;
    @FXML
    private TableView<OrderDetails> billTable;
    @FXML
    private TableColumn<OrderDetails, String> itemNameCol;
    @FXML
    private TableColumn<OrderDetails, Integer> qtyCol;
    @FXML
    private TableColumn<OrderDetails, Double> priceCol;
    @FXML
    private TableColumn<OrderDetails, Double> discountCol;
    @FXML
    private TableColumn<OrderDetails, Double> totalCol;
    public Text customerNameTxt;
    public Text orderDateTxt;
    public Text orderTimeTxt;
    public Text totalTxt;

    private AdminMainFormController adminMainFormController;

    public void setAdminMainFormController(AdminMainFormController adminMainFormController) {
        this.adminMainFormController = adminMainFormController;
    }

    public void btnCompleteOrderOnAction(ActionEvent actionEvent) {
        try {
            Order order = new Order(
                    orderIdTxt.getText(),
                    customerNameTxt.getText(),
                    "",
                    "CASH",
                    Double.parseDouble(totalTxt.getText()),
                    OrderController.getInstance().getCart().stream()
                            .mapToDouble(OrderDetails::getDiscount)
                            .sum(),
                    LocalDate.now(),
                    adminMainFormController.getActiveAdminTxt().getText()
            );

            boolean success = OrderController.getInstance().completeOrder(order);

            if (success) {
                OrderController.getInstance().getCart(0);

                new Alert(Alert.AlertType.INFORMATION, "Order placed successfully!").showAndWait();
                if (adminMainFormController != null) {
                    adminMainFormController.loadAdminHomeForm();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to place order").showAndWait();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).showAndWait();
            e.printStackTrace();
        }
    }

    public void btnCancelOrderOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadPlaceOrderForm();
        }
    }

    private void loadCartItems() {
        List<OrderDetails> cartItems = OrderController.getInstance().getCart();
        billTable.setItems(FXCollections.observableArrayList(cartItems));

        double total = cartItems.stream()
                .mapToDouble(item -> item.getPrice() - item.getDiscount())
                .sum();
        totalTxt.setText(String.format("%.2f", total));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (OrderController.getInstance().getSelectedCustomerName()!=null) {
            customerNameTxt.setText(OrderController.getInstance().getSelectedCustomerName());
        }else{
            customerNameTxt.setText("Guest Order");
        }
        orderIdTxt.setText(OrderController.getInstance().generateId());
        orderDateTxt.setText(LocalDate.now().toString());

        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        orderTimeTxt.setText(currentTime.format(formatter));

        itemNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        discountCol.setCellValueFactory(new PropertyValueFactory<>("discount"));
        totalCol.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getPrice() - cellData.getValue().getDiscount()));

        loadCartItems();
    }
}
