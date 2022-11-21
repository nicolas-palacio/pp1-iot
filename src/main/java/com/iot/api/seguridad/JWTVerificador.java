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
            map.put("TABLA_SUGERENCIAS_RECHAZADAS ",true);
            map.put("TABLA_SUGERENCIAS_ACEPTADAS",true);
            map.put("TABLA_SUGERENCIAS_PENDIENTES",true);
            map.put("TABLA_SUGERENCIAS_CERRADAS",true);
            map.put("TABLA_SOLICITUDES_RECHAZADAS",true);
            map.put("TABLA_SOLICITUDES_ACEPTADAS",true);
            map.put("TABLA_SOLICITUDES_PENDIENTES",true);
            map.put("TABLA_SOLICITUDES_CERRADAS",true);
            map.put("BOTON_ALTA_SUGERENCIA",true);
            map.put("BOTON_RECHAZO_SUGERENCIA",true);
            map.put("BOTON_ACEPTA_SUGERENCIA",true);
            map.put("BOTON_ACEPTA_SOLICITUD_SENSOR",false);
            map.put("BOTON_RECHAZA_SOLICITUD_SENSOR",false);
            map.put("BOTON_ALTA_SENSOR",true);
            map.put("BOTON_MODIFICACION_SENSOR",true);
            map.put("BOTON_BAJA_SENSOR",true);
        }

        if(rol.equals("TECNICO")){
            map.put("TABLA_SUGERENCIAS_RECHAZADAS ",true);
            map.put("TABLA_SUGERENCIAS_ACEPTADAS",true);
            map.put("TABLA_SUGERENCIAS_PENDIENTES",true);
            map.put("TABLA_SUGERENCIAS_CERRADAS",true);
            map.put("TABLA_SOLICITUDES_RECHAZADAS",true);
            map.put("TABLA_SOLICITUDES_ACEPTADAS",true);
            map.put("TABLA_SOLICITUDES_PENDIENTES",true);
            map.put("TABLA_SOLICITUDES_CERRADAS",true);
            map.put("BOTON_ALTA_SUGERENCIA",false);
            map.put("BOTON_RECHAZO_SUGERENCIA",false);
            map.put("BOTON_ACEPTA_SUGERENCIA",false);
            map.put("BOTON_ACEPTA_SOLICITUD_SENSOR",true);
            map.put("BOTON_RECHAZA_SOLICITUD_SENSOR",true);
            map.put("BOTON_ALTA_SENSOR",false);
            map.put("BOTON_MODIFICACION_SENSOR",false);
            map.put("BOTON_BAJA_SENSOR",false);
        }

        if(rol.equals("ALUMNO_ULTIMO_ANIO")){
            map.put("TABLA_SUGERENCIAS_RECHAZADAS ",false);
            map.put("TABLA_SUGERENCIAS_ACEPTADAS",true);
            map.put("TABLA_SUGERENCIAS_PENDIENTES",true);
            map.put("TABLA_SUGERENCIAS_CERRADAS",true);
            map.put("TABLA_SOLICITUDES_RECHAZADAS",true);
            map.put("TABLA_SOLICITUDES_ACEPTADAS",true);
            map.put("TABLA_SOLICITUDES_PENDIENTES",true);
            map.put("TABLA_SOLICITUDES_CERRADAS",true);
            map.put("BOTON_ALTA_SUGERENCIA",true);
            map.put("BOTON_RECHAZO_SUGERENCIA",false);
            map.put("BOTON_ACEPTA_SUGERENCIA",false);
            map.put("BOTON_ACEPTA_SOLICITUD_SENSOR",false);
            map.put("BOTON_RECHAZA_SOLICITUD_SENSOR",false);
            map.put("BOTON_ALTA_SENSOR",false);
            map.put("BOTON_MODIFICACION_SENSOR",false);
            map.put("BOTON_BAJA_SENSOR",false);
        }

        if(rol.equals("ADMIN")){
            map.put("TABLA_SUGERENCIAS_RECHAZADAS ",true);
            map.put("TABLA_SUGERENCIAS_ACEPTADAS",true);
            map.put("TABLA_SUGERENCIAS_PENDIENTES",true);
            map.put("TABLA_SUGERENCIAS_CERRADAS",true);
            map.put("TABLA_SOLICITUDES_RECHAZADAS",true);
            map.put("TABLA_SOLICITUDES_ACEPTADAS",true);
            map.put("TABLA_SOLICITUDES_PENDIENTES",true);
            map.put("TABLA_SOLICITUDES_CERRADAS",true);
            map.put("BOTON_ALTA_SUGERENCIA",true);
            map.put("BOTON_RECHAZO_SUGERENCIA",true);
            map.put("BOTON_ACEPTA_SUGERENCIA",true);
            map.put("BOTON_ACEPTA_SOLICITUD_SENSOR",false);
            map.put("BOTON_RECHAZA_SOLICITUD_SENSOR",false);
            map.put("BOTON_ALTA_SENSOR",true);
            map.put("BOTON_MODIFICACION_SENSOR",true);
            map.put("BOTON_BAJA_SENSOR",true);
        }

        return map;
    }


}
