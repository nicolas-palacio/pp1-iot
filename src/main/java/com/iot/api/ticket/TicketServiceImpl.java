package com.iot.api.ticket;

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
    @Override
    public List<Ticket> getTodosLosTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket postTicket(Ticket ticket,String usuarioEmail) {
        Usuario usuario=usuarioRepository.findByEmail(usuarioEmail);
        ticketRepository.save(ticket);

        usuario.getSolicitudes().add(ticket);


        usuarioRepository.save(usuario);


        System.out.println("TICKET "+ticket.toString());
        return ticket;
    }
}
