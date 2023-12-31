package eich.com.exampleapi.Models.Repositories;

import eich.com.exampleapi.Models.Entities.ContactoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<ContactoEntity, Integer> {
    boolean existsByNombre(String nombre);
    boolean existsByCelular(Integer celular);
}

