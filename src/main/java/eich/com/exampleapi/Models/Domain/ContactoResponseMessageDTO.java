package eich.com.exampleapi.Models.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ContactoResponseMessageDTO {
    Integer id;
    String mensaje;
}
