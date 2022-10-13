package com.iot.api.ticket;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements  TicketService{
    private TicketRepository ticketRepository;

    @Override
    public List<Ticket> getTodosLosTickets() {
        return ticketRepository.findAll();
    }
}
