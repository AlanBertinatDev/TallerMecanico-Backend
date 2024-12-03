package com.tallermecanico.service;

import com.tallermecanico.model.Presupuesto;
import com.tallermecanico.dto.PresupuestoDataDTO;

import java.util.List;
import java.util.Optional;

public interface PresupuestoService {
    Presupuesto crearPresupuesto(Presupuesto presupuesto);
    List<Presupuesto> getAllPresupuestos();
    Presupuesto obtenerPresupuestoPorId(Long id);
    void eliminarPresupuesto(Long id);

    Optional<Presupuesto> findByID(Long idPresupuesto);

    PresupuestoDataDTO obtenerDatosCombinados();

}
