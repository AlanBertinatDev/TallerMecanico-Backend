package com.tallermecanico.controller;


import com.tallermecanico.model.Orden;
import com.tallermecanico.model.Presupuesto;
import com.tallermecanico.service.PresupuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/presupuesto")
public class PresupuestoController {

    @Autowired
    private PresupuestoService presupuestoService;

    @PostMapping
    public ResponseEntity<Presupuesto> crearPresupuesto(@RequestBody Presupuesto presupuesto) {
        Presupuesto nuevaPresupuesto = presupuestoService.crearPresupuesto(presupuesto);
        return ResponseEntity.ok(nuevaPresupuesto);
    }

    @GetMapping
    public List<Presupuesto> getAllPresupuestos() {
        return presupuestoService.getAllPresupuestos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Presupuesto> obtenerPresupuestoPorId(@PathVariable Long id) {
        Presupuesto presupuesto = presupuestoService.obtenerPresupuestoPorId(id);
        return presupuesto != null ? ResponseEntity.ok(presupuesto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPresupuesto(@PathVariable Long id) {
        presupuestoService.eliminarPresupuesto(id);
        return ResponseEntity.noContent().build();
    }
}
