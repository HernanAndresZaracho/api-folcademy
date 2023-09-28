package eich.com.exampleapi.Models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ContactoResponseDTO {
    Integer id;
    String nombre;
    Integer celular;
}
