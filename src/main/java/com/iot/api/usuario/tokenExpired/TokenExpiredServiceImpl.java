package com.iot.api.usuario.tokenExpired;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenExpiredServiceImpl implements TokenExpiredService{
    private TokenExpiredRepository tokenExpiredRepository;

    @Override
    public TokenExpired saveTokenExpired(String token) {
        TokenExpired tokenExpired=new TokenExpired(token);
        return tokenExpiredRepository.save(tokenExpired);
    }

    @Override
    public String getTokenExpired(String token) {
        return tokenExpiredRepository.getToken(token);
    }
}
