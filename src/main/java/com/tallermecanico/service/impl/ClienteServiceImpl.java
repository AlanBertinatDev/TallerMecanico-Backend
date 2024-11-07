package com.tallermecanico.service.impl;

import com.tallermecanico.model.Cliente;
import com.tallermecanico.repository.ClienteRepository;
import com.tallermecanico.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    @EntityGraph(attributePaths = {"vehiculo"})
    public List<Cliente> findAllClientsWithVehicles() {
        return clienteRepository.findAllWithVehiculos();
    }

    @Override
    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}