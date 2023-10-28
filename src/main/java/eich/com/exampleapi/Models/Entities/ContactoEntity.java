package eich.com.exampleapi.Models.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contactos")
public class ContactoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT UNSIGNED")
    private Integer id;

    @Column(name = "nombre", columnDefinition = "VARCHAR(100)")
    private String nombre;

    @Column(name = "celular", columnDefinition = "INT")
    private Integer celular;

    @Column(name = "username", columnDefinition = "VARCHAR(150)")
    private String username;

    @Column(name = "contrasena", columnDefinition = "VARCHAR(100)")
    private String password;
}