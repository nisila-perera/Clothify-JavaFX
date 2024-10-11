package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Admin {
    private Integer adminId;
    private String adminName;
    private String empEmail;
    private String empPassword;
}
