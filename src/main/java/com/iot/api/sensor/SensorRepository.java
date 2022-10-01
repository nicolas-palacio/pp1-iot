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
    @Modifying

    @Query("DELETE FROM Sensor s WHERE s.tipo=?1")
    void deleteSensoresPorTipo(String tipo);

   /* @Query(value = "DELETE FROM Registro r WHERE r.sensor_id=?1")
    void deleteRegistrosPorSensorId(Long id);*/

}
