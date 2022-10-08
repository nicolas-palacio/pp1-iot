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
            Sensor humedad=new Sensor(
                    Enum.valueOf(TipoSensor.class,"HUMEDAD"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Porcentaje", aula);
            Sensor temperatura=new Sensor(
                    Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Celcius", aula);
            Sensor nivelDeRuido=new Sensor(
                    Enum.valueOf(TipoSensor.class,"NIVEL_DE_RUIDO"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Decibeles", aula);
            Sensor calidadDeAire=new Sensor(
                    Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", aula);
            Sensor puerta=new Sensor(
                    Enum.valueOf(TipoSensor.class,"PUERTA"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", aula);

            sensorRepository.saveAll(List.of(humedad,temperatura,nivelDeRuido,calidadDeAire,puerta));
        }

        //Aulas Segunda Planta
        for(int i=1;i<=10;i++){
            Area aula=new Area("Aula "+i+10,2);
            areaRepository.save(aula);
            Sensor humedad=new Sensor(
                    Enum.valueOf(TipoSensor.class,"HUMEDAD"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Porcentaje", aula);
            Sensor temperatura=new Sensor(
                    Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Celcius", aula);
            Sensor nivelDeRuido=new Sensor(
                    Enum.valueOf(TipoSensor.class,"NIVEL_DE_RUIDO"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Decibeles", aula);
            Sensor calidadDeAire=new Sensor(
                    Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", aula);
            Sensor puerta=new Sensor(
                    Enum.valueOf(TipoSensor.class,"PUERTA"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", aula);

            sensorRepository.saveAll(List.of(humedad,temperatura,nivelDeRuido,calidadDeAire,puerta));
        }

        //Aulas Tercera Planta
        for(int i=1;i<=6;i++){
            Area aula=new Area("Aula "+i+20,3);
            areaRepository.save(aula);
            Sensor humedad=new Sensor(
                    Enum.valueOf(TipoSensor.class,"HUMEDAD"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Porcentaje", aula);
            Sensor temperatura=new Sensor(
                    Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Celcius", aula);
            Sensor nivelDeRuido=new Sensor(
                    Enum.valueOf(TipoSensor.class,"NIVEL_DE_RUIDO"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Decibeles", aula);
            Sensor calidadDeAire=new Sensor(
                    Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", aula);
            Sensor puerta=new Sensor(
                    Enum.valueOf(TipoSensor.class,"PUERTA"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", aula);

            sensorRepository.saveAll(List.of(humedad,temperatura,nivelDeRuido,calidadDeAire,puerta));
        }

        //Oficinas Planta Baja
        for(int i=1;i<=6;i++){
            Area oficina=new Area("Oficina "+i,0);
            areaRepository.save(oficina);
            Sensor humedad=new Sensor(
                    Enum.valueOf(TipoSensor.class,"HUMEDAD"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Porcentaje", oficina);
            Sensor temperatura=new Sensor(
                    Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Celcius", oficina);
            Sensor nivelDeRuido=new Sensor(
                    Enum.valueOf(TipoSensor.class,"NIVEL_DE_RUIDO"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Decibeles", oficina);
            Sensor calidadDeAire=new Sensor(
                    Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", oficina);
            Sensor puerta=new Sensor(
                    Enum.valueOf(TipoSensor.class,"PUERTA"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", oficina);

            sensorRepository.saveAll(List.of(humedad,temperatura,nivelDeRuido,calidadDeAire,puerta));
        }

        //Oficinas Primera Planta
        for(int i=1;i<=2;i++){
            Area oficina=new Area("Oficina "+i+6,1);
            areaRepository.save(oficina);
            Sensor humedad=new Sensor(
                    Enum.valueOf(TipoSensor.class,"HUMEDAD"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Porcentaje", oficina);
            Sensor temperatura=new Sensor(
                    Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Celcius", oficina);
            Sensor nivelDeRuido=new Sensor(
                    Enum.valueOf(TipoSensor.class,"NIVEL_DE_RUIDO"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Decibeles", oficina);
            Sensor calidadDeAire=new Sensor(
                    Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", oficina);
            Sensor puerta=new Sensor(
                    Enum.valueOf(TipoSensor.class,"PUERTA"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", oficina);


            sensorRepository.saveAll(List.of(humedad,temperatura,nivelDeRuido,calidadDeAire,puerta));
        }

        //Oficinas Segunda Planta
        for(int i=1;i<=3;i++){
            Area oficina=new Area("Oficina "+i+8,1);
            areaRepository.save(oficina);
            Sensor humedad=new Sensor(
                    Enum.valueOf(TipoSensor.class,"HUMEDAD"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Porcentaje", oficina);
            Sensor temperatura=new Sensor(
                    Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Celcius", oficina);
            Sensor nivelDeRuido=new Sensor(
                    Enum.valueOf(TipoSensor.class,"NIVEL_DE_RUIDO"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Decibeles", oficina);
            Sensor calidadDeAire=new Sensor(
                    Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", oficina);
            Sensor puerta=new Sensor(
                    Enum.valueOf(TipoSensor.class,"PUERTA"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", oficina);

            sensorRepository.saveAll(List.of(humedad,temperatura,nivelDeRuido,calidadDeAire,puerta));
        }

        //Oficinas Tercera Planta
        Area oficina=new Area("Oficina 1",3);
        areaRepository.save(oficina);
        Sensor humedad=new Sensor(
                Enum.valueOf(TipoSensor.class,"HUMEDAD"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Porcentaje", oficina);
        Sensor temperatura=new Sensor(
                Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Celcius", oficina);
        Sensor nivelDeRuido=new Sensor(
                Enum.valueOf(TipoSensor.class,"NIVEL_DE_RUIDO"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Decibeles", oficina);
        Sensor calidadDeAire=new Sensor(
                Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", oficina);
        Sensor puerta=new Sensor(
                Enum.valueOf(TipoSensor.class,"PUERTA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", oficina);
        sensorRepository.saveAll(List.of(humedad,temperatura,nivelDeRuido,calidadDeAire,puerta));

        //Patios Planta Baja
        for(int i=1;i<=2;i++){
            Area patio=new Area("Patio "+i,0);
            areaRepository.save(patio);
            Sensor temperaturaPatio=new Sensor(
                    Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Celcius", patio);
            Sensor calidadDeAirePatio=new Sensor(
                    Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", patio);

            sensorRepository.saveAll(List.of(temperaturaPatio,calidadDeAirePatio));
        }

        //Patio Primera Planta
        Area patio=new Area("Patio 3",1);
        areaRepository.save(patio);
        Sensor temperaturaPatio=new Sensor(
                Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Celcius", patio);
        Sensor calidadDeAirePatio=new Sensor(
                Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", patio);

        sensorRepository.saveAll(List.of(temperaturaPatio,calidadDeAirePatio));
        //Patio Tercera Planta
        Area patioTerceraPlanta=new Area("Patio 4",3);
        areaRepository.save(patioTerceraPlanta);
        Sensor temperaturaPatioPlanta3=new Sensor(
                Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Celcius", patioTerceraPlanta);
        Sensor calidadDeAirePatioPlanta3=new Sensor(
                Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", patioTerceraPlanta);

        sensorRepository.saveAll(List.of(temperaturaPatioPlanta3,calidadDeAirePatioPlanta3));

        Area gymPlantaBaja=new Area("Gym 1",0);
        Sensor calidadDeAireGymPlanta0=new Sensor(
                Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", gymPlantaBaja);
        Sensor puertaGymPlanta0=new Sensor(
                Enum.valueOf(TipoSensor.class,"PUERTA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", gymPlantaBaja);
        Area gymTerceraPlanta=new Area("Gym 2",3);
        Sensor calidadDeAireGymPlanta3=new Sensor(
                Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", gymTerceraPlanta);
        Sensor puertaGymPlanta3=new Sensor(
                Enum.valueOf(TipoSensor.class,"PUERTA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", gymTerceraPlanta);
        areaRepository.saveAll(List.of(gymPlantaBaja,gymTerceraPlanta));
        sensorRepository.saveAll(List.of(calidadDeAireGymPlanta0,puertaGymPlanta0,calidadDeAireGymPlanta3,puertaGymPlanta3));

        //Pasillos
        for(int i=0;i<=3;i++){
            for(int k=1;k<=3;k++){
                Area pasillo=new Area("Pasillo "+k,i);
                areaRepository.save(pasillo);
            }
        }
        Area laboFisicaPrimeraPlanta=new Area("Laboratorio Fisica 1",1);
        Sensor humedadLabo1=new Sensor(
                Enum.valueOf(TipoSensor.class,"HUMEDAD"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Porcentaje", laboFisicaPrimeraPlanta);
        Sensor temperaturaLabo1=new Sensor(
                Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Celcius", laboFisicaPrimeraPlanta);
        Sensor calidadDeAireLabo1=new Sensor(
                Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", laboFisicaPrimeraPlanta);
        Sensor puertaLabo1=new Sensor(
                Enum.valueOf(TipoSensor.class,"PUERTA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", laboFisicaPrimeraPlanta);

        Area laboFisicaSegundaPlanta=new Area("Laboratorio Fisica 2",2);
        Sensor humedadLabo2=new Sensor(
                Enum.valueOf(TipoSensor.class,"HUMEDAD"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Porcentaje", laboFisicaSegundaPlanta);
        Sensor temperaturaLabo2=new Sensor(
                Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Celcius", laboFisicaSegundaPlanta);
        Sensor calidadDeAireLabo2=new Sensor(
                Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", laboFisicaSegundaPlanta);
        Sensor puertaLabo2=new Sensor(
                Enum.valueOf(TipoSensor.class,"PUERTA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", laboFisicaSegundaPlanta);

        Area laboQuimPrimeraPlanta=new Area("Laboratorio Quimica 1",1);
        Sensor humedadLabo3=new Sensor(
                Enum.valueOf(TipoSensor.class,"HUMEDAD"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Porcentaje", laboQuimPrimeraPlanta);
        Sensor temperaturaLabo3=new Sensor(
                Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Celcius", laboQuimPrimeraPlanta);
        Sensor calidadDeAireLabo3=new Sensor(
                Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", laboQuimPrimeraPlanta);
        Sensor puertaLabo3=new Sensor(
                Enum.valueOf(TipoSensor.class,"PUERTA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", laboQuimPrimeraPlanta);

        Area laboQuimSegundaPlanta=new Area("Laboratorio Quimica 2",2);
        Sensor humedadLabo4=new Sensor(
                Enum.valueOf(TipoSensor.class,"HUMEDAD"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Porcentaje", laboQuimSegundaPlanta);
        Sensor temperaturaLabo4=new Sensor(
                Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Celcius", laboQuimSegundaPlanta);
        Sensor calidadDeAireLabo4=new Sensor(
                Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", laboQuimSegundaPlanta);
        Sensor puertaLabo4=new Sensor(
                Enum.valueOf(TipoSensor.class,"PUERTA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", laboQuimSegundaPlanta);


        Area laboBioPrimeraPlanta=new Area("Laboratorio Biologia 1",1);
        Sensor humedadLabo5=new Sensor(
                Enum.valueOf(TipoSensor.class,"HUMEDAD"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Porcentaje", laboBioPrimeraPlanta);
        Sensor temperaturaLabo5=new Sensor(
                Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Celcius", laboBioPrimeraPlanta);
        Sensor calidadDeAireLabo5=new Sensor(
                Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", laboBioPrimeraPlanta);
        Sensor puertaLabo5=new Sensor(
                Enum.valueOf(TipoSensor.class,"PUERTA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", laboBioPrimeraPlanta);

        Area laboBioSegundaPlanta=new Area("Laboratorio Biologia 2",2);
        Sensor humedadLabo6=new Sensor(
                Enum.valueOf(TipoSensor.class,"HUMEDAD"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Porcentaje", laboBioSegundaPlanta);
        Sensor temperaturaLabo6=new Sensor(
                Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Celcius", laboBioSegundaPlanta);
        Sensor calidadDeAireLabo6=new Sensor(
                Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", laboBioSegundaPlanta);
        Sensor puertaLabo6=new Sensor(
                Enum.valueOf(TipoSensor.class,"PUERTA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", laboBioSegundaPlanta);

        areaRepository.saveAll(List.of(laboFisicaPrimeraPlanta,laboBioSegundaPlanta,laboBioPrimeraPlanta,laboFisicaSegundaPlanta,laboQuimPrimeraPlanta,laboQuimSegundaPlanta));
        sensorRepository.saveAll(List.of(
                humedadLabo1,humedadLabo2,humedadLabo3,humedadLabo4,humedadLabo5,humedadLabo6,
                temperaturaLabo1,temperaturaLabo2,temperaturaLabo3,temperaturaLabo4,temperaturaLabo5,temperaturaLabo6,
                calidadDeAireLabo1,calidadDeAireLabo2,calidadDeAireLabo3,calidadDeAireLabo4,calidadDeAireLabo5,calidadDeAireLabo6,
                puertaLabo1,puertaLabo2,puertaLabo3,puertaLabo4,puertaLabo5,puertaLabo6
        ));


        //Aulas virtuales
        for(int i=1;i<=3;i++){
            Area aulaVirtual=new Area("Aula virtual "+i,i);
            Sensor humedadAulaVirtual=new Sensor(
                    Enum.valueOf(TipoSensor.class,"HUMEDAD"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Porcentaje", aulaVirtual);
            Sensor temperaturaAulaVirtual=new Sensor(
                    Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Celcius", aulaVirtual);
            Sensor nivelDeRuidoAulaVirtual=new Sensor(
                    Enum.valueOf(TipoSensor.class,"NIVEL_DE_RUIDO"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Decibeles", aulaVirtual);
            Sensor calidadDeAireAulaVirtual=new Sensor(
                    Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", aulaVirtual);
            Sensor puertaAulaVirtual=new Sensor(
                    Enum.valueOf(TipoSensor.class,"PUERTA"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", aulaVirtual);
            areaRepository.save(aulaVirtual);
            sensorRepository.saveAll(List.of(humedadAulaVirtual,temperaturaAulaVirtual,nivelDeRuidoAulaVirtual,calidadDeAireAulaVirtual,puertaAulaVirtual));
        }

        //Baños Planta Baja
        for(int i=1;i<=6;i++){
            Area baño=new Area("Baño "+i,0);
            Sensor puertaBaño=new Sensor(
                    Enum.valueOf(TipoSensor.class,"PUERTA"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", baño);
            areaRepository.save(baño);
            sensorRepository.save(puertaBaño);
        }

        //Baños Primera Planta
        for(int i=7;i<=9;i++){
            Area baño=new Area("Baño "+i,1);
            Sensor puertaBaño=new Sensor(
                    Enum.valueOf(TipoSensor.class,"PUERTA"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", baño);
            areaRepository.save(baño);
            sensorRepository.save(puertaBaño);
        }

        //Baños Segunda Planta
        for(int i=10;i<=13;i++){
            Area baño=new Area("Baño "+i,2);
            Sensor puertaBaño=new Sensor(
                    Enum.valueOf(TipoSensor.class,"PUERTA"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", baño);
            areaRepository.save(baño);
            sensorRepository.save(puertaBaño);
        }

        //Baños Tercera Planta
        for(int i=14;i<=16;i++){
            Area baño=new Area("Baño "+i,3);
            Sensor puertaBaño=new Sensor(
                    Enum.valueOf(TipoSensor.class,"PUERTA"),
                    Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", baño);
            areaRepository.save(baño);
            sensorRepository.save(puertaBaño);
        }

        Area cocina=new Area("Cocina",0);
        Sensor puertaCocina=new Sensor(
                Enum.valueOf(TipoSensor.class,"PUERTA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", cocina);

        Area comedorPlantaBaja=new Area("Comedor 1",0);
        Sensor puertaComedorPlanta0=new Sensor(
                Enum.valueOf(TipoSensor.class,"PUERTA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", comedorPlantaBaja);

        Area comedorTerceraPlanta=new Area("Comedor 2",3);
        Sensor puertaComedorPlanta1=new Sensor(
                Enum.valueOf(TipoSensor.class,"PUERTA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", comedorTerceraPlanta);

        Area biblioteca=new Area("Biblioteca",0);
        Sensor humedadBiblioteca=new Sensor(
                Enum.valueOf(TipoSensor.class,"HUMEDAD"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Porcentaje", biblioteca);
        Sensor temperaturaBiblioteca=new Sensor(
                Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Celcius", biblioteca);
        Sensor nivelDeRuidoBiblioteca=new Sensor(
                Enum.valueOf(TipoSensor.class,"NIVEL_DE_RUIDO"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Decibeles", biblioteca);
        Sensor calidadDeAireBiblioteca=new Sensor(
                Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", biblioteca);
        Sensor puertaBiblioteca=new Sensor(
                Enum.valueOf(TipoSensor.class,"PUERTA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", biblioteca);

        Area salonDeActosPlantaBaja=new Area("Salon de actos 1",0);
        Sensor humedadSalonDeActos1=new Sensor(
                Enum.valueOf(TipoSensor.class,"HUMEDAD"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Porcentaje", salonDeActosPlantaBaja);
        Sensor temperaturaSalonDeActos1=new Sensor(
                Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Celcius", salonDeActosPlantaBaja);
        Sensor calidadDeAireSalonDeActos1=new Sensor(
                Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", salonDeActosPlantaBaja);
        Sensor puertaSalonDeActos1=new Sensor(
                Enum.valueOf(TipoSensor.class,"PUERTA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", salonDeActosPlantaBaja);

        Area salonDeActosPrimPlanta=new Area("Salon de actos 2",1);
        Sensor humedadSalonDeActos2=new Sensor(
                Enum.valueOf(TipoSensor.class,"HUMEDAD"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Porcentaje", salonDeActosPrimPlanta);
        Sensor temperaturaSalonDeActos2=new Sensor(
                Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Celcius", salonDeActosPrimPlanta);
        Sensor calidadDeAireSalonDeActos2=new Sensor(
                Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "UCalidad", salonDeActosPrimPlanta);
        Sensor puertaSalonDeActos2=new Sensor(
                Enum.valueOf(TipoSensor.class,"PUERTA"),
                Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", salonDeActosPrimPlanta);

        areaRepository.saveAll(List.of(cocina,comedorPlantaBaja,comedorTerceraPlanta,biblioteca,salonDeActosPlantaBaja,salonDeActosPrimPlanta));
        sensorRepository.saveAll(List.of(puertaCocina,puertaComedorPlanta0,puertaComedorPlanta1,humedadBiblioteca,temperaturaBiblioteca,
                nivelDeRuidoBiblioteca,calidadDeAireBiblioteca,puertaBiblioteca,humedadSalonDeActos1,temperaturaSalonDeActos1,
                calidadDeAireSalonDeActos1,puertaSalonDeActos1,humedadSalonDeActos2,temperaturaSalonDeActos2,
                calidadDeAireSalonDeActos2,puertaSalonDeActos2));

        //Registros de prueba
        LocalDateTime now=LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);

        Registro registroTemp=new Registro(
                timestamp,
                "Celcius",
                40.3,
                temperaturaPatio,
                60.0
        );

        Registro registroPuerta0=new Registro(
                timestamp,
                "Boolean",
                1.0,
                puertaBiblioteca,
                30.0
        );

        Registro registroPuerta1=new Registro(
                timestamp,
                "Boolean",
                0.0,
                puertaBiblioteca,
                30.0
        );

        Registro registroPuerta=new Registro(
                timestamp,
                "Boolean",
                1.0,
                puertaBiblioteca,
                30.0
        );
        registroRepository.save(registroPuerta0);
        registroRepository.save(registroPuerta1);
        registroRepository.save(registroTemp);
        registroRepository.save(registroPuerta);
    }
}
