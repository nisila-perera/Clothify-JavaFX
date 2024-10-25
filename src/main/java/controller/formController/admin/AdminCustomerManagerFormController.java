package controller.formController.admin;

import com.jfoenix.controls.JFXButton;
import controller.modelController.CustomerController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import lombok.Setter;
import model.Customer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AdminCustomerManagerFormController implements Initializable {
    public TextField searchCustomerField;
    public TableView<Customer> customerTable;
    public TextField customerNameField;
    public TextField customerMobileField;
    public TextField customerEmailField;
    public TextField customerAddressField;
    public Text customerIdTxt;
    public TableColumn colCustId;
    public TableColumn colCustName;
    public TableColumn colCustPhone;
    public JFXButton addBtn;
    public TableColumn colCustEmail;
    public JFXButton addAndContinueBtn;
    private Customer customer;
    @Setter
    private AdminMainFormController adminMainFormController;

    public void btnSearchCustomerOnAction(ActionEvent actionEvent) {
        String searchText = searchCustomerField.getText().trim().toLowerCase();
        if (searchText.isEmpty()) {
            loadCustomerTable();
            return;
        }

        List<Customer> allCustomers = CustomerController.getInstance().getCustomer();
        List<Customer> filteredCustomers = allCustomers.stream()
                .filter(customer -> customer.getName().toLowerCase().contains(searchText) ||
                        customer.getId().toLowerCase().contains(searchText) ||
                        customer.getPhone().toLowerCase().contains(searchText) ||
                        customer.getEmail().toLowerCase().contains(searchText))
                .collect(Collectors.toList());

        customerTable.setItems(FXCollections.observableArrayList(filteredCustomers));
    }

    public void btnSelectCustomerOnAction(ActionEvent actionEvent) {
        if (customer != null) {
            customerIdTxt.setText(customer.getId());
            customerNameField.setText(customer.getName());
            customerMobileField.setText(customer.getPhone());
            customerEmailField.setText(customer.getEmail());
            customerAddressField.setText(customer.getAddress());
            addBtn.setDisable(true);
            addAndContinueBtn.setDisable(true);
        }
    }

    public void btnUpdateCustomerOnAction(ActionEvent actionEvent) {
        CustomerController.getInstance().updateCustomer(
                customerIdTxt.getText(),
                customerNameField.getText(),
                customerEmailField.getText(),
                customerAddressField.getText(),
                customerMobileField.getText()
        );

        clearFields();
        loadCustomerTable();
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadAdminHomeForm();
        }
    }

    public void btnAddCustomerAndContinueOnAction(ActionEvent actionEvent) {
        CustomerController.getInstance().addCustomer(
                customerIdTxt.getText(),
                customerNameField.getText(),
                customerEmailField.getText(),
                customerAddressField.getText(),
                customerMobileField.getText()
        );

        clearFields();
        loadCustomerTable();
    }

    public void btnDeleteCustomerOnAction(ActionEvent actionEvent) {
        if (customer != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to delete customer " + customer.getName() + "?",
                    ButtonType.YES, ButtonType.NO);

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    boolean deleted = CustomerController.getInstance().deleteCustomer(customer.getId());
                    if (deleted) {
                        new Alert(Alert.AlertType.INFORMATION, "Customer deleted successfully!").show();
                        clearFields();
                        loadCustomerTable();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to delete customer!").show();
                    }
                }
            });
        }
    }

    public void btnGenerateNewIdOnAction(ActionEvent actionEvent) {
        clearFields();
        customerIdTxt.setText(CustomerController.getInstance().generateCustomerId());
        addBtn.setDisable(false);
        addAndContinueBtn.setDisable(false);
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
        CustomerController.getInstance().addCustomer(
                customerIdTxt.getText(),
                customerNameField.getText(),
                customerEmailField.getText(),
                customerAddressField.getText(),
                customerMobileField.getText()
        );

        clearFields();
        loadCustomerTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addBtn.setDisable(true);
        addAndContinueBtn.setDisable(true);
        colCustId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colCustEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        customerTable.getSelectionModel().selectedItemProperty().addListener(((observableValue, o, t1) ->{
            if(t1!=null){
                customer = t1;
            }
        }));

        loadCustomerTable();
    }

    private void loadCustomerTable(){
        List<Customer> customers = CustomerController.getInstance().getCustomer();
        System.out.println("Loading customers: " + customers.size());
        customerTable.setItems(FXCollections.observableArrayList(customers));
    }

    private void clearFields() {
        customerIdTxt.setText("");
        customerNameField.setText("");
        customerEmailField.setText("");
        customerAddressField.setText("");
        customerMobileField.setText("");
        addBtn.setDisable(true);
        addAndContinueBtn.setDisable(true);
        customer = null;
    }
}
