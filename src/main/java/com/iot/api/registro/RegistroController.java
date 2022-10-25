package com.iot.api.registro;


import com.iot.api.area.Area;
import com.iot.api.registro.util.RegistroContext;
import com.iot.api.seguridad.JWTVerificador;
import com.iot.api.seguridad.excepciones.BadRequestException;
import com.iot.api.seguridad.excepciones.NotFoundException;
import com.iot.api.sensor.util.SensorContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/registros")
@AllArgsConstructor
public class RegistroController {
    @Autowired
    private RegistroService registroService;
    @Autowired
    private JWTVerificador jwtVerificador;

    @Operation(summary = "Devuelve una lista con todos los registros de los sensores.",tags = {"Registros"})
    @GetMapping
    @ResponseBody
    public List<Registro> getRegistros(){
        return registroService.getTodosLosRegistros();
    }

    @Operation(summary = "Devuelve el registro segun el ID indicado.",tags = {"Registros"})
    @GetMapping("/ids/{id}")
    @ResponseBody
    public Optional<Registro> getRegistro(@PathVariable(name = "id")Long id){
        if(id==null){
            throw new BadRequestException("id");
        }
        Optional<Registro> registro=registroService.getRegistro(id);
        if(registro.isEmpty()){
            throw new NotFoundException("id "+id);
        }
        return registro;
    }

    @Operation(summary = "Inserta un nuevo registro.",tags = {"Registros"},security = {@SecurityRequirement(name="BearerJWT")})
    @PostMapping
    public ResponseEntity<Registro> postRegistro(@RequestHeader("Authorization") String authHeader,@Valid @RequestBody RegistroContext registroContext){
        jwtVerificador.validarToken(authHeader);

        Registro registroPost=registroService.postRegistro(registroContext);

        return new ResponseEntity<Registro>(registroPost, HttpStatus.CREATED);
    }

    @Operation(summary = "Elimina el registro con el ID indicado.",tags = {"Registros"},security = {@SecurityRequirement(name="BearerJWT")})
    @DeleteMapping("/ids/{id}")
    public ResponseEntity<Registro> deleteRegistro(@RequestHeader("Authorization") String authHeader, @PathVariable(name = "id")Long id){
        if(id==null){
            throw new BadRequestException("ID area.");
        }
        jwtVerificador.validarToken(authHeader);

        Optional<Registro> registro=registroService.getRegistro(id);
        if(registro.isEmpty()){
            throw new NotFoundException("El ID no existe.");
        }

        registroService.deleteRegistro(id);

        return new ResponseEntity<>(registro.get(),HttpStatus.OK);
    }

    @Operation(summary = "Elimina todos los registros.",tags = {"Registros"},security = {@SecurityRequirement(name="BearerJWT")})
    @DeleteMapping
    public ResponseEntity<Registro> deleteRegistros(@RequestHeader("Authorization") String authHeader){
        jwtVerificador.validarToken(authHeader);

        registroService.deleteRegistros();

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
