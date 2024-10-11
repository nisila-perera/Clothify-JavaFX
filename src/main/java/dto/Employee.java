package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Employee {
    private Integer empId;
    private String empName;
    private String empAddress;
    private String empMobileNumber;
    private String empEmail;
    private String empPassword;
}
