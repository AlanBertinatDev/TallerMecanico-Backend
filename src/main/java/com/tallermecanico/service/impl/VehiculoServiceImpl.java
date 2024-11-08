package com.tallermecanico.service.impl;

import com.tallermecanico.model.Vehiculo;
import com.tallermecanico.repository.VehiculoRepository;
import com.tallermecanico.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public Vehiculo agregarVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }
}
