package com.iot.api.ticket;



import com.iot.api.seguridad.JWTVerificador;
import com.iot.api.seguridad.excepciones.BadRequestException;
import com.iot.api.seguridad.excepciones.NotFoundException;
import com.iot.api.sensor.util.SensorContext;
import com.iot.api.ticket.util.TicketContext;
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
    public ResponseEntity<Ticket> postSensor(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody TicketContext ticketContext) throws MessagingException {
        jwtVerificador.validarToken(authHeader);
        if(ticketContext.getIdSensor()==null && !ticketContext.getTipo().toString().equals("ALTA_SENSOR")){
            throw new BadRequestException("El id del sensor es necesario en este tipo de ticket.");
        }

        String usuarioEmail= jwtVerificador.getUsuarioEmail(authHeader);

        Ticket ticketPost=new Ticket(ticketContext.getTipo(),ticketContext.getTipoSensor(),ticketContext.getNombreArea(),ticketContext.getDescripcion());
        ticketService.postTicket(ticketPost,usuarioEmail);

        return new ResponseEntity<Ticket>(ticketPost, HttpStatus.CREATED);
    }

    @Operation(summary = "Cierra la solicitud,segun el ID indicado.",tags = {"Solicitudes"},security = {@SecurityRequirement(name="BearerJWT")})
    @PutMapping("/cerrar/{id}")
    public ResponseEntity<Ticket> cerrarTicket(@RequestHeader("Authorization") String authHeader, @PathVariable(name = "id") Long id){
        if(id==null){
            throw new BadRequestException("ID solicitud.");
        }else if(ticketService.getTicket(id).isEmpty()){
            throw new NotFoundException("ID solicitud.");
        }

        jwtVerificador.validarToken(authHeader);

        Ticket ticket=ticketService.cerrarTicket(id);

        return new ResponseEntity<Ticket>(ticket,HttpStatus.OK);

    }


}
