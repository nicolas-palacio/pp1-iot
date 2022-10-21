package com.iot.api.ticket;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    @Modifying
    @Query("UPDATE Ticket t " +
            "SET t.estado = 'CERRADA' WHERE t.id = ?1")
    int cerrarTicket(Long id);


}
