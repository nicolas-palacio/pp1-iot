package com.iot.api;

import com.iot.api.registro.Registro;
import com.iot.api.registro.RegistroRepository;
import com.iot.api.area.Area;
import com.iot.api.area.AreaRepository;
import com.iot.api.sensor.Sensor;
import com.iot.api.sensor.SensorRepository;
import com.iot.api.sensor.util.EstadoSensor;
import com.iot.api.sensor.util.TipoSensor;
import com.iot.api.usuario.Usuario;
import com.iot.api.usuario.UsuarioRol;
import com.iot.api.usuario.UsuarioServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;

@Configuration
@AllArgsConstructor
public class Config {
    @Autowired
    SensorRepository sensorRepository;
    @Autowired
    AreaRepository areaRepository;
    @Autowired
    RegistroRepository registroRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

@Bean
    CommandLineRunner commandLineRunnerSensor(UsuarioServiceImpl usuarioService){
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
                  Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                  Enum.valueOf(EstadoSensor.class,"DISPONIBLE"),
                    "Celcius",
                    "Ubicado en",
                    patio

            );
            Sensor puerta=new Sensor(
                    Enum.valueOf(TipoSensor.class,"PUERTA"),
                    Enum.valueOf(EstadoSensor.class,"SOLICITADO"),
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

            //Guarda el usuario con rol de Administrador
            String encodedPassword= bCryptPasswordEncoder.encode("secure190");
            usuarioService.saveUser(new Usuario("Admin","adminfake@gmail.com",encodedPassword,
                    Enum.valueOf(UsuarioRol.class,"ADMIN")));
            usuarioService.saveUser(new Usuario("Director","directorfake@gmail.com",encodedPassword,
                    Enum.valueOf(UsuarioRol.class,"ADMIN")));
        };

    }
}
