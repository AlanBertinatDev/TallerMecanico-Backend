package com.tallermecanico.service.impl;

import com.tallermecanico.dto.ClienteDTO;
import com.tallermecanico.dto.PresupuestoDataDTO;
import com.tallermecanico.dto.PresupuestoDTO;
import com.tallermecanico.dto.ProductoDTO;
import com.tallermecanico.dto.VehiculoDTO;
import com.tallermecanico.model.Presupuesto;
import com.tallermecanico.repository.*;
import com.tallermecanico.service.PresupuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PresupuestoServiceImpl implements PresupuestoService {

    @Autowired
    private PresupuestoRepository presupuestoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PresupuestoClienteVehiculoRepository presupuestoClienteVehiculoRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

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

    @Override
    public Optional<Presupuesto> findByID(Long idPresupuesto){
        return presupuestoRepository.findById(idPresupuesto);
    }

    @Override
    public PresupuestoDataDTO obtenerDatosCombinados() {
        // 1. Obtener presupuestos con cliente y vehículo
        List<PresupuestoDTO> presupuestos = presupuestoRepository.findAll()
                .stream()
                .map(p -> {
                    PresupuestoDTO dto = new PresupuestoDTO();
                    dto.setId(p.getId());
                    dto.setEstado(p.getEstado().name());
                    dto.setTotal(p.getTotal_estimado());
                    dto.setNombreCliente(obtenerNombreCliente(p.getId())); // Personaliza estas funciones
                    dto.setMatriculaVehiculo(obtenerMatriculaVehiculo(p.getId()));
                    return dto;
                })
                .toList();

        // 2. Obtener clientes con sus vehículos
        List<ClienteDTO> clientes = clienteRepository.findAllWithVehiculos()
                .stream()
                .map(c -> {
                    ClienteDTO clienteDTO = new ClienteDTO();
                    clienteDTO.setId(c.getId());
                    clienteDTO.setNombre(c.getNombre());
                    clienteDTO.setVehiculos(c.getVehiculos().stream()
                            .map(v -> {
                                VehiculoDTO vehiculoDTO = new VehiculoDTO();
                                vehiculoDTO.setId(v.getId());
                                vehiculoDTO.setMatricula(v.getMatricula());
                                return vehiculoDTO;
                            })
                            .toList());
                    return clienteDTO;
                })
                .toList();

        // 3. Obtener productos básicos
        List<ProductoDTO> productos = productoRepository.findAll()
                .stream()
                .map(p -> {
                    ProductoDTO productoDTO = new ProductoDTO();
                    productoDTO.setId(p.getId());
                    productoDTO.setNombre(p.getNombre());
                    productoDTO.setPrecioVenta(p.getPrecioVenta());
                    productoDTO.setStock(p.getStock());
                    return productoDTO;
                })
                .toList();

        // 4. Crear el DTO combinado
        PresupuestoDataDTO data = new PresupuestoDataDTO();
        data.setPresupuestos(presupuestos);
        data.setClientes(clientes);
        data.setProductos(productos);

        return data;
    }

    private String obtenerNombreCliente(Long presupuestoId) {
        // Implementar lógica para buscar el cliente asociado
        return presupuestoClienteVehiculoRepository.findClienteNombreByPresupuestoId(presupuestoId);
    }

    private String obtenerMatriculaVehiculo(Long presupuestoId) {
        // Implementar lógica para buscar el vehículo asociado
        return presupuestoClienteVehiculoRepository.findVehiculoMatriculaByPresupuestoId(presupuestoId);
    }


}
