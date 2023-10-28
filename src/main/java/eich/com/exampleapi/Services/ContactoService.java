package eich.com.exampleapi.Services;

import eich.com.exampleapi.Exceptions.ExceptionKinds.ContactoExistenteException;
import eich.com.exampleapi.Exceptions.ExceptionKinds.ContactoInexistenteException;
import eich.com.exampleapi.Models.Dtos.ContactoAddDTO;
import eich.com.exampleapi.Models.Dtos.ContactoResponseDTO;
import eich.com.exampleapi.Models.Dtos.ContactoResponseMessageDTO;
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
        boolean nombreExists = contactoRepository.existsByNombre(contactoAddDTO.getNombre());
        boolean celularExists = contactoRepository.existsByCelular(contactoAddDTO.getCelular());
        boolean usernameExists = contactoRepository.findByUsername(contactoAddDTO.getUsername());

        if (nombreExists || celularExists || usernameExists) {
            throw new ContactoExistenteException("El contacto con nombre '" + contactoAddDTO.getNombre() +
                    "', número de celular '" + contactoAddDTO.getCelular() +
                    "' o nombre de usuario '" + contactoAddDTO.getUsername() + "' ya existe.");
        }

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
            throw new ContactoInexistenteException("El contacto con el ID " + contactoId + " no se encontró.");
        }
    }

    public ContactoResponseDTO updateContacto(Integer contactoId, ContactoAddDTO updatedContactoData) {
        Optional<ContactoEntity> contactoEntityOptional = contactoRepository.findById(contactoId);

        if (contactoEntityOptional.isPresent()) {
            ContactoEntity contactoEntity = contactoEntityOptional.get();

            if (contactoRepository.existsByNombre(updatedContactoData.getNombre())) {
                throw new ContactoExistenteException("El nombre ya existe en la base de datos.");
            }

            if (contactoRepository.existsByCelular(updatedContactoData.getCelular())) {
                throw new ContactoExistenteException("El celular ya existe en la base de datos.");
            }

            if (contactoRepository.findByUsername(updatedContactoData.getUsername())) {
                throw new ContactoExistenteException("El nombre de usuario ya existe en la base de datos.");
            }

            contactoEntity.setNombre(updatedContactoData.getNombre());
            contactoEntity.setCelular(updatedContactoData.getCelular());
            contactoEntity.setUsername(updatedContactoData.getUsername());

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
                    String nuevoNombre = (String) fieldValue;
                    if (contactoRepository.existsByNombre(nuevoNombre)) {
                        throw new ContactoExistenteException("El nombre '" + nuevoNombre + "' ya existe en la base de datos.");
                    }
                    contactoEntity.setNombre(nuevoNombre);
                } else if ("celular".equals(fieldName)) {
                    Integer nuevoCelular = (Integer) fieldValue;
                    if (contactoRepository.existsByCelular(nuevoCelular)) {
                        throw new ContactoExistenteException("El celular '" + nuevoCelular + "' ya existe en la base de datos.");
                    }
                    contactoEntity.setCelular(nuevoCelular);
                } else if ("username".equals(fieldName)) {
                    String nuevoUsername = (String) fieldValue;
                    if (contactoRepository.findByUsername(nuevoUsername)) {
                        throw new ContactoExistenteException("El username '" + nuevoUsername + "' ya existe en la base de datos.");
                    }
                    contactoEntity.setUsername(nuevoUsername);
                }
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
    public void verifyContactoExists(Integer contactoId) {
        Optional<ContactoEntity> contactoEntityOptional = contactoRepository.findById(contactoId);

        if (!contactoEntityOptional.isPresent()) {
            throw new ContactoInexistenteException("El contacto con el ID " + contactoId + " no se encontró.");
        }
    }
}
