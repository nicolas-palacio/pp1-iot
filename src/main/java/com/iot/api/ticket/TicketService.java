package com.iot.api.ticket;

import com.iot.api.usuario.Usuario;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<Ticket> getTodosLosTickets();

    Optional<Ticket> getTicket(Long id);

    Ticket postTicket(Ticket ticket,String usuarioEmail) throws MessagingException;

    Ticket cerrarTicket(Long id);

    Ticket deleteTicket(Long id);

    List<Ticket> getUsuario(Usuario usuario);
}
