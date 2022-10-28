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

            try{

                if(areaRepository.findById(1L).isEmpty()){
                    altasDeAreas();
                    altasSensores();

                    String encodedPassword= bCryptPasswordEncoder.encode("secure190");
                    usuarioService.saveUser(new Usuario("Admin","adminfake@gmail.com",encodedPassword,
                            Enum.valueOf(UsuarioRol.class,"ADMIN")));
                    usuarioService.saveUser(new Usuario("Director","directorfake@gmail.com",encodedPassword,
                            Enum.valueOf(UsuarioRol.class,"ADMIN")));
                }

            }catch (Error e){

            }

        };

    }

    private void altasDeAreas() {
        //Aulas Primera Planta
        for (int i = 1; i <= 10; i++) {
            Area aula = new Area("Aula " + i, 1);
            areaRepository.save(aula);
        }

        //Aulas Segunda Planta
        for (int i = 1; i <= 10; i++) {
            Area aula = new Area("Aula " + (i + 10), 2);
            areaRepository.save(aula);
        }

        //Aulas Tercera Planta
        for (int i = 1; i <= 6; i++) {
            Area aula = new Area("Aula " + (i + 20), 3);
            areaRepository.save(aula);
        }

        //Oficinas Planta Baja
        for (int i = 1; i <= 6; i++) {
            Area oficina = new Area("Oficina " + i, 0);
            areaRepository.save(oficina);
         }

        //Oficinas Primera Planta
        for (int i = 1; i <= 2; i++) {
            Area oficina = new Area("Oficina " + (i + 6), 1);
            areaRepository.save(oficina);
        }

        //Oficinas Segunda Planta
        for (int i = 1; i <= 3; i++) {
            Area oficina = new Area("Oficina " + (i + 8), 1);
            areaRepository.save(oficina);
        }

        //Oficinas Tercera Planta
        Area oficina = new Area("Oficina 12", 3);
        areaRepository.save(oficina);

        //Patios Planta Baja
        for (int i = 1; i <= 2; i++) {
            Area patio = new Area("Patio " + i, 0);
            areaRepository.save(patio);
        }

        //Patio Primera Planta
        Area patio = new Area("Patio 3", 1);
        areaRepository.save(patio);

        //Patio Tercera Planta
        Area patioTerceraPlanta = new Area("Patio 4", 3);
        areaRepository.save(patioTerceraPlanta);

        Area gymPlantaBaja = new Area("Gym 1", 0);
        Area gymTerceraPlanta = new Area("Gym 2", 3);

        areaRepository.saveAll(List.of(gymPlantaBaja, gymTerceraPlanta));

        //Pasillos
        for (int i = 0; i <= 3; i++) {
            for (int k = 1; k <= 3; k++) {
                Area pasillo = new Area("Pasillo " + k, i);
                areaRepository.save(pasillo);
            }
        }
        Area laboFisicaPrimeraPlanta = new Area("Laboratorio Fisica 1", 1);
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

    private void altasSensores() {
        List<Area> areas=areaRepository.findAll();

        for(Area area:areas){
            if((area.getNombre().contains("Aula") || area.getNombre().contains("Oficina") || area.getNombre().contains("Biblioteca")) && !area.getNombre().contains("virtual")){
                Sensor humedad = new Sensor(
                        Enum.valueOf(TipoSensor.class, "HUMEDAD"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Porcentaje", area);
                Sensor humo = new Sensor(
                        Enum.valueOf(TipoSensor.class, "HUMO"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Boolean", area);
                Sensor temperatura = new Sensor(
                        Enum.valueOf(TipoSensor.class, "TEMPERATURA"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Celcius", area);
                Sensor nivelDeRuido = new Sensor(
                        Enum.valueOf(TipoSensor.class, "NIVEL_DE_RUIDO"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Decibeles", area);
                Sensor calidadDeAire = new Sensor(
                        Enum.valueOf(TipoSensor.class, "CALIDAD_DE_AIRE"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "ppm", area);
                Sensor puerta = new Sensor(
                        Enum.valueOf(TipoSensor.class, "PUERTA"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Boolean", area);
                Sensor ventana = new Sensor(
                        Enum.valueOf(TipoSensor.class, "VENTANA"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Boolean", area);

                sensorRepository.saveAll(List.of(humedad, temperatura, nivelDeRuido, calidadDeAire, puerta, humo,ventana));
                if(area.getNombre().equals("Biblioteca")){
                    //Registros de prueba
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
                    Registro registroHumedad=new Registro(
                            timestamp,
                            "Porcentaje",
                            10.0,
                            humedad,
                            30.0
                    );
                    //registroRepository.saveAll(List.of(registroPuerta0,registroPuerta1,registroTemp,registroPuerta,registroHumedad));
                }

            }else if(area.getNombre().contains("Patio")){
                Sensor temperaturaPatio = new Sensor(
                        Enum.valueOf(TipoSensor.class, "TEMPERATURA"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Celcius", area);
                Sensor calidadDeAirePatio = new Sensor(
                        Enum.valueOf(TipoSensor.class, "CALIDAD_DE_AIRE"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "ppm", area);
                sensorRepository.saveAll(List.of(temperaturaPatio,calidadDeAirePatio));
            }else if(area.getNombre().contains("Gym")){
                Sensor calidadDeAireGym = new Sensor(
                        Enum.valueOf(TipoSensor.class, "CALIDAD_DE_AIRE"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "ppm", area);
                Sensor humoGym = new Sensor(
                        Enum.valueOf(TipoSensor.class, "HUMO"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Boolean", area);
                Sensor puertaGym = new Sensor(
                        Enum.valueOf(TipoSensor.class, "PUERTA"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Boolean", area);
                Sensor ventanaGym = new Sensor(
                        Enum.valueOf(TipoSensor.class, "VENTANA"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Boolean", area);
                sensorRepository.saveAll(List.of(calidadDeAireGym,humoGym,puertaGym,ventanaGym));
            }else if(area.getNombre().contains("Laboratorio")){
                Sensor humedadLabo=new Sensor(
                        Enum.valueOf(TipoSensor.class,"HUMEDAD"),
                        Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Porcentaje", area);
                Sensor humoLabo = new Sensor(
                        Enum.valueOf(TipoSensor.class, "HUMO"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Boolean", area);
                Sensor gasLabo = new Sensor(
                        Enum.valueOf(TipoSensor.class, "GAS"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Boolean", area);
                Sensor temperaturaLabo=new Sensor(
                        Enum.valueOf(TipoSensor.class,"TEMPERATURA"),
                        Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Celcius", area);
                Sensor calidadDeAireLabo=new Sensor(
                        Enum.valueOf(TipoSensor.class,"CALIDAD_DE_AIRE"),
                        Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "ppm", area);
                Sensor puertaLabo=new Sensor(
                        Enum.valueOf(TipoSensor.class,"PUERTA"),
                        Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", area);
                Sensor ventanaLabo=new Sensor(
                        Enum.valueOf(TipoSensor.class,"VENTANA"),
                        Enum.valueOf(EstadoSensor.class,"DISPONIBLE"), "Boolean", area);
                sensorRepository.saveAll(List.of(humedadLabo,humoLabo,gasLabo,temperaturaLabo,calidadDeAireLabo,calidadDeAireLabo,puertaLabo,ventanaLabo));
            }else if(area.getNombre().contains("Aula virtual") || area.getNombre().contains("Salon")){
                Sensor humedad = new Sensor(
                        Enum.valueOf(TipoSensor.class, "HUMEDAD"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Porcentaje", area);
                Sensor humo = new Sensor(
                        Enum.valueOf(TipoSensor.class, "HUMO"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Boolean", area);
                Sensor temperatura = new Sensor(
                        Enum.valueOf(TipoSensor.class, "TEMPERATURA"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Celcius", area);
                Sensor calidadDeAire = new Sensor(
                        Enum.valueOf(TipoSensor.class, "CALIDAD_DE_AIRE"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "ppm", area);
                Sensor puerta = new Sensor(
                        Enum.valueOf(TipoSensor.class, "PUERTA"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Boolean", area);
                Sensor ventana = new Sensor(
                        Enum.valueOf(TipoSensor.class, "VENTANA"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Boolean", area);
                sensorRepository.saveAll(List.of(humedad, temperatura,calidadDeAire, puerta, humo,ventana));
            } else if (area.getNombre().contains("Baño")) {
                Sensor humoBaño = new Sensor(
                        Enum.valueOf(TipoSensor.class, "HUMO"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Boolean", area);
                Sensor puertaBaño = new Sensor(
                        Enum.valueOf(TipoSensor.class, "PUERTA"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Boolean", area);
                Sensor ventanaBaño = new Sensor(
                        Enum.valueOf(TipoSensor.class, "VENTANA"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Boolean", area);
                sensorRepository.saveAll(List.of(humoBaño,puertaBaño,ventanaBaño));
            }else if(area.getNombre().contains("Cocina") || area.getNombre().contains("Comedor") ){
                Sensor humo = new Sensor(
                        Enum.valueOf(TipoSensor.class, "HUMO"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Boolean", area);
                Sensor gas = new Sensor(
                        Enum.valueOf(TipoSensor.class, "GAS"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Boolean", area);
                Sensor puerta = new Sensor(
                        Enum.valueOf(TipoSensor.class, "PUERTA"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Boolean", area);
                Sensor ventana = new Sensor(
                        Enum.valueOf(TipoSensor.class, "VENTANA"),
                        Enum.valueOf(EstadoSensor.class, "DISPONIBLE"), "Boolean", area);
                sensorRepository.saveAll(List.of(humo,gas,puerta,ventana));
            }
        }

    }
}
