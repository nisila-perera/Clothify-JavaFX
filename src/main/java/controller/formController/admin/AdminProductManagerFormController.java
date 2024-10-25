package controller.formController.admin;

import com.jfoenix.controls.JFXButton;
import controller.modelController.ProductController;
import controller.modelController.SupplierController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import lombok.Setter;
import model.Product;
import model.Supplier;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AdminProductManagerFormController implements Initializable {
    public TextField searchProductField;
    public TableView<Product> productTable;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colStock;
    public TableColumn colPrice;
    public TableColumn colDiscount;
    public TextField productNameField;
    public TextField availableStockField;
    public TextField sizeField;
    public TextField priceField;
    public TextField discountField;
    public Text productIdTxt;
    public ComboBox supplierCmb;
    public JFXButton addBtn;
    private Product product;

    @Setter
    private AdminMainFormController adminMainFormController;

    public void btnSearchProductOnAction(ActionEvent actionEvent) {
        String searchText = searchProductField.getText().trim().toLowerCase();
        if (searchText.isEmpty()) {
            loadProductsTable();
            return;
        }

        List<Product> allProducts = ProductController.getInstance().getProduct();
        List<Product> filteredProducts = allProducts.stream()
                .filter(product -> product.getName().toLowerCase().contains(searchText) ||
                        product.getId().toLowerCase().contains(searchText) ||
                        product.getSupplier().toLowerCase().contains(searchText))  // closing parenthesis here
                .collect(Collectors.toList());

        productTable.setItems(FXCollections.observableArrayList(filteredProducts));
    }

    public void btnSelectProductOnAction(ActionEvent actionEvent) {
        if (product != null) {
            productIdTxt.setText(product.getId());
            productNameField.setText(product.getName());
            sizeField.setText(product.getSize());
            availableStockField.setText(String.valueOf(product.getQty()));
            priceField.setText(String.valueOf(product.getPrice()));
            discountField.setText(String.valueOf(product.getDiscount()));
            supplierCmb.setValue(product.getSupplier());
            addBtn.setDisable(true);
        }
    }


    public void btnUpdateProductOnAction(ActionEvent actionEvent) {
        Double discount=1.0;
        if (!discountField.getText().equals("")||discountField.getText().equals(null)){
            discount=Double.parseDouble(discountField.getText());
        }

        ProductController.getInstance().updateProduct(
                productIdTxt.getText(),
                productNameField.getText(),
                sizeField.getText(),
                supplierCmb.getValue().toString(),
                Integer.parseInt(availableStockField.getText()),
                Double.parseDouble(priceField.getText()),
                discount
        );

        clearFields();
        loadProductsTable();
    }

    public void btnGenerateNewIdOnAction(ActionEvent actionEvent) {
        clearFields();
        productIdTxt.setText(ProductController.getInstance().generateId());
        addBtn.setDisable(false);
    }

    public void btnDeleteProductOnAction(ActionEvent actionEvent) {
        if (product != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to delete product " + product.getName() + "?",
                    ButtonType.YES, ButtonType.NO);

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    boolean deleted = ProductController.getInstance().deleteProduct(product.getId());
                    if (deleted) {
                        new Alert(Alert.AlertType.INFORMATION, "Product deleted successfully!").show();
                        clearFields();
                        loadProductsTable();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to delete product!").show();
                    }
                }
            });
        }
    }

    public void btnAddProductOnAction(ActionEvent actionEvent) {
        Double discount=1.0;
        if (!discountField.getText().equals("")||discountField.getText().equals(null)){
            discount=Double.parseDouble(discountField.getText());
        }
        ProductController.getInstance().addProduct(
                productIdTxt.getText(),
                productNameField.getText(),
                sizeField.getText(),
                supplierCmb.getValue().toString(),
                Integer.parseInt(availableStockField.getText()),
                Double.parseDouble(priceField.getText()),
                discount
        );

        clearFields();
        loadProductsTable();
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadAdminHomeForm();
        }
    }

    private void loadProductsTable(){
        List<Product> products = ProductController.getInstance().getProduct();
        System.out.println("Loading products: " + products.size());
        productTable.setItems(FXCollections.observableArrayList(products));
    }

    private void clearFields() {
        productIdTxt.setText("Select product");
        productNameField.setText("");
        availableStockField.setText("");
        sizeField.setText("");
        discountField.setText("");
        priceField.setText("");
        addBtn.setDisable(true);
        product = null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addBtn.setDisable(true);

        ObservableList<String> suppliers = FXCollections.observableArrayList(
                SupplierController.getInstance()
                        .getSupplier()
                        .stream()
                        .map(supplier -> supplier.getName())
                        .collect(Collectors.toList())
        );

        supplierCmb.setItems(suppliers);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));

        productTable.getSelectionModel().selectedItemProperty().addListener(((observableValue, o, t1) ->{
            if(t1!=null){
                product = t1;
            }
        }));
        loadProductsTable();
    }
}
