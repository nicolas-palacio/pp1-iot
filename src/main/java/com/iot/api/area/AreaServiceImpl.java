package com.iot.api.area;

import com.iot.api.registro.Registro;
import com.iot.api.sensor.Sensor;
import com.iot.api.sensor.SensorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class AreaServiceImpl implements AreaService{
    private AreaRepository areaRepository;
    private SensorRepository sensorRepository;

    @Override
    public Map<String, Object> getAreas() {
        Map<String, Object> map = new HashMap<String, Object>();
        //List<Area> areas=null;
        List<Integer> pisos=areaRepository.pisos();

        for(int i=0;i<pisos.size();i++){
            List<Area> areas=areaRepository.getAreaPorPiso(pisos.get(i));
            map.put("piso "+pisos.get(i),areas);
        }

        return map;
    }

    private List<Area> filtrarAreaPorPiso(List<Area>areas,Integer piso) {
        List<Area> areasReturn=new ArrayList<Area>();

        for(Area area:areas){
            if(area.getPiso()==piso){
                areasReturn.add(area);
            }
        }

        return areasReturn;
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
