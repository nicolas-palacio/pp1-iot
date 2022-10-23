package com.iot.api.ticket;


import com.iot.api.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    @Modifying
    @Query("UPDATE Ticket t " +
            "SET t.estado = 'DESAPROBADA' WHERE t.id = ?1")
    int desaprobarTicket(Long id);

    @Modifying
    @Query("UPDATE Ticket t " +
            "SET t.estado = 'APROBADA' WHERE t.id = ?1")
    int aprobarTicket(Long id);

    @Query("SELECT t from Ticket t where t.appUsuario.email=?1")
    List<Ticket> getTicketsDeUsuario(String email);

}
