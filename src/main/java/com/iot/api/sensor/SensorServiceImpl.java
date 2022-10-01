package com.iot.api.sensor;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SensorServiceImpl implements  SensorService{

    private SensorRepository sensorRepository;


    @Override
    public List<Sensor> getTodosLosSensores() {
        return sensorRepository.findAll();
    }
}
