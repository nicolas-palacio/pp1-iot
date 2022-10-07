package com.iot.api.sensor;


import com.iot.api.seguridad.JWTUtil;
import com.iot.api.seguridad.JWTVerificador;
import com.iot.api.seguridad.excepciones.BadRequestException;
import com.iot.api.seguridad.excepciones.InternalServerErrorException;
import com.iot.api.seguridad.excepciones.NotFoundException;
import com.iot.api.sensor.util.SensorContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(summary = "Inserta un nuevo sensor.",tags = {"Sensores"},security = {@SecurityRequirement(name="BearerJWT")})
    @PostMapping
    public ResponseEntity<Sensor> postSensor(@RequestHeader("Authorization") String authHeader,@Valid @RequestBody SensorContext sensor){
        JWTVerificador.validarToken(authHeader);

        Sensor sensorPost=sensorService.postSensor(sensor);
        return new ResponseEntity<Sensor>(sensorPost, HttpStatus.CREATED);
    }
    @Operation(summary = "Actualiza el estado del sensor a Habilitado,segun el ID indicado.",tags = {"Sensores"},security = {@SecurityRequirement(name="BearerJWT")})
    @PutMapping("/habilitar/{id}")
    public ResponseEntity<Sensor> habilitarSensor(@RequestHeader("Authorization") String authHeader, @PathVariable(name = "id") Long id){
        if(id==null){
            throw new BadRequestException("ID sensor.");
        }else if(sensorService.getSensor(id).isEmpty()){
            throw new NotFoundException("ID sensor.");
        }
        JWTVerificador.validarToken(authHeader);
        Sensor sensor=sensorService.habilitarSensor(id);

        return new ResponseEntity<Sensor>(sensor,HttpStatus.OK);
    }
    @Operation(summary = "Actualiza el estado del sensor a Deshabilitado,segun el ID indicado.",tags = {"Sensores"},security = {@SecurityRequirement(name="BearerJWT")})
    @PutMapping("/deshabilitar/{id}")
    public ResponseEntity<Sensor> deshabilitarSensor(@RequestHeader("Authorization") String authHeader, @PathVariable(name = "id") Long id){
        if(id==null){
            throw new BadRequestException("ID sensor.");
        }else if(sensorService.getSensor(id).isEmpty()){
            throw new NotFoundException("ID sensor.");
        }
        JWTVerificador.validarToken(authHeader);
        Sensor sensor=sensorService.deshabilitarSensor(id);

        return new ResponseEntity<Sensor>(sensor,HttpStatus.OK);
    }

    @Operation(summary = "Elimina el sensor con el ID indicado.",tags = {"Sensores"},security = {@SecurityRequirement(name="BearerJWT")})
    @DeleteMapping("/ids/{id}")
    public ResponseEntity<Sensor> deleteSensor(@RequestHeader("Authorization") String authHeader,@PathVariable(name = "id") Long id){
        if(id==null){
            throw new BadRequestException("ID sensor.");
        }
        JWTVerificador.validarToken(authHeader);

        return new ResponseEntity<Sensor>(sensorService.deleteSensor(id),HttpStatus.OK);
    }

    @Operation(summary = "Elimina los sensores segun el tipo.",tags = {"Sensores"},security = {@SecurityRequirement(name="BearerJWT")})
    @DeleteMapping("/tipos/{id}")
    public ResponseEntity<List<Sensor>> deleteSensorPorTipo(@RequestHeader("Authorization") String authHeader,@PathVariable(name = "tipo")String tipo){
        if(tipo==null){
            throw new BadRequestException("Tipo de sensor.");
        }
        JWTVerificador.validarToken(authHeader);

        return new ResponseEntity<List<Sensor>>(sensorService.deleteSensoresPorTipo(tipo).get(),HttpStatus.OK);
    }


}
