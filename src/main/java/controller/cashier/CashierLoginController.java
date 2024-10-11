package controller.cashier;

import db.DBConnection;
import dto.Employee;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CashierLoginController {
    public boolean cashierLoginValidate(String email, String password) throws SQLException {
        String SQl = "SELECT * FROM employee WHERE empEmail=?";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            System.out.println(connection);
            PreparedStatement psTm = connection.prepareStatement(SQl);
            psTm.setString(1, email);

            ResultSet rs = psTm.executeQuery();

            Employee employee = null;
            while (rs.next()) {
                employee = new Employee(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                );
            }

            if (employee != null && email.equals(employee.getEmpEmail()) && password.equals(employee.getEmpPassword())) {
                System.out.println("Admin Login Successful");
                return true;
            } else {
                //CustomAlert.errorAlert("Login Error", new Exception("Login Failed"));
                System.out.println("Login Failed");
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return false;
    }
}
