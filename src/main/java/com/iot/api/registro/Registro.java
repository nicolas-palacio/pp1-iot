package com.iot.api.registro;

import com.fasterxml.jackson.annotation.*;
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
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")*/
@NoArgsConstructor
public class Registro {
    @SequenceGenerator(name = "registro_sequence",sequenceName = "registro_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "registro_sequence")
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Timestamp fecha;
    private String unidad;
    private double valor;
    private double frecuencia; //segundos


    @JsonIgnore
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="sensor_id", nullable=false)
   // @JsonBackReference
    private Sensor sensor;


    public Registro(Timestamp fecha, String unidad,double valor,Sensor sensor,double frecuencia){
        this.fecha=fecha;
        this.unidad=unidad;
        this.valor=valor;
        this.sensor=sensor;
        this.frecuencia=frecuencia;
    }

}
