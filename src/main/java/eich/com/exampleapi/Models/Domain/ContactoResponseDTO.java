package eich.com.exampleapi.Models.Domain;

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
