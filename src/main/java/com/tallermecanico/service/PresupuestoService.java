package com.tallermecanico.service;

import com.tallermecanico.model.Presupuesto;

import java.util.List;

public interface PresupuestoService {
    Presupuesto crearPresupuesto(Presupuesto presupuesto);
    List<Presupuesto> getAllPresupuestos();
    Presupuesto obtenerPresupuestoPorId(Long id);
    void eliminarPresupuesto(Long id);
}
