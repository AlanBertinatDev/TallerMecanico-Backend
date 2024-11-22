package com.tallermecanico.controller;


import com.tallermecanico.model.Cliente;
import com.tallermecanico.model.Producto;
import com.tallermecanico.service.ProductoService;
import com.tallermecanico.service.impl.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.getAll();
    }

    @PostMapping
    public ResponseEntity<Producto> addProducto(@RequestBody Producto producto){
        Producto nuevoProducto = productoService.crearProducto(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto updatedProducto = productoService.updateProducto(id, producto);
        return ResponseEntity.ok(updatedProducto);
    }
}
