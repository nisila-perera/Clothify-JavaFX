package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Supplier {
    private Integer supplierId;
    private String supplierName;
    private String supplierAddress;
    private String supplierMobileNumber;
    private String supplierEmail;
}
