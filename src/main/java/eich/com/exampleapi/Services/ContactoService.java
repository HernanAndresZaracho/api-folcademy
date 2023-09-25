package eich.com.exampleapi.Services;

import eich.com.exampleapi.Models.Domain.ContactoAddDTO;
import eich.com.exampleapi.Models.Domain.ContactoResponseDTO;
import eich.com.exampleapi.Models.Domain.ContactoResponseMessageDTO;
import eich.com.exampleapi.Models.Entities.ContactoEntity;
import eich.com.exampleapi.Models.Mappers.ContactoMapper;
import eich.com.exampleapi.Models.Repositories.ContactoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactoService {
    private final ContactoRepository contactoRepository;
    private final ContactoMapper contactoMapper;

    public ContactoService(ContactoRepository contactoRepository, ContactoMapper contactoMapper) {
        this.contactoRepository = contactoRepository;
        this.contactoMapper = contactoMapper;
    }

    public List<ContactoResponseDTO> findAllContactos() {
        return contactoRepository
                .findAll()
                .stream()
                .map(contactoMapper::contactoEntityToContactoReadDTO)
                .collect(Collectors.toList());
    }

    public ContactoResponseMessageDTO addContacto(ContactoAddDTO contactoAddDTO) {
        ContactoEntity contactoEntity = contactoMapper.contactoAddDTOToContactoEntity(contactoAddDTO);
        ContactoEntity savedContacto = contactoRepository.save(contactoEntity);

        ContactoResponseMessageDTO response = new ContactoResponseMessageDTO();
        response.setId(savedContacto.getId());
        response.setMensaje("añadido exitosamente");

        return response;
    }

    public ContactoResponseDTO findContactoById(Integer contactoId) {
        Optional<ContactoEntity> contactoEntityOptional = contactoRepository.findById(contactoId);

        if (contactoEntityOptional.isPresent()) {
            ContactoEntity contactoEntity = contactoEntityOptional.get();
            return contactoMapper.contactoEntityToContactoReadDTO(contactoEntity);
        } else {
            return null;
        }
    }

    public ContactoResponseDTO updateContacto(Integer contactoId, ContactoAddDTO updatedContactoData) {
        Optional<ContactoEntity> contactoEntityOptional = contactoRepository.findById(contactoId);

        if (contactoEntityOptional.isPresent()) {
            ContactoEntity contactoEntity = contactoEntityOptional.get();

            contactoEntity.setNombre(updatedContactoData.getNombre());
            contactoEntity.setCelular(updatedContactoData.getCelular());

            contactoRepository.save(contactoEntity);

            return contactoMapper.contactoEntityToContactoReadDTO(contactoEntity);
        } else {
            return null;
        }
    }

    public ContactoResponseDTO partialUpdateContacto(Integer contactoId, Map<String, Object> updatedContactoData) {
        Optional<ContactoEntity> contactoEntityOptional = contactoRepository.findById(contactoId);

        if (contactoEntityOptional.isPresent()) {
            ContactoEntity contactoEntity = contactoEntityOptional.get();

            for (Map.Entry<String, Object> entry : updatedContactoData.entrySet()) {
                String fieldName = entry.getKey();
                Object fieldValue = entry.getValue();

                if ("nombre".equals(fieldName)) {
                    contactoEntity.setNombre((String) fieldValue);
                } else if ("celular".equals(fieldName)) {
                    contactoEntity.setCelular((Integer) fieldValue);
                }
                // Agrega aquí otros campos si los tienes
            }

            contactoRepository.save(contactoEntity);

            return contactoMapper.contactoEntityToContactoReadDTO(contactoEntity);
        } else {
            return null;
        }
    }

    public boolean deleteContacto(Integer contactoId) {
        Optional<ContactoEntity> contactoEntityOptional = contactoRepository.findById(contactoId);

        if (contactoEntityOptional.isPresent()) {
            contactoRepository.deleteById(contactoId);
            return true;
        } else {
            return false;
        }
    }
}
