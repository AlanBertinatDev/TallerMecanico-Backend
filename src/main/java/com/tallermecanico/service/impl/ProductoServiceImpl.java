package com.tallermecanico.service.impl;

import com.tallermecanico.model.Producto;
import com.tallermecanico.repository.ProductoRepository;
import com.tallermecanico.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Long id, Producto producto) {
        // Busca que el producto exista en la base
        Optional<Producto> existingProducto = productoRepository.findById(id);

        if (!existingProducto.isPresent()) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }

        // Actualizar los atributos del cliente
        Producto productoToUpdate = existingProducto.get();
        productoToUpdate.setNombre(producto.getNombre());
        productoToUpdate.setDescripcion(producto.getDescripcion());
        productoToUpdate.setStock(producto.getStock());
        productoToUpdate.setPrecioVenta(producto.getPrecioVenta());
        productoToUpdate.setPrecioCompra(producto.getPrecioCompra());

        // Guardar el cliente actualizado
        return productoRepository.save(productoToUpdate);
    }
    @Override
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Producto> getAll(){
        return productoRepository.findAll();
    }
    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
