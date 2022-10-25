package com.iot.api.registro;

import com.iot.api.registro.util.RegistroContext;
import com.iot.api.sensor.Sensor;
import com.iot.api.sensor.SensorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegistroServiceImpl implements RegistroService{

    @Autowired
    private RegistroRepository registroRepository;
    @Autowired
    private SensorRepository sensorRepository;

    @Override
    public List<Registro> getTodosLosRegistros() {
        return registroRepository.findAll();
    }

    @Override
    public Optional<Registro> getRegistro(Long id) {
        return registroRepository.findById(id);
    }

    @Override
    public Registro postRegistro(RegistroContext registroContext) {
        Optional<Sensor> sensor=sensorRepository.findById(registroContext.getSensorID());
        Registro registro=new Registro(registroContext.getFecha(),registroContext.getUnidad(),
                registroContext.getValor(),sensor.get(),registroContext.getFrecuencia());

        registroRepository.save(registro);

        return registro;
    }

    @Override
    public void deleteRegistro(Long id) {
        registroRepository.deleteById(id);
    }

    @Override
    public void deleteTodosLosRegistros() {
        registroRepository.deleteAll();
    }

    @Override
    public Registro getUltimoRegistro(Long idSensor) {
        return registroRepository.ultimoRegistro(idSensor);
    }

    @Override
    public void deleteRegistros() {
        registroRepository.deleteRegistros();
    }
}
