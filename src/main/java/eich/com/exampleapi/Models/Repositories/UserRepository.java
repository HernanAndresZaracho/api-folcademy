package eich.com.exampleapi.Models.Repositories;

import eich.com.exampleapi.Models.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Boolean existsByEmail(String email);
    List<UserEntity> findAllByNameAndSurname(String name, String surname);
    Optional<UserEntity> findOneByEmail(String email);
}
