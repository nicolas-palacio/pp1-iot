package com.iot.api.sensor;


import com.iot.api.area.Area;
import com.iot.api.area.AreaRepository;
import com.iot.api.sensor.util.SensorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SensorServiceImpl implements  SensorService{

    private SensorRepository sensorRepository;
    private AreaRepository areaRepository;


    @Override
    public List<Sensor> getTodosLosSensores() {
        return sensorRepository.findAll();
    }

    @Override
    public Optional<List<Sensor>> getSensoresPorTipo(String tipo) {
        return sensorRepository.findSensoresPorTipo(tipo);
    }

    @Override
    public Optional<Sensor> getSensor(Long id) {
        return sensorRepository.findById(id);
    }

    @Override
    public Sensor postSensor(SensorContext sensor) {
        Optional<Area> area=areaRepository.findById(sensor.getAreaId());
        Sensor sensorPost=new Sensor(sensor.getTipo(),sensor.getUnidadDeMedida(),sensor.getDescripcion(),area.get());
        sensorRepository.save(sensorPost);
        return sensorPost;
    }

    @Override
    public Sensor deleteSensor(Long id) {
        Optional<Sensor> sensor=sensorRepository.findById(id);
        sensorRepository.deleteById(id);

        return sensor.get();
    }

    @Override
    public Optional<List<Sensor>> deleteSensoresPorTipo(String tipo) {

        Optional<List<Sensor>> sensores=sensorRepository.findSensoresPorTipo(tipo);
        /*if(!sensores.isEmpty()){
            for(Sensor sensor:sensores.get()) {
                    System.out.println("ACAAA "+sensor.getId());
                    sensorRepository.deleteRegistrosPorSensorId(sensor.getId());
            }
        }*/
        sensorRepository.deleteSensoresPorTipo(tipo);
        return sensores;
    }
}
