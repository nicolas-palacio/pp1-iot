package com.iot.api.area;


import com.iot.api.sensor.Sensor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="areas")
@NoArgsConstructor
public class Area {
    @SequenceGenerator(name = "area_sequence",sequenceName = "area_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "area_sequence")
    private Long id;
    private String nombre;
    private Integer piso;
    private String descripcion;

    @OneToMany(mappedBy = "area",cascade = CascadeType.ALL)
    private List<Sensor> sensores;

    public Area(String nombre,Integer piso,String descripcion){
        this.nombre=nombre;
        this.piso=piso;
        this.descripcion=descripcion;
    }

}
