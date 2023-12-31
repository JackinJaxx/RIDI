package com.klmj.ridi_api.persistence.entity.location;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Kevin Alejandro Francisco Gonzalez
 * @author Jose Alejandro Perez Chavez
 * @author Mauricio Betancourt Mora
 * @author Luis Hurtado Gomez
 * @version 1.0
 * Esta clase hace referencia a un lugar, a un espacio geográfico en el cual está ubicado el lugar en
 * donde se puede encontrar cualquier tipo de dispositivo, básicamente son los posibles lugares
 * registrados donde opera el negocio.
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"nombre", "direccion", "municipio"})

@Entity(name = "locaciones")
public class Locacion {
    @Id
    @Column(name = "id_locacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false , length = 50, unique = true)
    private String nombre;
    @Column(nullable = false , length = 100)
    private String direccion;
    @ManyToOne
    @JoinColumn(name = "id_municipio", nullable = false)
    private Municipio municipio;
}
