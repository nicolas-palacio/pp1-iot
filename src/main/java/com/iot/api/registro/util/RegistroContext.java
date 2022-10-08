package com.iot.api.registro.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistroContext {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @NotNull(message = "La Fecha no debe estar vacia")
    private Timestamp fecha;

    @NotEmpty(message = "La Unidad no debe estar vacia")
    private String unidad;

    @NotNull(message = "La frecuencia de registor no debe estar vacia")
    private double frecuencia; //segundos

    @NotNull(message = "El valor del registro no debe estar vacio")
    private double valor;

    @NotNull(message = "El ID del sensor no debe estar vacio")
    private Long sensorID;
}
