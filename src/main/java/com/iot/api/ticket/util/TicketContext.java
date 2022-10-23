package com.iot.api.ticket.util;

import com.iot.api.sensor.util.TipoSensor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketContext {
    @NotNull(message = "El tipo no debe estar vacio.")
    @Enumerated(EnumType.STRING)
    private TipoTicket tipo;

    // @NotNull(message = "El estado no debe estar vacio.")

    @NotNull(message = "El tipo de sensor no debe estar vacio.")
    private TipoSensor tipoSensor;

    @NotNull(message = "El nombre del area no debe estar vacio.")
    private String nombreArea;

    @NotNull(message = "La descripcion no puede estar vacia.")
    private String descripcion;

    private Long idSensor;

}
