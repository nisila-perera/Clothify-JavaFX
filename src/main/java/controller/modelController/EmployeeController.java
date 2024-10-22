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

    private EmployeeController(){}

    public static EmployeeController getInstance(){
        return instance==null?instance= new EmployeeController():instance;
    }

    public String GenerateId(){
        List<Employee> list = employeeservice.getEmployee();
        list.sort((employee1,employee2) -> {
            int id1 = Integer.parseInt(employee1.getId().split("Emp")[1]);
            int id2 = Integer.parseInt(employee2.getId().split("Emp")[1]);
            return Integer.compare(id1, id2);
        });
        int id = list.isEmpty() ?1:Integer.parseInt((list.getLast().getId().split("Emp",2)[1]))+1;
        return "Emp"+id;
    }

    public void AddEmployee(String name,String email, String address, String password,String checkpassword,String phone){

        Pattern ppassword= Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");
        Matcher mpassword = ppassword.matcher(password);
        Pattern pemail= Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Matcher memail = pemail.matcher(email);

        if(name.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Enter a Name!").showAndWait();
        }
        else if(phone.length()!=10){
            new Alert(Alert.AlertType.ERROR,"Phone number is incorrect").showAndWait();
        }
        else if(phone.charAt(0)!='0'){
            new Alert(Alert.AlertType.ERROR,"Phone number is incorrect").showAndWait();
        }
        else if (!memail.matches()){
            new Alert(Alert.AlertType.ERROR,"Inncorrect Email").showAndWait();
        }
        else if (address.isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Enter an Address").showAndWait();
        }
        else if(password.length()<8){
            new Alert(Alert.AlertType.ERROR,"Password is Small").showAndWait();
        }
        else if (!mpassword.matches()) {
            new Alert(Alert.AlertType.ERROR,"Password must contain at least one of '!@#$%^&*' and one of numeric").showAndWait();
        }
        else if (!password.equals(checkpassword)) {
            new Alert(Alert.AlertType.ERROR,"Password doesn't match").showAndWait();
        }
        else {
            String id = GenerateId();
            if(employeeservice.addEmployee(new Employee(id, name, phone, email, address, new Encryptor().encryptString(password)))){
                new Alert(Alert.AlertType.INFORMATION,"Employee added Successfully!").showAndWait();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Employee didn't added Successfully!").showAndWait();
            }
        }
    }

    public List<Employee> GetEmployee(){
        return employeeservice.getEmployee();
    }
}
