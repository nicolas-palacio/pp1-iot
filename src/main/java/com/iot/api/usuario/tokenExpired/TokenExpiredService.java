package com.iot.api.usuario.tokenExpired;




public interface TokenExpiredService {
    TokenExpired saveTokenExpired(String token);
    String getTokenExpired(String token);
}
