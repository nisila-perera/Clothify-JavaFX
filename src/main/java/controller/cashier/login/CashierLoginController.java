package controller.cashier.login;

import db.DBConnection;
import model.Employee;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CashierLoginController {
    private String cashierName=null;
    public boolean cashierLoginValidate(String email, String password) throws SQLException {
        String SQl = "SELECT * FROM employee WHERE empEmail=?";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQl);
            psTm.setString(1, email);

            ResultSet rs = psTm.executeQuery();

            Employee employee = null;
            while (rs.next()) {
                employee = new Employee(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                );
            }

            if (employee != null && email.equals(employee.getEmail()) && password.equals(employee.getPassword())) {
                cashierName=employee.getName();
                return true;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setContentText("Cashier Login Failed Check your credentials");
                alert.setHeaderText(null);
                alert.setResizable(false);
                alert.showAndWait();

                return false;
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setContentText("SQL Error, Contact Admin");
            alert.setHeaderText(null);
            alert.setResizable(false);
            alert.showAndWait();
        }
        return false;
    }
    public String getCashierName(){
        return cashierName;
    }
}
