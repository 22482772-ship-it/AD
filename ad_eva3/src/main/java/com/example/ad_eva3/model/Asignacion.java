package com.example.ad_eva3.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "asignacion")
public class Asignacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // MUCHOS a UNO: Muchas asignaciones pueden pertenecer a un solo Usuario
@ManyToOne
@JoinColumn(name = "usuario_id")
@JsonBackReference // Corta el bucle infinito
    private Usuario usuario;

    // MUCHOS a UNO: Muchas asignaciones (a lo largo del tiempo) pueden recaer sobre un mismo Hardware
@ManyToOne
@JoinColumn(name = "hardware_id")
    private Hardware hardware;

    // MUCHOS a UNO: Muchas asignaciones pueden ser del mismo tipo de Accesorio
@ManyToOne
@JoinColumn(name = "accesorio_id")
    private Accesorio accesorio;
}
