package com.iot.api.Registro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface RegistroRepository extends JpaRepository<Registro,Long> {


}
