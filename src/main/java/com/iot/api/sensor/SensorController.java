package com.iot.api.sensor;


import com.iot.api.seguridad.excepciones.BadRequestException;
import com.iot.api.seguridad.excepciones.InternalServerErrorException;
import com.iot.api.seguridad.excepciones.NotFoundException;
import com.iot.api.sensor.util.SensorContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/sensores")
@AllArgsConstructor
public class SensorController {
    @Autowired
    private SensorServiceImpl sensorService;

    @GetMapping
    @ResponseBody
    public List<Sensor> getSensores(){
        return sensorService.getTodosLosSensores();
    }

    @GetMapping("/ids")
    @ResponseBody
    public Optional<Sensor> getSensor(@RequestParam(name = "id")Long id ){
        if(id.equals(null)){
            throw new BadRequestException("id");
        }

        Optional<Sensor> sensor=sensorService.getSensor(id);
        if(sensor.isEmpty()){
            throw new NotFoundException("id "+id);
        }

        return sensor;
    }

    @GetMapping("/tipos")
    @ResponseBody
    public Optional<List<Sensor>> getSensorPorTipo(@RequestParam(name = "tipo")String tipo ){
        if(tipo==null){
            throw new BadRequestException("tipo");
        }

        Optional<List<Sensor>> sensores=sensorService.getSensoresPorTipo(tipo);

        return sensores;
    }

    @PostMapping
    public ResponseEntity<Sensor> postSensor(@Valid @RequestBody SensorContext sensor){
        Sensor sensorPost=sensorService.postSensor(sensor);
        return new ResponseEntity<Sensor>(sensorPost, HttpStatus.CREATED);
    }

    @DeleteMapping("/ids")
    public ResponseEntity<Sensor> deleteSensor(@RequestParam(name = "id")Long id){
        if(id==null){
            throw new BadRequestException("ID sensor.");
        }

        return new ResponseEntity<Sensor>(sensorService.deleteSensor(id),HttpStatus.OK);
    }

    @DeleteMapping("/tipos")
    public ResponseEntity<List<Sensor>> deleteSensorPorTipo(@RequestParam(name = "tipo")String tipo){
        if(tipo==null){
            throw new BadRequestException("Tipo de sensor.");
        }

        return new ResponseEntity<List<Sensor>>(sensorService.deleteSensoresPorTipo(tipo).get(),HttpStatus.OK);
    }

}
