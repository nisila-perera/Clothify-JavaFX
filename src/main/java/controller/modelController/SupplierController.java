package controller.modelController;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Supplier;
import service.custom.SupplierBo;
import service.custom.impl.SupplierBoImpl;

import java.util.List;

public class SupplierController {
    private static SupplierController instance;
    final SupplierBo supplierService = new SupplierBoImpl();

    private SupplierController() {}

    public static SupplierController getInstance() {
        return instance == null ? instance = new SupplierController() : instance;
    }

    public List<Supplier> getSupplier() {
        return supplierService.getSupplier();
    }

    public void addSupplier(String id, String name, String company, String contact) {
        if (name.isEmpty()||name.equals("")) {
            return;
        }

        Supplier supplier = new Supplier(id, name, company, contact);
        if (supplierService.addSupplier(supplier)) {
            new Alert(Alert.AlertType.INFORMATION, "Supplier added Successfully!").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Supplier didn't add Successfully!").showAndWait();
        }
    }

    public String generateSupplierId() {
        List<Supplier> list = supplierService.getSupplier();
        list.sort((supplier1, supplier2) -> {
            int id1 = Integer.parseInt(supplier1.getId().split("Sup")[1]);
            int id2 = Integer.parseInt(supplier2.getId().split("Sup")[1]);
            return Integer.compare(id1, id2);
        });
        int id = list.isEmpty() ? 1 : Integer.parseInt((list.get(list.size() - 1).getId().split("Sup")[1])) + 1;
        return "Sup" + id;
    }

    public void updateSupplier(String id, String name, String company, String contact) {
        if (!name.isEmpty()||name.equals("")) {
            return;
        }

        Supplier supplier = new Supplier(id, name, company, contact);

        if (supplierService.updateSupplier(supplier)) {
            new Alert(Alert.AlertType.INFORMATION, "Supplier updated Successfully!").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update supplier!").showAndWait();
        }
    }

    public boolean deleteSupplier(String id) {
        Supplier supplier = getSupplier(id);
        if (supplier != null) {
            return supplierService.deleteSupplier(supplier);
        }
        return false;
    }


    public Supplier getSupplier(String id) {
        return supplierService.searchSupplier(id);
    }
}
