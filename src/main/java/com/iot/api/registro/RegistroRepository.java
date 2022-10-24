package com.iot.api.registro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface RegistroRepository extends JpaRepository<Registro,Long> {

    @Query(value = "SELECT * from registros r WHERE r.sensor_id=:idSensor ORDER BY id DESC LIMIT 1;",nativeQuery = true)
    Registro ultimoRegistro(@Param("idSensor") Long idSensor);
}
