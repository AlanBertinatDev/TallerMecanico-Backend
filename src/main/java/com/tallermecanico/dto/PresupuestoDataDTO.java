package com.tallermecanico.dto;

import lombok.Data;

import java.util.List;

@Data
public class PresupuestoDataDTO {
    private List<PresupuestoDTO> presupuestos; // Lista de presupuestos
    private List<ClienteDTO> clientes;        // Lista de clientes con sus vehículos
    private List<ProductoDTO> productos;      // Lista de productos básicos
}
