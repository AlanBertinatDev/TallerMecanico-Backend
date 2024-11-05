package com.tallermecanico.service.impl;

import com.tallermecanico.model.Presupuesto;
import com.tallermecanico.repository.PresupuestoRepository;
import com.tallermecanico.service.PresupuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresupuestoServiceImpl implements PresupuestoService {

    @Autowired
    private PresupuestoRepository presupuestoRepository;

    @Override
    public Presupuesto crearPresupuesto(Presupuesto presupuesto) {
        return presupuestoRepository.save(presupuesto);
    }

    @Override
    public List<Presupuesto> getAllPresupuestos() {
        return presupuestoRepository.findAll();
    }

    @Override
    public Presupuesto obtenerPresupuestoPorId(Long id) {
        return presupuestoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarPresupuesto(Long id) {
        presupuestoRepository.deleteById(id);
    }
}
