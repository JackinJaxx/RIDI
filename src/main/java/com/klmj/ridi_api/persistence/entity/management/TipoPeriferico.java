package com.klmj.ridi_api.persistence.entity.management;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"descripcion"})

@Entity(name = "tipos_periferico")
public class TipoPeriferico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo", nullable = false) // Especifica el nombre de la columna en la base de datos
    private Long id;
    @Column(nullable = false)
    private String descripcion;
}
