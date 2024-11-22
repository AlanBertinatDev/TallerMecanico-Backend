package com.tallermecanico.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="descripcion", nullable = false)
    private String descripcion;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio_compra", nullable = false)
    private Double precioCompra;

    @Column(name = "precio_venta", nullable = false)
    private Double precioVenta;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "orden_id")
    private Long orden; // Este es el campo necesario para la relación bidireccional

}
