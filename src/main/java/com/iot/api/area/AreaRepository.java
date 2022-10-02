package com.iot.api.area;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AreaRepository extends JpaRepository<Area,Long> {
    @Query("SELECT a FROM Area a JOIN FETCH a.sensores s WHERE s.tipo='Puerta'")
   List<Area> getAreasPuertas();

}
