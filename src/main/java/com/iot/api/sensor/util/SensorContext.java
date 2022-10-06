package com.iot.api.sensor.util;

import com.iot.api.sensor.Sensor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SensorContext {
    @NotEmpty(message = "El Tipo no debe estar vacio")
    private String tipo;

    @NotEmpty(message = "El Estado no debe estar vacio")
    private String estado;

    @NotEmpty(message = "La Unidad de medida no debe estar vacia")
    private String unidadDeMedida;

    @NotEmpty(message = "La descripcion no debe estar vacia")
    private String descripcion;

    @NotNull(message = "El ID del area no debe estar vacio")
    private Long areaId;
}
