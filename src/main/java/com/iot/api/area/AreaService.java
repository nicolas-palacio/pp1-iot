package com.iot.api.area;

import com.iot.api.sensor.Sensor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AreaService {
    Map<String,Object> getAreas() throws CloneNotSupportedException;
    Optional<Area> getArea(Long id);
    void postArea(Area area);
    void deleteArea(Long id);
    List<Area> getAreasPuertasAbiertas();

    Map<String, Object> getNombreAreas();
    Map<String, Object> getNombreAreasPorPiso(int piso);
}
