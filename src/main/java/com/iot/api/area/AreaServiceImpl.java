package com.iot.api.area;

import com.iot.api.registro.Registro;
import com.iot.api.registro.RegistroService;
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

    private RegistroService registroService;

    @Override
    public Map<String, Object> getAreas() throws CloneNotSupportedException {
        Map<String, Object> map = new HashMap<String, Object>();
        //List<Area> areas=null;
        List<Integer> pisos=areaRepository.pisos();

        for(int i=0;i<pisos.size();i++){
            List<Area> areas=new ArrayList<>(areaRepository.getAreaPorPiso(pisos.get(i)));
            //List<Area> areasConUltimoRegistro=filtrarArea(areas);
            map.put("piso "+pisos.get(i),areas);
        }

        return map;
    }

    private List<Area> filtrarArea(List<Area>areas) throws CloneNotSupportedException {
        List<Area> areasReturn=new ArrayList<Area>();

        for(int i=0;i<areas.size();i++){
            Area nuevaArea= (Area) areas.get(i).clone();

            List<Sensor> sensores=new ArrayList<>(getUltimoRegistro(nuevaArea.getSensores()));
            //nuevaArea.getSensores().removeAll(nuevaArea.getSensores());
            nuevaArea.getSensores().addAll(sensores);


            areasReturn.add(nuevaArea);
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

    @Override
    public Map<String, Object> getNombreAreas() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Integer> pisos=areaRepository.pisos();

        for(int i=0;i<pisos.size();i++){
            List<Object> areas=new ArrayList<>(areaRepository.getNombreAreas(pisos.get(i)));
            map.put("piso "+pisos.get(i),areas);
        }



        return map;
    }

    @Override
    public Map<String, Object> getNombreAreasPorPiso(int piso) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Integer> pisos=areaRepository.pisos();

        List<Object> areas=new ArrayList<>(areaRepository.getNombreAreas(piso));
        map.put("piso "+pisos.get(piso),areas);



        return map;
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
