package com.iot.api.sensor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iot.api.registro.Registro;
import com.iot.api.area.Area;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="sensores")
public class Sensor {

    @SequenceGenerator(name = "sensor_sequence",sequenceName = "sensor_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sensor_sequence")
    private Long id;

    @NotNull
    private String tipo;
    @NotNull
    private String unidadDeMedida;
    @NotNull
    private String descripcion;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="area_id", nullable=true)
    private Area area;

    @OneToMany(mappedBy = "sensorR",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Registro> registros;

    public Sensor(String tipo,String unidadDeMedida,String descripcion,Area area){
        this.tipo=tipo;
        this.unidadDeMedida=unidadDeMedida;
        this.descripcion=descripcion;
        this.area=area;
    }


}
