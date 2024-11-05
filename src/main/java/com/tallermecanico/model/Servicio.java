package com.tallermecanico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "servicio_producto",
            joinColumns = @JoinColumn(name = "servicio_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> repuestosUsados;

    @Column(name = "costo_servicio", nullable = false)
    private Double costoManoDeObra;

    @Column(name = "fecha_servicio", nullable = false)
    private Date fechaServicio;

    @ManyToOne // Cambiado a ManyToOne para reflejar que un servicio pertenece a una orden
    @JoinColumn(name = "orden_id", nullable = false)
    private Orden orden;
}
