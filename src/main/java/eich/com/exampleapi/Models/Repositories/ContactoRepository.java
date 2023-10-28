package eich.com.exampleapi.Models.Repositories;

import eich.com.exampleapi.Models.Entities.ContactoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactoRepository extends JpaRepository<ContactoEntity, Integer> {
    boolean existsByNombre(String nombre);
    boolean existsByCelular(Integer celular);
    Boolean findByUsername(String username);

}

