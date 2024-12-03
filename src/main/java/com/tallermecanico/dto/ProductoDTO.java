package com.tallermecanico.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    private Long id;              // ID del producto
    private String nombre;        // Nombre del producto
    private Double precioVenta;   // Precio de venta
    private Integer stock;        // Cantidad en stock
}
