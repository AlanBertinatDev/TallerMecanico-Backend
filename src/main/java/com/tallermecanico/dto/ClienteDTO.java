package com.tallermecanico.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClienteDTO {
    private Long id;                       // ID del cliente
    private String nombre;                 // Nombre del cliente
    private List<VehiculoDTO> vehiculos;   // Lista de vehículos asociados al cliente
}
