package com.iot.api.sensor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface SensorRepository extends JpaRepository<Sensor,Long> {
    @Query("SELECT s FROM Sensor s WHERE s.tipo=?1")
    Optional<List<Sensor>> findSensoresPorTipo(String tipo);

    @Query("SELECT s FROM Sensor s JOIN FETCH s.registros r WHERE r.valor=1.0 AND s.tipo='Puerta'")
    List<Sensor> findSensoresPuertaAbierta();

    @Modifying
    @Query("DELETE FROM Sensor s WHERE s.tipo=?1")
    void deleteSensoresPorTipo(String tipo);

}
