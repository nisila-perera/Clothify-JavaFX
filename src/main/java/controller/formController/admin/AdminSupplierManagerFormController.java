package controller.formController.admin;

import com.jfoenix.controls.JFXButton;
import controller.modelController.CustomerController;
import controller.modelController.SupplierController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import lombok.Setter;
import model.Customer;
import model.Supplier;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AdminSupplierManagerFormController implements Initializable {
    public TextField searchSupplierField;
    public TableView<Supplier> supplierTable;
    public TextField supplierNameField;
    public TextField supplierContactField;
    public TableColumn colSupplierId;
    public TableColumn colSupplierName;
    public JFXButton addBtn;
    public Text supplierIdTxt;
    public TableColumn colSupplierCompany;
    public TableColumn colSupplierContact;
    public TextField supplierCompanyField;
    private Supplier supplier;
    @Setter
    private AdminMainFormController adminMainFormController;


    public void btnCancelOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadAdminHomeForm();
        }
    }

    public void btnGenerateNewIdOnAction(ActionEvent actionEvent) {
        clearFields();
        supplierIdTxt.setText(SupplierController.getInstance().generateSupplierId());
        addBtn.setDisable(false);
    }

    public void btnSearchSupplierOnAction(ActionEvent actionEvent) {
        String searchText = searchSupplierField.getText().trim().toLowerCase();
        if (searchText.isEmpty()) {
            loadSupplierTable();
            return;
        }

        List<Supplier> allSuppliers = SupplierController.getInstance().getSupplier();
        List<Supplier> filteredSuppliers = allSuppliers.stream()
                .filter(supplier -> supplier.getName().toLowerCase().contains(searchText) ||
                        supplier.getId().toLowerCase().contains(searchText) ||
                        supplier.getCompany().toLowerCase().contains(searchText) ||
                        supplier.getContact().toLowerCase().contains(searchText))
                .collect(Collectors.toList());

        supplierTable.setItems(FXCollections.observableArrayList(filteredSuppliers));
    }

    public void btnSelectSupplierOnAction(ActionEvent actionEvent) {
        if (supplier != null) {
            supplierIdTxt.setText(supplier.getId());
            supplierNameField.setText(supplier.getName());
            supplierCompanyField.setText(supplier.getCompany());
            supplierContactField.setText(supplier.getContact());
            addBtn.setDisable(true);
        }
    }

    public void btnUpdateSupllierOnAction(ActionEvent actionEvent) {
        SupplierController.getInstance().updateSupplier(
                supplierIdTxt.getText(),
                supplierNameField.getText(),
                supplierCompanyField.getText(),
                supplierContactField.getText()
        );

        clearFields();
        loadSupplierTable();
    }

    public void btnAddSupplierOnAction(ActionEvent actionEvent) {
        SupplierController.getInstance().addSupplier(
                supplierIdTxt.getText(),
                supplierNameField.getText(),
                supplierCompanyField.getText(),
                supplierContactField.getText()
        );

        clearFields();
        loadSupplierTable();
    }

    public void btnDeleteSupplierOnAction(ActionEvent actionEvent) {
        if (supplier != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to delete supplier " + supplier.getName() + "?",
                    ButtonType.YES, ButtonType.NO);

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    boolean deleted = SupplierController.getInstance().deleteSupplier(supplier.getId());
                    if (deleted) {
                        new Alert(Alert.AlertType.INFORMATION, "Supplier deleted successfully!").show();
                        clearFields();
                        loadSupplierTable();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to delete supplier!").show();
                    }
                }
            });
        }
    }

    private void loadSupplierTable(){
        List<Supplier> suppliers = SupplierController.getInstance().getSupplier();
        System.out.println("Loading suppliers: " + suppliers.size());
        supplierTable.setItems(FXCollections.observableArrayList(suppliers));
    }

    private void clearFields() {
        supplierIdTxt.setText("");
        supplierNameField.setText("");
        supplierCompanyField.setText("");
        supplierContactField.setText("");
        addBtn.setDisable(true);
        supplier = null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addBtn.setDisable(true);
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSupplierCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colSupplierContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        supplierTable.getSelectionModel().selectedItemProperty().addListener(((observableValue, o, t1) ->{
            if(t1!=null){
                supplier = t1;
            }
        }));

        loadSupplierTable();
    }
}
