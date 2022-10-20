package com.iot.api.area;


import com.fasterxml.jackson.annotation.*;
import com.iot.api.sensor.Sensor;
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
@Table(name="areas")
@NoArgsConstructor
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")*/
public class Area {
    @SequenceGenerator(name = "area_sequence",sequenceName = "area_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "area_sequence")
    private Long id;
    @NotEmpty(message = "El Nombre del area no debe estar vacio")
    private String nombre;

    @NotNull(message = "El Piso del area no debe estar vacio")
    private Integer piso;

    /*@NotEmpty(message = "La Descripcion del area no debe estar vacia")
    private String descripcion;*/

    //@JsonIgnore
    @OneToMany(mappedBy = "area",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
   // @JsonManagedReference
    private List<Sensor> sensores;

    public Area(String nombre,Integer piso){
        this.nombre=nombre;
        this.piso=piso;
    }

}
