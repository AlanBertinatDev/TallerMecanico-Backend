package com.tallermecanico.service;

import com.tallermecanico.model.Orden;
import java.util.List;

public interface OrdenService {
    Orden crearOrden(Orden orden);
    List<Orden> obtenerTodasLasOrdenes();
    Orden obtenerOrdenPorId(Long id);
    void eliminarOrden(Long id);
}
