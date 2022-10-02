package com.iot.api.area;

import com.iot.api.registro.Registro;
import com.iot.api.sensor.Sensor;
import com.iot.api.sensor.SensorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AreaServiceImpl implements AreaService{
    private AreaRepository areaRepository;
    private SensorRepository sensorRepository;

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

    @Override
    public List<Area> getAreasPuertasAbiertas() {
        List<Area> areas=areaRepository.getAreasPuertas();
        List<Sensor> sensores=getUltimoRegistro(sensorRepository.findSensoresPuertaAbierta());

        for(Area area:areas){
            for(Sensor sensor:area.getSensores()){
                if(!sensores.contains(sensor)){
                    area.getSensores().remove(sensor);
                }
            }
        }

        return areas;
    }

    private List<Sensor> getUltimoRegistro(List<Sensor>sensores){
        List<Sensor> sensoresConUltimoRegistro=sensores;

        for(Sensor sensor:sensoresConUltimoRegistro){
            if(sensor.getRegistros().size()>1){
                Registro ultimoRegistro=sensor.getRegistros().get(sensor.getRegistros().size()-1);
                sensor.getRegistros().clear();
                sensor.setRegistros(List.of(ultimoRegistro));
            }
        }

        return sensoresConUltimoRegistro;
    }
}
