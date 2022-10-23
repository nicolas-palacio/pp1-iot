package com.iot.api.sensor;


import com.iot.api.area.Area;
import com.iot.api.area.AreaRepository;
import com.iot.api.email.EmailSenderService;
import com.iot.api.sensor.util.EstadoSensor;
import com.iot.api.sensor.util.SensorContext;
import com.iot.api.sensor.util.TipoSensor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SensorServiceImpl implements SensorService{

    private SensorRepository sensorRepository;
    private AreaRepository areaRepository;

    @Autowired
    private EmailSenderService emailSenderService;


    @Override
    public List<Sensor> getTodosLosSensores() {
        return sensorRepository.findAll();
    }

    @Override
    public Optional<List<Sensor>> getSensoresPorTipo(String tipo) {
        return sensorRepository.findSensoresPorTipo(TipoSensor.valueOf(tipo.toUpperCase()));
    }

    @Override
    public Optional<Sensor> getSensor(Long id) {
        return sensorRepository.findById(id);
    }

    @Override
    public List<TipoSensor> tiposDeSensores() {
        List<TipoSensor> tipos= new ArrayList<>(EnumSet.allOf(TipoSensor.class));

        return tipos;
    }

    @Override
    public Sensor postSensor(SensorContext sensor) throws MessagingException {
        Optional<Area> area=areaRepository.findById(sensor.getAreaId());
        Sensor sensorPost=new Sensor(Enum.valueOf(TipoSensor.class,sensor.getTipo().toUpperCase()),
                Enum.valueOf(EstadoSensor.class,sensor.getEstado().toUpperCase())
                ,sensor.getUnidadDeMedida(),area.get());
        sensorRepository.save(sensorPost);

       // emailSenderService.enviarEmail(sensor.getTipo().toString(),"Aula 1");

        return sensorPost;
    }

    @Override
    public Sensor postSensorSolicitado(SensorContext sensor) {
        Optional<Area> area=areaRepository.findById(sensor.getAreaId());

        Sensor sensorPost=new Sensor(Enum.valueOf(TipoSensor.class,sensor.getTipo().toString()),
                Enum.valueOf(EstadoSensor.class,sensor.getEstado().toString())
                ,sensor.getUnidadDeMedida(),area.get());
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

        Optional<List<Sensor>> sensores=sensorRepository.findSensoresPorTipo(TipoSensor.valueOf(tipo.toUpperCase()));
        sensorRepository.deleteSensoresPorTipo(TipoSensor.valueOf(tipo.toUpperCase()));
        return sensores;
    }

    @Override
    public Sensor habilitarSensor(Long id) {
        sensorRepository.habilitarSensor(id);

        return sensorRepository.findById(id).get();
    }

    @Override
    public Sensor deshabilitarSensor(Long id) {
        sensorRepository.deshabilitarSensor(id);

        return sensorRepository.findById(id).get();
    }
}
