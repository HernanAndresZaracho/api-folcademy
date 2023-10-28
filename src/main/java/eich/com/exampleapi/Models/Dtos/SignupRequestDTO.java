package eich.com.exampleapi.Models.Dtos;

import eich.com.exampleapi.Models.Entities.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String username;
    private Integer celular;
    private AddressReadDTO address;
}