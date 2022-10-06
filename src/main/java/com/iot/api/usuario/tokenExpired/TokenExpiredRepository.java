package com.iot.api.usuario.tokenExpired;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface TokenExpiredRepository extends JpaRepository<TokenExpired,Long> {

    @Query(value = "SELECT token from token_expired t WHERE t.token=?1 ",nativeQuery = true)
    String getToken(String token);
}
