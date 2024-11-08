package com.tallermecanico.controller;


import com.tallermecanico.model.Cliente;
import com.tallermecanico.model.Vehiculo;
import com.tallermecanico.service.ClienteService;
import com.tallermecanico.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VehiculoService vehiculoService;

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente nuevaCliente = clienteService.crearCliente(cliente);
        return ResponseEntity.ok(nuevaCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente updatedCliente = clienteService.updateCliente(id, cliente);
        return ResponseEntity.ok(updatedCliente);
    }

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.findAllClientsWithVehicles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{clienteId}/vehiculos")
    public ResponseEntity<?> agregarVechiculo(@PathVariable Long clienteId, @RequestBody Vehiculo vehiculo) {
        Optional<Cliente> clienteOptional = Optional.ofNullable(clienteService.obtenerClientePorId(clienteId));

        if (clienteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron datos del cliente.");
        }

        Cliente cliente = clienteOptional.get();
        vehiculo.setCliente(cliente);
        vehiculoService.agregarVehiculo(vehiculo);

        return ResponseEntity.status(HttpStatus.CREATED).body("Vehículo añadido correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
