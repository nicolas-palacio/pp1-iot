package com.iot.api.ticket;

import com.iot.api.area.AreaRepository;
import com.iot.api.sensor.SensorRepository;
import com.iot.api.sensor.SensorServiceImpl;
import com.iot.api.sensor.util.SensorContext;
import com.iot.api.sensor.util.TipoSensor;
import com.iot.api.usuario.Usuario;
import com.iot.api.usuario.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements  TicketService{
    private TicketRepository ticketRepository;
    private UsuarioRepository usuarioRepository;

    private SensorServiceImpl sensorService;

    private SensorRepository sensorRepository;
    private AreaRepository areaRepository;

    @Override
    public List<Ticket> getTodosLosTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket postTicket(Ticket ticket,String usuarioEmail) {
        Usuario usuario=usuarioRepository.findByEmail(usuarioEmail);
        ticketRepository.save(ticket);

        String unidadDeMedida=sensorRepository.getUnidadDeMedida(ticket.getTipoSensor().toString());
        Long areaID= areaRepository.getIDArea(ticket.getNombreArea());


        SensorContext sensorContext=new SensorContext(ticket.getTipoSensor().toString(),"SOLICITADO",unidadDeMedida,areaID);
        sensorService.postSensorSolicitado(sensorContext);


        usuario.getSolicitudes().add(ticket);
        usuarioRepository.save(usuario);

        return ticket;
    }
}
