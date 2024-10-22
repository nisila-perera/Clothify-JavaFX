package service.custom;

import model.Customer;
import model.Employee;

import java.util.List;

public interface CustomerBo {
    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(Customer customer);
    List<Customer> getCustomer();
    Customer search(String id);
}
