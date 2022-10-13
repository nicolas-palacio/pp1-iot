package com.iot.api.ticket;


import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/tickets")
@AllArgsConstructor
public class TicketController {
    @Autowired
    private TicketServiceImpl ticketService;

    @Operation(summary = "Devuelve una lista con todas las solicitudes.",tags = {"Solicitudes"})
    @GetMapping
    @ResponseBody
    public List<Ticket> getTickets(){
        return ticketService.getTodosLosTickets();
    }


}
