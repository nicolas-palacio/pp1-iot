package com.iot.api.area;


import com.iot.api.seguridad.JWTVerificador;
import com.iot.api.seguridad.excepciones.BadRequestException;
import com.iot.api.seguridad.excepciones.NotFoundException;
import com.iot.api.sensor.Sensor;
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
@RequestMapping(path = "api/areas")
@AllArgsConstructor
public class AreaController {
    @Autowired
    private AreaServiceImpl areaService;

    @Operation(summary = "Devuelve una lista con todas las areas de la escuela.",tags = {"Areas"})
    @GetMapping
    @ResponseBody
    public List<Area> getAreas(){
        return areaService.getAreas();
    }

    @Operation(summary = "Devuelve el area, junto con sus sensores, segun el ID indicado.",tags = {"Areas"})
    @GetMapping("/ids/{id}")
    @ResponseBody
    public Optional<Area> getArea(@PathVariable(name = "id")Long id){
        if(id==null){
            throw new BadRequestException("id");
        }
        Optional<Area> area=areaService.getArea(id);
        if(area.isEmpty()){
            throw new NotFoundException("id "+id);
        }

        return area;
    }

    @Operation(summary = "Devuelve una lista de las areas que tengan su puerta abierta.",tags = {"Areas"})
    @GetMapping("/puertas-abiertas")
    @ResponseBody
    public List<Area> getAreaPuertasAbiertas(){

        return areaService.getAreasPuertasAbiertas();
    }

    @Operation(summary = "Inserta una nueva area.",tags = {"Areas"})
    @PostMapping
    public ResponseEntity<Area> postArea(@RequestHeader("Authorization") String authHeader,@Valid @RequestBody Area area){
        JWTVerificador.validarToken(authHeader);

        areaService.postArea(area);

        return new ResponseEntity<Area>(area, HttpStatus.CREATED);
    }

    @Operation(summary = "Elimina el area con el ID indicado.",tags = {"Areas"})
    @DeleteMapping("/ids/{id}")
    public ResponseEntity<Area> deleteArea(@RequestHeader("Authorization") String authHeader, @PathVariable(name = "id")Long id){
        if(id==null){
            throw new BadRequestException("ID area.");
        }
        JWTVerificador.validarToken(authHeader);

        Optional<Area> area=areaService.getArea(id);
        if(area.isEmpty()){
            throw new NotFoundException("El ID no existe.");
        }
        areaService.deleteArea(id);

        return new ResponseEntity<>(area.get(),HttpStatus.OK);
    }

}
