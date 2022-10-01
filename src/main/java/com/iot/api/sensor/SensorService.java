package com.iot.api.sensor;

import com.iot.api.sensor.util.SensorContext;

import java.util.List;
import java.util.Optional;

public interface SensorService {
    List<Sensor> getTodosLosSensores();
    Optional<List<Sensor>> getSensoresPorTipo(String tipo);
    Optional<Sensor> getSensor(Long id);
    Sensor postSensor(SensorContext sensor);
    Sensor deleteSensor(Long id);
    Optional<List<Sensor>> deleteSensoresPorTipo(String tipo);





}
