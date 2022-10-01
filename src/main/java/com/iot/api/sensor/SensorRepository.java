package com.iot.api.sensor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface SensorRepository extends JpaRepository<Sensor,Long> {
   /* @Query("SELECT * FROM Sensor")
    List<Sensor> findAllSensores();*/

}
