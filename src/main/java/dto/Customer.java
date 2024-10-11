package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Customer {
    private Integer customerId;
    private String customerName;
    private String customerAddress;
    private String customerMobileNumber;
    private String customerEmail;
}
