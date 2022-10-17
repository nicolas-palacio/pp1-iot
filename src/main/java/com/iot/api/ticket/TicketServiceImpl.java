package com.iot.api.ticket;

import com.iot.api.area.AreaRepository;
import com.iot.api.email.EmailSenderService;
import com.iot.api.sensor.SensorRepository;
import com.iot.api.sensor.SensorServiceImpl;
import com.iot.api.sensor.util.SensorContext;
import com.iot.api.sensor.util.TipoSensor;
import com.iot.api.usuario.Usuario;
import com.iot.api.usuario.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements  TicketService{
    private TicketRepository ticketRepository;
    @Autowired
    private EmailSenderService emailSenderService;
    private UsuarioRepository usuarioRepository;

    private SensorServiceImpl sensorService;

    private SensorRepository sensorRepository;
    private AreaRepository areaRepository;

    @Override
    public List<Ticket> getTodosLosTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket postTicket(Ticket ticket,String usuarioEmail) throws MessagingException {
        Usuario usuario=usuarioRepository.findByEmail(usuarioEmail);
        ticketRepository.save(ticket);

        Long areaID= areaRepository.getIDArea(ticket.getNombreArea());
        String unidadDeMedida=sensorRepository.getUnidadDeMedida(ticket.getTipoSensor().toString());
        Long idSensor=null;

        if(ticket.getTipo().toString()=="ALTA_SENSOR"){
            SensorContext sensorContext=new SensorContext(ticket.getTipoSensor().toString(),"SOLICITADO",unidadDeMedida,areaID);
            idSensor= sensorService.postSensorSolicitado(sensorContext).getId();

        }else{
            idSensor= ticket.getIdSensor();
        }

        emailSenderService.enviarEmail(ticket.getTipoSensor().toString(),idSensor.toString(),
                ticket.getNombreArea(),areaID.toString(),ticket.getTipo().toString());

        usuario.getSolicitudes().add(ticket);
        usuarioRepository.save(usuario);

        return ticket;
    }
}
