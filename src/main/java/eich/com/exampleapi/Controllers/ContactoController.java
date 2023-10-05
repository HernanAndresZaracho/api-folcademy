package eich.com.exampleapi.Controllers;

import eich.com.exampleapi.Models.Dtos.ContactoAddDTO;
import eich.com.exampleapi.Models.Dtos.ContactoResponseDTO;
import eich.com.exampleapi.Models.Dtos.ContactoResponseMessageDTO;
import eich.com.exampleapi.Services.ContactoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class ContactoController {

    private final ContactoService contactoService;

    public ContactoController(ContactoService contactoService) {
        this.contactoService = contactoService;
    }

    @PostMapping("/contacto")
    public ResponseEntity<ContactoResponseMessageDTO> addContacto(@RequestBody ContactoAddDTO contactoAddDTO) {
        ContactoResponseMessageDTO response = contactoService.addContacto(contactoAddDTO);

        if (response != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/contactos")
    public ResponseEntity<List<ContactoResponseDTO>> findAllContactos() {
        List<ContactoResponseDTO> contactos = contactoService.findAllContactos();

        if (!contactos.isEmpty()) {
            return ResponseEntity.ok(contactos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/contacto/{contactoId}")
    public ResponseEntity<ContactoResponseDTO> findContactoById(@PathVariable Integer contactoId) {
        ContactoResponseDTO response = contactoService.findContactoById(contactoId);

        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/contacto/{contactoId}")
    public ResponseEntity<ContactoResponseDTO> updateContacto(@PathVariable Integer contactoId, @RequestBody ContactoAddDTO updatedContactoAddDTO) {
        ContactoResponseDTO response = contactoService.updateContacto(contactoId, updatedContactoAddDTO);

        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/contacto/{contactoId}")
    public ResponseEntity<ContactoResponseDTO> updateContacto(
            @PathVariable Integer contactoId,
            @RequestBody Map<String, Object> updatedData
    ) {
        ContactoResponseDTO response = contactoService.partialUpdateContacto(contactoId, updatedData);

        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/contacto/{contactoId}")
    public ResponseEntity<Void> deleteContacto(@PathVariable Integer contactoId) {
        boolean deleted = contactoService.deleteContacto(contactoId);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            contactoService.verifyContactoExists(contactoId);
            return ResponseEntity.notFound().build();
        }
    }
}
