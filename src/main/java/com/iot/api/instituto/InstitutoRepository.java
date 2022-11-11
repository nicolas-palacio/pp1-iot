package com.iot.api.instituto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface InstitutoRepository extends JpaRepository<Instituto,Long> {
}
