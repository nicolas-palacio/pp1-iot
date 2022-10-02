package com.iot.api.sensor;


import com.iot.api.seguridad.excepciones.BadRequestException;
import com.iot.api.seguridad.excepciones.InternalServerErrorException;
import com.iot.api.seguridad.excepciones.NotFoundException;
import com.iot.api.sensor.util.SensorContext;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Devuelve una lista con todos los sensores.",tags = {"Sensores"})
    @GetMapping
    @ResponseBody
    public List<Sensor> getSensores(){
        return sensorService.getTodosLosSensores();
    }

    @Operation(summary = "Devuelve el sensor segun el ID indicado.",tags = {"Sensores"})
    @GetMapping("/ids/{id}")
    @ResponseBody
    public Optional<Sensor> getSensor(@PathVariable(name = "id")Long id ){
        if(id==null){
            throw new BadRequestException("id");
        }

        Optional<Sensor> sensor=sensorService.getSensor(id);
        if(sensor.isEmpty()){
            throw new NotFoundException("id "+id);
        }

        return sensor;
    }

    @Operation(summary = "Devuelve una lista con todos los sensores segun el tipo.",tags = {"Sensores"})
    @GetMapping("/tipos/{tipo}")
    @ResponseBody
    public Optional<List<Sensor>> getSensorPorTipo(@PathVariable(name = "tipo")String tipo ){
        if(tipo==null){
            throw new BadRequestException("tipo");
        }

        Optional<List<Sensor>> sensores=sensorService.getSensoresPorTipo(tipo);

        return sensores;
    }

    @Operation(summary = "Inserta un nuevo sensor.",tags = {"Sensores"})
    @PostMapping
    public ResponseEntity<Sensor> postSensor(@Valid @RequestBody SensorContext sensor){
        Sensor sensorPost=sensorService.postSensor(sensor);
        return new ResponseEntity<Sensor>(sensorPost, HttpStatus.CREATED);
    }
    @Operation(summary = "Elimina el sensor con el ID indicado.",tags = {"Sensores"})
    @DeleteMapping("/ids/{id}")
    public ResponseEntity<Sensor> deleteSensor(@PathVariable(name = "id") Long id){
        if(id==null){
            throw new BadRequestException("ID sensor.");
        }

        return new ResponseEntity<Sensor>(sensorService.deleteSensor(id),HttpStatus.OK);
    }

    @Operation(summary = "Elimina los sensores segun el tipo.",tags = {"Sensores"})
    @DeleteMapping("/tipos/{id}")
    public ResponseEntity<List<Sensor>> deleteSensorPorTipo(@PathVariable(name = "tipo")String tipo){
        if(tipo==null){
            throw new BadRequestException("Tipo de sensor.");
        }

        return new ResponseEntity<List<Sensor>>(sensorService.deleteSensoresPorTipo(tipo).get(),HttpStatus.OK);
    }

}
