package com.klmj.ridi_api.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Alejandro Francisco Gonzalez
 * @author Jose Alejandro Perez Chavez
 * @author Mauricio Betancourt Mora
 * @author Luis Hurtado Gomez
 * @version 1.0
 * Es un usuario que tiene acceso al aplicativo, con sus respectivos permisos.
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"correo", "no_telefono", "nombres", "apellido_p", "apellido_m", "password", "salt", "grupo"})

@Entity(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "correo_electronico", nullable = false, unique = true)
    private String correo;
    @Column(name = "no_telefono", nullable = false)
    private String no_telefono;
    @Column(name = "nombres", nullable = false)
    private String nombres;
    @Column(name = "apellido_p", nullable = false)
    private String apellido_p;
    @Column(name = "apellido_m")
    private String apellido_m;
    @Column(name = "password", nullable = false)
    private String password;
    @JsonIgnore
    @Column(name = "salt")
    private String salt;
    @Enumerated(EnumType.STRING)
    private Grupo grupo;

}