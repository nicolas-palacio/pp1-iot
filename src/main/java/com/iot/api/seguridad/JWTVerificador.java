package com.iot.api.seguridad;

import com.iot.api.seguridad.excepciones.BadRequestException;
import com.iot.api.seguridad.excepciones.NotFoundException;
import com.iot.api.usuario.UsuarioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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

        return email;
    }

    public Map<String, Object> getUserAuth(String rol){
        Map<String, Object> map = new HashMap<String, Object>();

        if(rol.equals("DIRECTIVO")){
            map.put("Alta sensor",true);
            map.put("Modif. sensor",true);
            map.put("Baja sensor",true);
            map.put("Ver tabla solicitudes",true);
            map.put("Alta sugerencia",true);
            map.put("Baja sugerencia",false);
        }

        if(rol.equals("TECNICO")){
            map.put("Alta sensor",true);
            map.put("Modif. sensor",true);
            map.put("Baja sensor",true);
            map.put("Ver tabla solicitudes",true);
            map.put("Alta sugerencia",true);
            map.put("Baja sugerencia",true);
        }

        if(rol.equals("ALUMNO_ULTIMO_ANIO")){
            map.put("Alta sensor",false);
            map.put("Modif. sensor",false);
            map.put("Baja sensor",false);
            map.put("Ver tabla solicitudes",true);
            map.put("Alta sugerencia",true);
            map.put("Baja sugerencia",false);
        }

        if(rol.equals("ADMIN")){
            map.put("Alta sensor",true);
            map.put("Modif. sensor",true);
            map.put("Baja sensor",true);
            map.put("Ver tabla solicitudes",true);
            map.put("Alta sugerencia",true);
            map.put("Baja sugerencia",true);
        }

        return map;
    }


}
