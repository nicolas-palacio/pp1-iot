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
    @Query("SELECT a FROM Area a JOIN FETCH a.sensores s WHERE s.tipo='PUERTA'")
   List<Area> getAreasPuertas();

    @Query("SELECT a FROM Area a  WHERE a.nombre=?1")
    Optional<Area> getAreaPorNombre(String nombre);

    @Query("SELECT a.id FROM Area a  WHERE a.nombre=?1")
    Long getIDArea(String nombre);

    @Query(value = "select piso from areas group by piso order by piso asc;",nativeQuery = true)
    List<Integer> pisos();

    @Query("SELECT a FROM Area a WHERE a.piso=?1")
    List<Area> getAreaPorPiso(Integer piso);

    @Query("SELECT a.id,a.nombre FROM Area a WHERE a.piso=?1")
    List<Object> getNombreAreas(Integer piso);

}
