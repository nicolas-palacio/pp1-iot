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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
            altasDeAreas();

            Area patio=new Area(
                    "Patio",
                    0

            );
            Area aula1=new Area(
                    "Aula 1",
                    1

            );

            areaRepository.save(patio);
            areaRepository.save(aula1);
            
          Sensor temperatura=new Sensor(
                  Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                  Enum.valueOf(EstadoSensor.class,"DISPONIBLE"),
                    "Celcius",
                    patio

            );
            Sensor puerta=new Sensor(
                    Enum.valueOf(TipoSensor.class,"PUERTA"),
                    Enum.valueOf(EstadoSensor.class,"SOLICITADO"),
                    "Boolean",
                    aula1
            );
            sensorRepository.save(temperatura);
            sensorRepository.save(puerta);

            LocalDateTime now=LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);

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

            String encodedPassword= bCryptPasswordEncoder.encode("secure190");
            usuarioService.saveUser(new Usuario("Admin","adminfake@gmail.com",encodedPassword,
                    Enum.valueOf(UsuarioRol.class,"ADMIN")));
            usuarioService.saveUser(new Usuario("Director","directorfake@gmail.com",encodedPassword,
                    Enum.valueOf(UsuarioRol.class,"ADMIN")));
        };

    }

    private void altasDeAreas(){
       //Aulas Primera Planta
        for(int i=1;i<=10;i++){
            Area aula=new Area("Aula "+i,1);
            areaRepository.save(aula);
        }

        //Aulas Segunda Planta
        for(int i=1;i<=10;i++){
            Area aula=new Area("Aula "+i+10,2);
            areaRepository.save(aula);
        }

        //Aulas Tercera Planta
        for(int i=1;i<=6;i++){
            Area aula=new Area("Aula "+i+20,3);
            areaRepository.save(aula);
        }

        //Oficinas Planta Baja
        for(int i=1;i<=6;i++){
            Area oficina=new Area("Oficina "+i,0);
            areaRepository.save(oficina);
        }

        //Oficinas Primera Planta
        for(int i=1;i<=2;i++){
            Area oficina=new Area("Oficina "+i+6,1);
            areaRepository.save(oficina);
        }

        //Oficinas Segunda Planta
        for(int i=1;i<=3;i++){
            Area oficina=new Area("Oficina "+i+8,1);
            areaRepository.save(oficina);
        }

        //Oficinas Tercera Planta
        Area oficina=new Area("Oficina 1",3);

        //Patios Planta Baja
        for(int i=1;i<=2;i++){
            Area patio=new Area("Patio "+i,0);
            areaRepository.save(patio);
        }

        //Patio Primera Planta
        Area patio=new Area("Patio 3",1);
        areaRepository.save(patio);
        //Patio Tercera Planta
        Area patioTerceraPlanta=new Area("Patio 4",3);
        areaRepository.save(patioTerceraPlanta);

        Area gymPlantaBaja=new Area("Gym 1",0);
        Area gymTerceraPlanta=new Area("Gym 2",3);

        //Pasillos
        for(int i=0;i<=3;i++){
            for(int k=1;k<=3;k++){
                Area pasillo=new Area("Pasillo "+k,i);
                areaRepository.save(pasillo);
            }
        }
        Area laboFisicaPrimeraPlanta=new Area("Laboratorio Fisica 1",1);
        Area laboFisicaSegundaPlanta=new Area("Laboratorio Fisica 2",2);
        Area laboQuimPrimeraPlanta=new Area("Laboratorio Quimica 1",1);
        Area laboQuimSegundaPlanta=new Area("Laboratorio Quimica 2",2);
        Area laboBioPrimeraPlanta=new Area("Laboratorio Biologia 1",1);
        Area laboBioSegundaPlanta=new Area("Laboratorio Biologia 2",2);
        areaRepository.saveAll(List.of(laboFisicaPrimeraPlanta,laboBioSegundaPlanta,laboBioPrimeraPlanta,laboFisicaSegundaPlanta,laboQuimPrimeraPlanta,laboQuimSegundaPlanta));

        //Aulas virtuales
        for(int i=1;i<=3;i++){
            Area aulaVirtual=new Area("Aula virtual "+i,i);
            areaRepository.save(aulaVirtual);
        }

        //Baños Planta Baja
        for(int i=1;i<=6;i++){
            Area baño=new Area("Baño "+i,0);
            areaRepository.save(baño);
        }

        //Baños Primera Planta
        for(int i=7;i<=9;i++){
            Area baño=new Area("Baño "+i,1);
            areaRepository.save(baño);
        }

        //Baños Segunda Planta
        for(int i=10;i<=13;i++){
            Area baño=new Area("Baño "+i,2);
            areaRepository.save(baño);
        }

        //Baños Tercera Planta
        for(int i=14;i<=16;i++){
            Area baño=new Area("Baño "+i,3);
            areaRepository.save(baño);
        }

        Area cocina=new Area("Cocina",0);
        Area comedorPlantaBaja=new Area("Comedor 1",0);
        Area comedorTerceraPlanta=new Area("Comedor 2",3);
        Area biblioteca=new Area("Biblioteca",0);
        Area salonDeActosPlantaBaja=new Area("Salon de actos 1",0);
        Area salonDeActosPrimPlanta=new Area("Salon de actos 2",1);
        areaRepository.saveAll(List.of(cocina,comedorPlantaBaja,comedorTerceraPlanta,biblioteca,salonDeActosPlantaBaja,salonDeActosPrimPlanta));
    }
}
