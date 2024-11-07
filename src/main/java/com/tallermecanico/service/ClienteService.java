package com.tallermecanico.service;

import com.tallermecanico.model.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente crearCliente(Cliente cliente);
    List<Cliente> findAllClientsWithVehicles();
    Cliente obtenerClientePorId(Long id);
    void eliminarCliente(Long id);
}
