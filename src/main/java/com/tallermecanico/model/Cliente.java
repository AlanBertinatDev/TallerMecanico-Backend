package com.tallermecanico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "contacto", nullable = false)
    private String contacto;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Vehiculo> vehiculos;

    @OneToMany(mappedBy = "cliente")
    private List<Orden> ordenes;
}


