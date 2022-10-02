package com.iot.api.registro;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iot.api.sensor.Sensor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;

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
    private double frecuencia; //segundos
    private double valor;

    @JsonIgnore
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="sensor_id", nullable=false)
    private Sensor sensorR;


    public Registro(Timestamp fecha, String unidad,double valor,Sensor sensor,double frecuencia){
        this.fecha=fecha;
        this.unidad=unidad;
        this.valor=valor;
        this.sensorR=sensor;
        this.frecuencia=frecuencia;
    }

}
