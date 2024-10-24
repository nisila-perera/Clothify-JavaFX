package controller.modelController;

import javafx.scene.control.Alert;
import model.Employee;
import service.custom.EmployeeBo;
import service.custom.impl.EmployeeBoImpl;
import util.Encryptor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeController {
    private static EmployeeController instance;
    final EmployeeBo employeeservice = new EmployeeBoImpl();

    private EmployeeController() {}

    public static EmployeeController getInstance() {
        return instance == null ? instance = new EmployeeController() : instance;
    }

    public String GenerateId() {
        List<Employee> list = employeeservice.getEmployee();
        list.sort((employee1, employee2) -> {
            int id1 = Integer.parseInt(employee1.getId().split("Emp")[1]);
            int id2 = Integer.parseInt(employee2.getId().split("Emp")[1]);
            return Integer.compare(id1, id2);
        });
        int id = list.isEmpty() ? 1 : Integer.parseInt((list.get(list.size() - 1).getId().split("Emp")[1])) + 1;
        return "Emp" + id;
    }

    public void AddEmployee(String id, String name, String email, String address, String password, String phone) {
        if (!validateEmployeeData(name, email, address, password, phone)) {
            return;
        }

        Employee employee = new Employee(id, name, phone, email, address, new Encryptor().encryptString(password));
        if (employeeservice.addEmployee(employee)) {
            new Alert(Alert.AlertType.INFORMATION, "Employee added Successfully!").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Employee didn't add Successfully!").showAndWait();
        }
    }

    public void updateEmployee(String id, String name, String email, String address, String password, String phone) {
        if (!validateEmployeeData(name, email, address, password, phone)) {
            return;
        }

        Employee employee = new Employee(id, name, phone, email, address,
                password.isEmpty() || password.equals("*****") ? getEmployee(id).getPassword() : new Encryptor().encryptString(password));

        if (employeeservice.updateEmployee(employee)) {
            new Alert(Alert.AlertType.INFORMATION, "Employee updated Successfully!").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update employee!").showAndWait();
        }
    }

    public boolean deleteEmployee(String id) {
        Employee employee = getEmployee(id);
        if (employee != null) {
            return employeeservice.deleteEmployee(employee);
        }
        return false;
    }

    public List<Employee> getEmployee() {
        return employeeservice.getEmployee();
    }

    public Employee getEmployee(String id) {
        return employeeservice.search(id);
    }

    private boolean validateEmployeeData(String name, String email, String address, String password, String phone) {
        Pattern ppassword = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");
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
        if (password.isEmpty()||password.equals("")) {
            new Alert(Alert.AlertType.ERROR, "Empty Password").showAndWait();
            return false;
        }else{
            if (password.length() < 8) {
                new Alert(Alert.AlertType.ERROR, "Password is too short").showAndWait();
                return false;
            }
            if (!ppassword.matcher(password).matches()) {
                new Alert(Alert.AlertType.ERROR,
                        "Password must contain at least one number, one lowercase letter, " +
                                "one uppercase letter, and one special character").showAndWait();
                return false;
            }
        }
        return true;
    }
}