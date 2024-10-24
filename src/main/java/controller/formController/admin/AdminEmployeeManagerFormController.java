package controller.formController.admin;

import com.jfoenix.controls.JFXButton;
import controller.modelController.EmployeeController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;
import model.Employee;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AdminEmployeeManagerFormController implements Initializable {

    public TextField empNameTxt;
    public TextField empMobileTxt;
    public TextField empEmailTxt;
    public TextField empAddressTxt;
    public PasswordField empPasswordTxt;
    public TableView<Employee> empTable;
    public TextField empSearchField;
    public TableColumn empId;
    public TableColumn empName;
    public TableColumn empMobile;
    public TableColumn empEmail;
    public Text empIdTxt;
    public JFXButton addBtn;
    private Employee employee;
    @Setter
    private AdminMainFormController adminMainFormController;

    public void btnSearchEmpOnAction(ActionEvent actionEvent) {
        String searchText = empSearchField.getText().trim().toLowerCase();
        if (searchText.isEmpty()) {
            loadEmployeeTable();
            return;
        }

        List<Employee> allEmployees = EmployeeController.getInstance().getEmployee();
        List<Employee> filteredEmployees = allEmployees.stream()
                .filter(emp -> emp.getName().toLowerCase().contains(searchText) ||
                        emp.getId().toLowerCase().contains(searchText) ||
                        emp.getPhone().toLowerCase().contains(searchText) ||
                        emp.getEmail().toLowerCase().contains(searchText))
                .collect(Collectors.toList());

        empTable.setItems(FXCollections.observableArrayList(filteredEmployees));
    }

    public void btnEmpSelectOnAction(ActionEvent actionEvent) {
        if (employee != null) {
            empIdTxt.setText(employee.getId());
            empNameTxt.setText(employee.getName());
            empMobileTxt.setText(employee.getPhone());
            empEmailTxt.setText(employee.getEmail());
            empAddressTxt.setText(employee.getAddress());
            empPasswordTxt.setText("*****");
            addBtn.setDisable(true);
        }
    }

    public void btnAddEmpOnAction(ActionEvent actionEvent) {
        EmployeeController.getInstance().AddEmployee(
                empIdTxt.getText(),
                empNameTxt.getText(),
                empEmailTxt.getText(),
                empAddressTxt.getText(),
                empPasswordTxt.getText(),
                empMobileTxt.getText()
        );

        clearFields();
        loadEmployeeTable();
    }

    public void btnUpdateEmpOnAction(ActionEvent actionEvent) {
        EmployeeController.getInstance().updateEmployee(
                empIdTxt.getText(),
                empNameTxt.getText(),
                empEmailTxt.getText(),
                empAddressTxt.getText(),
                empPasswordTxt.getText(),
                empMobileTxt.getText()
        );

        clearFields();
        loadEmployeeTable();
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        if (adminMainFormController!=null){
            adminMainFormController.loadAdminHomeForm();
        }
    }

    public void btnDeleteEmpOnAction(ActionEvent actionEvent) {
        if (employee != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to delete employee " + employee.getName() + "?",
                    ButtonType.YES, ButtonType.NO);

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    boolean deleted = EmployeeController.getInstance().deleteEmployee(employee.getId());
                    if (deleted) {
                        new Alert(Alert.AlertType.INFORMATION, "Employee deleted successfully!").show();
                        clearFields();
                        loadEmployeeTable();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to delete employee!").show();
                    }
                }
            });
        }
    }

    private void clearFields() {
        empIdTxt.setText("");
        empNameTxt.setText("");
        empEmailTxt.setText("");
        empAddressTxt.setText("");
        empPasswordTxt.setText("");
        empMobileTxt.setText("");
        addBtn.setDisable(true);
        employee = null;
    }

    public void btnGenerateNewIdOnAction(ActionEvent actionEvent) {
        clearFields();
        empIdTxt.setText(EmployeeController.getInstance().GenerateId());
        addBtn.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addBtn.setDisable(true);
        empId.setCellValueFactory(new PropertyValueFactory<>("id"));
        empName.setCellValueFactory(new PropertyValueFactory<>("name"));
        empMobile.setCellValueFactory(new PropertyValueFactory<>("phone"));
        empEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        empTable.getSelectionModel().selectedItemProperty().addListener(((observableValue, o, t1) ->{
            if(t1!=null){
                employee = t1;
            }
        }));

        loadEmployeeTable();
    }

    private void loadEmployeeTable(){
        List<Employee> employees = EmployeeController.getInstance().getEmployee();
        System.out.println("Loading employees: " + employees.size());
        empTable.setItems(FXCollections.observableArrayList(employees));
    }
}
