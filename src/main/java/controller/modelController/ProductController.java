package controller.modelController;

import javafx.scene.control.Alert;
import model.Product;
import service.custom.ProductBo;
import service.custom.impl.ProductBoImpl;

import java.util.List;
import java.util.regex.Pattern;

public class ProductController {
    private static ProductController instance;
    final ProductBo productService = new ProductBoImpl();

    private ProductController() {}

    public static ProductController getInstance() {
        return instance == null ? instance = new ProductController() : instance;
    }

    public String generateId() {
        List<Product> list = productService.getProduct();
        if (list.isEmpty()) {
            return "Prd1";
        }

        list.sort((product1, product2) -> {
            try {
                String id1Str = product1.getId().replace("Prd", "");
                String id2Str = product2.getId().replace("Prd", "");
                int id1 = Integer.parseInt(id1Str);
                int id2 = Integer.parseInt(id2Str);
                return Integer.compare(id1, id2);
            } catch (Exception e) {
                return 0;
            }
        });

        String lastId = list.get(list.size() - 1).getId();
        String numberPart = lastId.replace("Prd", "");
        int nextId = Integer.parseInt(numberPart) + 1;
        return "Prd" + nextId;
    }

    public void addProduct(String id, String name,String size, String supplier, int qty, double price, double discount) {
        if (!validateProductData(name,supplier,qty,price,discount)) {
            return;
        }

        Product product = new Product(id, name, size, supplier, qty, price, discount);
        if (productService.addProduct(product)) {
            new Alert(Alert.AlertType.INFORMATION, "Product added Successfully!").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Product didn't add Successfully!").showAndWait();
        }
    }

    public void updateProduct(String id, String name,String size, String supplier, int qty, double price, double discount) {
        if (!validateProductData(name,supplier,qty,price,discount)) {
            return;
        }

        Product product = new Product(id, name, size, supplier, qty, price, discount);

        if (productService.updateProduct(product)) {
            new Alert(Alert.AlertType.INFORMATION, "Product updated Successfully!").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update product!").showAndWait();
        }
    }

    public boolean deleteProduct(String id) {
        Product product = getProduct(id);
        if (product != null) {
            return productService.deleteProduct(product);
        }
        return false;
    }

    public List<Product> getProduct() {
        return productService.getProduct();
    }

    public Product getProduct(String id) {
        return productService.searchProduct(id);
    }

    private boolean validateProductData(String name, String supplier, int qty, Double price, Double discount) {

        if (name.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Enter a Name!").showAndWait();
            return false;
        }
        if (supplier.isEmpty()||supplier.equals("")) {
            new Alert(Alert.AlertType.ERROR, "Select a supplier").showAndWait();
            return false;
        }
        if (qty<0) {
            new Alert(Alert.AlertType.ERROR, "Invalid Quantity").showAndWait();
            return false;
        }
        if (price<0) {
            new Alert(Alert.AlertType.ERROR, "Invalid Price").showAndWait();
            return false;
        }
        if (discount<1) {
            new Alert(Alert.AlertType.ERROR, "Invalid Discount").showAndWait();
            return false;
        }
        return true;
    }
}
