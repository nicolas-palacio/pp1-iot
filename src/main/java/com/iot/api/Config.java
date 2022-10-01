package com.iot.api;

import com.iot.api.registro.Registro;
import com.iot.api.registro.RegistroRepository;
import com.iot.api.area.Area;
import com.iot.api.area.AreaRepository;
import com.iot.api.sensor.Sensor;
import com.iot.api.sensor.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;

@Configuration
public class Config {
    @Autowired
    SensorRepository sensorRepository;
    @Autowired
    AreaRepository areaRepository;
    @Autowired
    RegistroRepository registroRepository;

@Bean
    CommandLineRunner commandLineRunnerSensor(){
        return args -> {
            Area patio=new Area(
                    "Patio",
                    0,
                    "Zona abierta"

            );
            areaRepository.save(patio);
          Sensor temperatura=new Sensor(
                    "Temperatura",
                    "Celcius",
                    "Ubicado en",
                    patio

            );
            sensorRepository.save(temperatura);

            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);

            Registro registroTemp=new Registro(
                    timestamp,
                    "Celcius",
                    40.0,
                    temperatura
            );

            registroRepository.save(registroTemp);

        };

    }
}
