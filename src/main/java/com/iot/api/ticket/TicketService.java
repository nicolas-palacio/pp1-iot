package com.iot.api.ticket;

import javax.mail.MessagingException;
import java.util.List;

public interface TicketService {
    List<Ticket> getTodosLosTickets();

    Ticket postTicket(Ticket ticket,String usuarioEmail) throws MessagingException;
}
