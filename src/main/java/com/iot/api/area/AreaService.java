package com.iot.api.area;

import java.util.List;
import java.util.Optional;

public interface AreaService {
    List<Area> getAreas();
    Optional<Area> getArea(Long id);
    void postArea(Area area);
    void deleteArea(Long id);
}
