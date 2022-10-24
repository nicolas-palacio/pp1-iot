package com.iot.api.usuario;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.api.seguridad.JWTUtil;
import com.iot.api.ticket.Ticket;
import com.iot.api.ticket.TicketServiceImpl;
import com.iot.api.usuario.tokenExpired.TokenExpiredServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioResource {
    private final UsuarioServiceImpl appUserServiceImpl;
    private final TokenExpiredServiceImpl tokenExpiredService;

    private final TicketServiceImpl ticketService;
    @Autowired
    private JWTUtil jwtUtil;

    /*@GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUsers(){
        return ResponseEntity.ok(appUserServiceImpl.getUsers());

    }*/
    @Operation(summary = "Devuelve la informacion del usuario.",tags = {"Usuarios"},security = {@SecurityRequirement(name="BearerJWT")})
    @GetMapping("/user/info")
    public Object getDataUsuario(@RequestHeader("Authorization") String authHeader){
        Usuario usuario=null;
        String jwt=null;
        UsuarioInfo userInfo=null;

        if(authHeader!=null && authHeader.startsWith("Bearer")){
            jwt=authHeader.substring(7);

            if(tokenExpiredService.getTokenExpired(jwt)!=null && tokenExpiredService.getTokenExpired(jwt).equals(jwt)){
                return "User blocked";
            }

            String email=jwtUtil.validateTokenAndRetrieveSubject(jwt);

            usuario=appUserServiceImpl.getUser(email);
            userInfo=new UsuarioInfo(usuario.getNombre(),email);

        }
        return userInfo;
    }

    @Operation(summary = "Devuelve el token del usuario.",tags = {"Usuarios"},security = {@SecurityRequirement(name="BearerJWT")})
    @GetMapping("/token")
    public Object getToken(@RequestHeader("Email") String emailUsuario,@RequestHeader("Usuario") String nombre,
                           @RequestHeader("Rol") String rol){

        Usuario usuario= appUserServiceImpl.getUser(emailUsuario);

        if(usuario==null){
               appUserServiceImpl.guardarUsuario(emailUsuario,rol,nombre);
        }



           /* String email=jwtUtil.validateTokenAndRetrieveSubject(jwt);

            usuario=appUserServiceImpl.getUser(email);
            userInfo=new UsuarioInfo(usuario.getNombre(),email);*/


        return null;
    }






    @Operation(summary = "Devuelve las solicitudes del usuario.",tags = {"Usuarios"},security = {@SecurityRequirement(name="BearerJWT")})
    @GetMapping("/user/tickets")
    public List<Ticket> getUsuarioTickets(@RequestHeader("Authorization") String authHeader){
        Usuario usuario=null;
        String jwt=null;
        UsuarioInfo userInfo=null;
        String email="";

        if(authHeader!=null && authHeader.startsWith("Bearer")){
            jwt=authHeader.substring(7);

            email=jwtUtil.validateTokenAndRetrieveSubject(jwt);

            usuario=appUserServiceImpl.getUser(email);



        }

        return ticketService.getUsuario(usuario);
    }

    /*@PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
      appUserServiceImpl.addRoleToUser(form.getUsername(),form.getRolename());
      return ResponseEntity.ok().build();
    }*/
    @Operation(summary = "Guarda un nuevo usuario.",tags = {"Usuarios"},security = {@SecurityRequirement(name="BearerJWT")})
    @PostMapping
    public ResponseEntity<Usuario> saveUser(@RequestHeader("Authorization") String authHeader,@RequestBody Usuario usuario){


        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(appUserServiceImpl.saveUser(usuario));
    }

   /* @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader= request.getHeader(AUTHORIZATION);
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            try{
                String refresh_token= authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm= Algorithm.HMAC256("secret".getBytes());//TO DO: UTILITY CLASS
                JWTVerifier verifier= JWT.require(algorithm).build();
                DecodedJWT decodedJWT= verifier.verify(refresh_token);
                String username=decodedJWT.getSubject();
                AppUser appUser=appUserServiceImpl.getUser(username);
                String access_token= JWT.create()
                        .withSubject(appUser.getUsername())
                        .withExpiresAt(new java.sql.Date(System.currentTimeMillis() + 10 *60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",appUser.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String,String> tokens=new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);
            }catch (Exception e){
                response.setHeader("error",e.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String,String> error= new HashMap<>();
                error.put("error_message",e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            }

        }else{
           throw new RuntimeException("Refresh token is missing");
        }
    }*/

   /* @GetMapping("/logout")
    public String logout(Authentication authentication, @RequestHeader("Authorization") String authHeader){
        String jwt=null;

        if(authHeader!=null && authHeader.startsWith("Bearer")){
            jwt=authHeader.substring(7);

            tokenExpiredService.saveTokenExpired(jwt);
        }

        return "test";
    }*/

    @Data
    @AllArgsConstructor
    class UsuarioInfo{
        private String name;
        private String email;
    }
}

