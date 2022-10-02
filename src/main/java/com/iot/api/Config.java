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
            Area aula1=new Area(
                    "Aula 1",
                    1,
                    "Zona cerrada"

            );

            areaRepository.save(patio);
            areaRepository.save(aula1);
          Sensor temperatura=new Sensor(
                    "Temperatura",
                    "Celcius",
                    "Ubicado en",
                    patio

            );
            Sensor puerta=new Sensor(
                    "Puerta",
                    "Boolean",
                    "Ubicado en el Aula 1",
                    aula1
            );
            sensorRepository.save(temperatura);
            sensorRepository.save(puerta);

            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);

            Registro registroTemp=new Registro(
                    timestamp,
                    "Celcius",
                    40.3,
                    temperatura,
                    60.0
            );

            Registro registroPuerta0=new Registro(
                    timestamp,
                    "Boolean",
                    1.0,
                    puerta,
                    30.0
            );

            Registro registroPuerta1=new Registro(
                    timestamp,
                    "Boolean",
                    0.0,
                    puerta,
                    30.0
            );

            Registro registroPuerta=new Registro(
                    timestamp,
                    "Boolean",
                    1.0,
                    puerta,
                    30.0
            );
            registroRepository.save(registroPuerta0);
            registroRepository.save(registroPuerta1);
            registroRepository.save(registroTemp);
            registroRepository.save(registroPuerta);

        };

    }
}
