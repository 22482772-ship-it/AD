package com.example.ad_eva3.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hardware")

public class Hardware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String sn;

    @Column
    private String modelo;

    public enum TipoDispositivo {
        LAPTOP,
        SMARTPHONE,
        MONITOR,
        TABLET
    }

    public enum Estado {
        DISPONIBLE,
        ASIGNADO,
        REPARACION
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_dispositivo")
    private TipoDispositivo tipoDispositivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;
}
