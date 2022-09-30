package com.iot.api.area;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AreaRepository extends JpaRepository<Area,Long> {
}
