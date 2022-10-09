package com.iot.api.area;

import com.iot.api.registro.Registro;
import com.iot.api.sensor.Sensor;
import com.iot.api.sensor.SensorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AreaServiceImpl implements AreaService{
    private AreaRepository areaRepository;
    private SensorRepository sensorRepository;

    @Override
    public List<Area> getAreas() {
        return areaRepository.findAll();
    }

    @Override
    public Optional<Area> getArea(Long id) {
        return areaRepository.findById(id);
    }

    @Override
    public void postArea(Area area) {
        areaRepository.save(area);
    }

    @Override
    public void deleteArea(Long id) {
        areaRepository.deleteById(id);
    }

    @Override
    public List<Area> getAreasPuertasAbiertas() {
        List<Area> areas=areaRepository.getAreasPuertas();
        List<Sensor> sensores=getUltimoRegistro(sensorRepository.findSensoresPuertaAbierta());
        List<Area> areasReturn=new ArrayList<>();

        for(int i=0;i<areas.size();i++){
            Area area=areas.get(i);
            for(int k=0;k<area.getSensores().size();k++){
                Sensor sensor=area.getSensores().get(k);
                if(!sensores.contains(sensor)){
                    area.getSensores().remove(sensor);
                }else{
                    areasReturn.add(area);
                }
            }
        }

        return areasReturn;
    }

    private List<Sensor> getUltimoRegistro(List<Sensor>sensores){
        List<Sensor> sensoresConUltimoRegistro=sensores;

        for(int i=0;i<=sensoresConUltimoRegistro.size()-1;i++){
            if(sensoresConUltimoRegistro.get(i).getRegistros().size()>1){
                Registro ultimoRegistro=sensoresConUltimoRegistro.get(i).getRegistros().get(sensoresConUltimoRegistro.get(i).getRegistros().size()-1);
                sensoresConUltimoRegistro.get(i).getRegistros().clear();
                sensoresConUltimoRegistro.get(i).setRegistros(List.of(ultimoRegistro));
            }
        }

        return sensoresConUltimoRegistro;
    }
}
