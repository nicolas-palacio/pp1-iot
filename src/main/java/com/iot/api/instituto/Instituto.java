package com.iot.api.instituto;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.iot.api.area.Area;
import com.iot.api.sensor.Sensor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="institutos")
@NoArgsConstructor
public class Instituto {
    @SequenceGenerator(name = "instituto_sequence",sequenceName = "instituto_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instituto_sequence")
    private Long id;

    @NotEmpty(message = "El Nombre del instituto no debe estar vacio")
    private String nombre;

    @OneToMany(mappedBy = "instituto",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    //@JsonManagedReference
    private List<Area> areas;



}
