package com.iot.api.Registro;

import com.iot.api.area.Area;
import com.iot.api.sensor.Sensor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="registros")
@NoArgsConstructor
public class Registro {
    @SequenceGenerator(name = "registro_sequence",sequenceName = "registro_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "registro_sequence")
    private Long id;
    private Timestamp fecha;
    private String unidad;

    @ManyToOne
    @JoinColumn(name="sensor_id", nullable=false)
    private Sensor sensorR;
    private double valor;

    public Registro(Timestamp fecha, String unidad,double valor,Sensor sensor){
        this.fecha=fecha;
        this.unidad=unidad;
        this.valor=valor;
        this.sensorR=sensor;
    }

}
