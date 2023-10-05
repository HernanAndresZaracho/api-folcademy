package eich.com.exampleapi.Models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReadDTO {
    Integer id;
    String name;
    String surname;
    String email;
}
