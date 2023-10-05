package eich.com.exampleapi.Models.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT UNSIGNED")
    private Integer id;

    @Column(name = "nombre", columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(name = "apellido", columnDefinition = "VARCHAR(100)")
    private String surname;

    @Column(name = "email", columnDefinition = "VARCHAR(100)")
    private String email;

    @Column(name = "contrasena", columnDefinition = "VARCHAR(100)")
    private String password;
}
