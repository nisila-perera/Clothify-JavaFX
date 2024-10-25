package controller.modelController;

import javafx.scene.control.Alert;
import model.Customer;
import service.custom.CustomerBo;
import service.custom.impl.CustomerBoImpl;

import java.util.List;
import java.util.regex.Pattern;

public class CustomerController {
    private static CustomerController instance;
    final CustomerBo customerService = new CustomerBoImpl();

    private CustomerController() {}

    public static CustomerController getInstance() {
        return instance == null ? instance = new CustomerController() : instance;
    }

    public List<Customer> getCustomer() {
        return customerService.getCustomer();
    }

    public void addCustomer(String id, String name, String email, String address, String phone) {
        if (!validateCustomerData(name, email, address, phone)) {
            return;
        }

        Customer customer = new Customer(id, name, phone, email, address);
        if (customerService.addCustomer(customer)) {
            new Alert(Alert.AlertType.INFORMATION, "Customer added Successfully!").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Customer didn't add Successfully!").showAndWait();
        }
    }

    public String generateCustomerId() {
        List<Customer> list = customerService.getCustomer();
        list.sort((customer1, customer2) -> {
            int id1 = Integer.parseInt(customer1.getId().split("Cust")[1]);
            int id2 = Integer.parseInt(customer2.getId().split("Cust")[1]);
            return Integer.compare(id1, id2);
        });
        int id = list.isEmpty() ? 1 : Integer.parseInt((list.get(list.size() - 1).getId().split("Cust")[1])) + 1;
        return "Cust" + id;
    }

    private boolean validateCustomerData(String name, String email, String address, String phone) {
        Pattern pemail = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

        if (name.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Enter a Name!").showAndWait();
            return false;
        }
        if (!phone.isEmpty() && (phone.length() != 10 || phone.charAt(0) != '0')) {
            new Alert(Alert.AlertType.ERROR, "Phone number is incorrect").showAndWait();
            return false;
        }
        if (!pemail.matcher(email).matches()) {
            new Alert(Alert.AlertType.ERROR, "Incorrect Email").showAndWait();
            return false;
        }
        if (address.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Enter an Address").showAndWait();
            return false;
        }
        return true;
    }

    public void updateCustomer(String id, String name, String email, String address, String phone) {
        if (!validateCustomerData(name, email, address, phone)) {
            return;
        }

        Customer customer = new Customer(id, name, phone, email, address);

        if (customerService.updateCustomer(customer)) {
            new Alert(Alert.AlertType.INFORMATION, "Customer updated Successfully!").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update customer!").showAndWait();
        }
    }

    public boolean deleteCustomer(String id) {
        Customer customer = getCustomer(id);
        if (customer != null) {
            return customerService.deleteCustomer(customer);
        }
        return false;
    }


    public Customer getCustomer(String id) {
        return customerService.search(id);
    }
}
