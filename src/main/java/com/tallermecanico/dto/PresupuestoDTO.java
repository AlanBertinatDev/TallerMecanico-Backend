package com.tallermecanico.dto;

import lombok.Data;

import java.util.List;

@Data
public class PresupuestoDTO {
    private Long id;                  // ID del presupuesto
    private String estado;            // Estado del presupuesto (PENDIENTE, CANCELADO, REALIZADO)
    private Double total;             // Total estimado del presupuesto
    private String nombreCliente;     // Nombre del cliente asociado
    private String matriculaVehiculo;
}
