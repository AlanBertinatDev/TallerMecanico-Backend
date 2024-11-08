package com.tallermecanico.service.impl;

import com.tallermecanico.model.Cliente;
import com.tallermecanico.repository.ClienteRepository;
import com.tallermecanico.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente cliente) {
        // Buscar el cliente existente en la base de datos
        Optional<Cliente> existingCliente = clienteRepository.findById(id);

        if (!existingCliente.isPresent()) {
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        }

        // Actualizar los atributos del cliente
        Cliente clienteToUpdate = existingCliente.get();
        clienteToUpdate.setNombre(cliente.getNombre());
        clienteToUpdate.setContacto(cliente.getContacto());

        // Guardar el cliente actualizado
        return clienteRepository.save(clienteToUpdate);
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