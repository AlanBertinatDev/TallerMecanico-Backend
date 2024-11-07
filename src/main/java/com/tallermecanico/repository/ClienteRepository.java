package com.tallermecanico.repository;

import com.tallermecanico.model.Cliente;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Usar EntityGraph para incluir solo los vehículos y evitar la carga de órdenes y presupuestos
    @EntityGraph(attributePaths = {"vehiculos"})
    List<Cliente> findAll();
}
