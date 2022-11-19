package com.iot.api.ticket;



import com.iot.api.seguridad.JWTVerificador;
import com.iot.api.seguridad.excepciones.BadRequestException;
import com.iot.api.seguridad.excepciones.NotFoundException;
import com.iot.api.sensor.util.SensorContext;
import com.iot.api.sensor.util.TipoSensor;
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
    @GetMapping("/aprobados")
    @ResponseBody
    public List<Ticket> getTickets(){
        return ticketService.getTicketsAprobados();
    }

    @Operation(summary = "Devuelve una lista con todas las solicitudes aprobadas.",tags = {"Solicitudes"},security = {@SecurityRequirement(name="BearerJWT")})
    @GetMapping
    @ResponseBody
    public List<Ticket> getTicketsAprobados(){
        return ticketService.getTodosLosTickets();
    }

    @Operation(summary = "Inserta un nuevo ticket.",tags = {"Solicitudes"},security = {@SecurityRequirement(name="BearerJWT")})
    @PostMapping
    public ResponseEntity<Ticket> postSensor(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody TicketContext ticketContext) throws MessagingException {
        jwtVerificador.validarToken(authHeader);
        Ticket ticketPost=null;


        if(ticketContext.getIdSensor()==null && (ticketContext.getTipo().toString().equals("BAJA_SENSOR") || ticketContext.getTipo().toString().equals("MODIFICAR_SENSOR") ) ){
                    //( ticketContext.getTipo().toString().equals("MODIFICAR_SENSOR"))){
                throw new BadRequestException("El id del sensor es necesario en este tipo de ticket.");
        }



        String usuarioEmail= jwtVerificador.getUsuarioEmail(authHeader);

        if(ticketContext.getTipo().toString().equals("SUGERENCIA")){
             ticketPost=new Ticket(ticketContext.getTipo(),Enum.valueOf(TipoSensor.class, ticketContext.getTipoSensor().toString()),ticketContext.getNombreArea(),ticketContext.getDescripcion(),ticketContext.getIdSensor(),ticketContext.getURLs());
        }else{
            ticketPost=new Ticket(ticketContext.getTipo(),ticketContext.getTipoSensor(),ticketContext.getNombreArea(),ticketContext.getDescripcion(),ticketContext.getIdSensor(),ticketContext.getURLs());
        }

        ticketService.postTicket(ticketPost,usuarioEmail);


        return new ResponseEntity<Ticket>(ticketPost, HttpStatus.CREATED);
    }

    @Operation(summary = "Aprueba la solicitud,segun el ID indicado.",tags = {"Solicitudes"},security = {@SecurityRequirement(name="BearerJWT")})
    @PutMapping("/aprobar/{id}")
    public ResponseEntity<Ticket> aprobarTicket(@RequestHeader("Authorization") String authHeader, @PathVariable(name = "id") Long id){
        if(id==null){
            throw new BadRequestException("ID solicitud.");
        }else if(ticketService.getTicket(id).isEmpty()){
            throw new NotFoundException("ID solicitud.");
        }

        jwtVerificador.validarToken(authHeader);

        Ticket ticket=ticketService.aprobarTicket(id);

        return new ResponseEntity<Ticket>(ticket,HttpStatus.OK);

    }

    @Operation(summary = "Desaprueba la solicitud,segun el ID indicado.",tags = {"Solicitudes"},security = {@SecurityRequirement(name="BearerJWT")})
    @PutMapping("/desaprobar/{id}")
    public ResponseEntity<Ticket> cerrarTicket(@RequestHeader("Authorization") String authHeader, @PathVariable(name = "id") Long id){
        if(id==null){
            throw new BadRequestException("ID solicitud.");
        }else if(ticketService.getTicket(id).isEmpty()){
            throw new NotFoundException("ID solicitud.");
        }

        jwtVerificador.validarToken(authHeader);

        Ticket ticket=ticketService.desaprobarTicket(id);

        return new ResponseEntity<Ticket>(ticket,HttpStatus.OK);

    }


}
