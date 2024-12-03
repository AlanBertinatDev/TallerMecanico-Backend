package com.tallermecanico.repository;

import com.tallermecanico.model.PresupuestoClienteVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PresupuestoClienteVehiculoRepository extends JpaRepository<PresupuestoClienteVehiculo, Long> {
    @Query("SELECT c.nombre FROM PresupuestoClienteVehiculo pcv JOIN pcv.cliente c WHERE pcv.presupuesto.id = :presupuestoId")
    String findClienteNombreByPresupuestoId(@Param("presupuestoId") Long presupuestoId);

    @Query("SELECT v.matricula FROM PresupuestoClienteVehiculo pcv JOIN pcv.vehiculo v WHERE pcv.presupuesto.id = :presupuestoId")
    String findVehiculoMatriculaByPresupuestoId(@Param("presupuestoId") Long presupuestoId);
}
