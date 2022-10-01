package com.iot.api.area;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AreaServiceImpl implements AreaService{
    private AreaRepository areaRepository;

    @Override
    public List<Area> getAreas() {
        return areaRepository.findAll();
    }

    @Override
    public Optional<Area> getArea(Long id) {
        return areaRepository.findById(id);
    }

    @Override
    public void postArea(Area area) {
        areaRepository.save(area);
    }

    @Override
    public void deleteArea(Long id) {
        areaRepository.deleteById(id);
    }
}
