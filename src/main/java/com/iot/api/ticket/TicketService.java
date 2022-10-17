package com.iot.api.ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> getTodosLosTickets();

    Ticket postTicket(Ticket ticket,String usuarioEmail);
}
