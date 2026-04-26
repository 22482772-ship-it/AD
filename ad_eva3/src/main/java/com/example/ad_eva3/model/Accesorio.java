package com.example.ad_eva3.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accesorio")
public class Accesorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;

    public enum tipo_accesorio {
        TECLADO,
        RATON,
        HEADSET,
        CARGADOR
    }

    public enum estado {
        DISPONIBLE,
        ASIGNADO,
        REPARACION
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_accesorio")
    private tipo_accesorio tipoAccesorio;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private estado estado;
}
