package eich.com.exampleapi.Models.Mappers;

import eich.com.exampleapi.Models.Domain.ContactoAddDTO;
import eich.com.exampleapi.Models.Domain.ContactoResponseDTO;
import eich.com.exampleapi.Models.Entities.ContactoEntity;
import org.springframework.stereotype.Component;

@Component
public class ContactoMapper {
    public ContactoResponseDTO contactoEntityToContactoReadDTO(ContactoEntity contactoEntity) {
        ContactoResponseDTO contactoReadDTO = new ContactoResponseDTO();
        contactoReadDTO.setId(contactoEntity.getId());
        contactoReadDTO.setNombre(contactoEntity.getNombre());
        contactoReadDTO.setCelular(contactoEntity.getCelular());
        return contactoReadDTO;
    }

    public ContactoEntity contactoAddDTOToContactoEntity(ContactoAddDTO contactoAddDTO) {
        ContactoEntity contactoEntity = new ContactoEntity();
        contactoEntity.setNombre(contactoAddDTO.getNombre());
        contactoEntity.setCelular(contactoAddDTO.getCelular());
        return contactoEntity;
    }
}
