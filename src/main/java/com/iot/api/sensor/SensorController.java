package com.iot.api.sensor;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  


}
