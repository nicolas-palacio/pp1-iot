package com.iot.api.ticket;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.iot.api.area.Area;
import com.iot.api.sensor.util.EstadoSensor;
import com.iot.api.sensor.util.TipoSensor;
import com.iot.api.ticket.util.EstadoTicket;
import com.iot.api.ticket.util.TipoTicket;
import com.iot.api.usuario.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="tickets")
public class Ticket {
    @SequenceGenerator(name = "ticket_sequence",sequenceName = "ticket_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_sequence")
    private Long id;

    @NotNull(message = "El tipo no debe estar vacio.")
    @Enumerated(EnumType.STRING)
    private TipoTicket tipo;

    @NotNull(message = "El estado no debe estar vacio.")
    @Enumerated(EnumType.STRING)
    private EstadoTicket estado;

    @NotNull(message = "El tipo de sensor no debe estar vacio.")
    private TipoSensor tipoSensor;

    @NotNull(message = "El nombre del area no debe estar vacio.")
    private String nombreArea;

    @NotNull(message = "La descripcion no puede estar vacia.")
    private String descripcion;

    @ManyToMany(mappedBy = "solicitudes")
    List<Usuario> usuarioList;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Timestamp fecha;


    public Ticket(TipoTicket tipo,TipoSensor tipoSensor, String nombreArea, String descripcion, Timestamp fecha) {
        this.tipo = tipo;
        this.estado = Enum.valueOf(EstadoTicket.class,"ABIERTA");
        this.tipoSensor = tipoSensor;
        this.nombreArea = nombreArea;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", estado=" + estado +
                ", tipoSensor=" + tipoSensor +
                ", nombreArea='" + nombreArea + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
