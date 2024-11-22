package com.tallermecanico.service;

import com.tallermecanico.model.Cliente;
import com.tallermecanico.model.Producto;

import java.util.List;

public interface ProductoService {
    Producto crearProducto(Producto producto);
    Producto obtenerProductoPorId(Long id);

    public List<Producto> getAll();

    Producto updateProducto(Long id, Producto producto);
    void eliminarProducto(Long id);
}
