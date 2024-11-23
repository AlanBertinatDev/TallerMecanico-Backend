package com.tallermecanico.controller;

import com.tallermecanico.model.Presupuesto;
import com.tallermecanico.service.PresupuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/presupuestos")
public class PresupuestoController {

    @Autowired
    private PresupuestoService presupuestoService;

    @PostMapping
    public ResponseEntity<Presupuesto> crearPresupuesto(@RequestBody Presupuesto presupuesto) {
        Presupuesto nuevaPresupuesto = presupuestoService.crearPresupuesto(presupuesto);
        return ResponseEntity.ok(nuevaPresupuesto);
    }

    @PutMapping("/presupuestos/{id}")
    public ResponseEntity<?> actualizarEstado(@PathVariable Long id, @RequestBody Presupuesto.Estado nuevoEstado) {
        Optional<Presupuesto> optionalPresupuesto = presupuestoService.findByID(id);

        // Verificar si el presupuesto existe
        if (optionalPresupuesto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Presupuesto no encontrado");
        }

        Presupuesto presupuesto = optionalPresupuesto.get();

        // Verificar si el presupuesto ya está cancelado
        if (presupuesto.getEstado() == Presupuesto.Estado.CANCELADO) {
            return ResponseEntity.badRequest().body("No se puede cambiar el estado de un presupuesto cancelado");
        }

        // Actualizar el estado
        presupuesto.setEstado(nuevoEstado);

        // Guardar el presupuesto actualizado
        presupuestoService.crearPresupuesto(presupuesto);

        return ResponseEntity.ok("Estado actualizado");
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
