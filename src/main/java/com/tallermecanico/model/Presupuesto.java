package com.tallermecanico.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "presupuesto")
public class Presupuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fecha_creacion;

    @Column(name ="fecha_realizado", nullable = false)
    private Date fecha_realizado;

    @Column(name = "total_estimado", nullable = false)
    private Double total_estimado;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado = Estado.PENDIENTE;

    @OneToMany(mappedBy = "presupuesto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PresupuestoClienteVehiculo> clienteVehiculoRelaciones;

    public enum Estado {
        PENDIENTE,
        CANCELADO,
        REALIZADO
    }
}

