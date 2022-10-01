package com.iot.api.area;


import com.iot.api.seguridad.excepciones.BadRequestException;
import com.iot.api.seguridad.excepciones.NotFoundException;
import com.iot.api.sensor.Sensor;
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

    @GetMapping
    @ResponseBody
    public List<Area> getAreas(){
        return areaService.getAreas();
    }

    @GetMapping("/ids")
    @ResponseBody
    public Optional<Area> getArea(@RequestParam(name = "id")Long id){
        if(id==null){
            throw new BadRequestException("id");
        }
        Optional<Area> area=areaService.getArea(id);
        if(area.isEmpty()){
            throw new NotFoundException("id "+id);
        }

        return area;
    }

    @PostMapping
    public ResponseEntity<Area> postArea(@Valid @RequestBody Area area){
        areaService.postArea(area);

        return new ResponseEntity<Area>(area, HttpStatus.CREATED);
    }

    @DeleteMapping("/ids")
    public ResponseEntity<Area> deleteArea( @RequestParam(name = "id")Long id){
        if(id==null){
            throw new BadRequestException("ID area.");
        }

        Optional<Area> area=areaService.getArea(id);
        if(area.isEmpty()){
            throw new NotFoundException("El ID no existe.");
        }
        areaService.deleteArea(id);

        return new ResponseEntity<>(area.get(),HttpStatus.OK);
    }

}
