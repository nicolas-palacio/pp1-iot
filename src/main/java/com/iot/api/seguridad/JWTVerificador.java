package com.iot.api.seguridad;

import com.iot.api.seguridad.excepciones.BadRequestException;
import com.iot.api.seguridad.excepciones.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public class JWTVerificador {
    @Autowired
    private static JWTUtil jwtUtil;

    public static String validarToken(String authHeader) {
        String jwt=null;
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            jwt = authHeader.substring(7);
            String email = jwtUtil.validateTokenAndRetrieveSubject(jwt);
            System.out.println("EMAIL DE USUARIO "+email);

            if(email.equals("") || email.isEmpty()){
                throw new NotFoundException("Usuario no encontrado");
            }
        }else {
            throw new BadRequestException("Header");
        }
        return jwt;
    }
}
