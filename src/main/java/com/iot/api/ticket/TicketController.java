package com.iot.api.ticket;



import com.iot.api.seguridad.JWTVerificador;
import com.iot.api.seguridad.excepciones.BadRequestException;
import com.iot.api.sensor.util.SensorContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/tickets")
@AllArgsConstructor
public class TicketController {
    @Autowired
    private TicketServiceImpl ticketService;
    @Autowired
    private JWTVerificador jwtVerificador;

    @Operation(summary = "Devuelve una lista con todas las solicitudes.",tags = {"Solicitudes"},security = {@SecurityRequirement(name="BearerJWT")})
    @GetMapping
    @ResponseBody
    public List<Ticket> getTickets(){
        return ticketService.getTodosLosTickets();
    }

    @Operation(summary = "Inserta un nuevo ticket.",tags = {"Solicitudes"},security = {@SecurityRequirement(name="BearerJWT")})
    @PostMapping
    public ResponseEntity<Ticket> postSensor(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody Ticket ticket) throws MessagingException {
        jwtVerificador.validarToken(authHeader);
        if(ticket.getIdSensor()==null && !ticket.getTipo().toString().equals("ALTA_SENSOR")){
            throw new BadRequestException("El id del sensor es necesario en este tipo de ticket.");
        }

        String usuarioEmail= jwtVerificador.getUsuarioEmail(authHeader);

        Ticket ticketPost=ticketService.postTicket(ticket,usuarioEmail);

        return new ResponseEntity<Ticket>(ticketPost, HttpStatus.CREATED);
    }

}
