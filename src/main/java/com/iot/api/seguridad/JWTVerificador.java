package com.iot.api.seguridad;

import com.iot.api.seguridad.excepciones.BadRequestException;
import com.iot.api.seguridad.excepciones.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JWTVerificador {
    @Autowired
    private JWTUtil jwtUtil;

    public String validarToken(String authHeader) {
        String jwt=null;
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            jwt = authHeader.substring(7);
            String email = jwtUtil.validateTokenAndRetrieveSubject(jwt);

            if(email.equals("") || email.isEmpty()){
                throw new NotFoundException("Usuario no encontrado");
            }
        }else {
            throw new BadRequestException("Header");
        }
        return jwt;
    }

    public String getUsuarioEmail(String authHeader){
        String jwt=null;
        String email=null;
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            jwt = authHeader.substring(7);
            email = jwtUtil.validateTokenAndRetrieveSubject(jwt);

            if(email.equals("") || email.isEmpty()){
                throw new NotFoundException("Usuario no encontrado");
            }
        }else {
            throw new BadRequestException("Header");
        }

        System.out.println("EMAILLLL "+email);
        return email;
    }

}
