package com.iot.api.usuario;


import com.iot.api.seguridad.JWTVerificador;
import com.iot.api.seguridad.excepciones.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UserDetailsService{
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JWTVerificador jwtVerificador;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if(usuario==null){
            throw new NotFoundException("Usuario no encontrado");
        }

        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(usuario.getUsuarioRol().toString()));

        return new org.springframework.security.core.userdetails.User(usuario.getEmail(),usuario.getPassword(),authorities);
    }

    public Usuario getUser(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Map<String, Object> getUserAuth(String email){
        Usuario usuario=getUser(email);
        Map<String, Object> map =jwtVerificador.getUserAuth(usuario.getUsuarioRol().toString()) ;

        return map;

    }


    public Usuario saveUser(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario guardarUsuario(String email,String rol,String nombre) {
        String encodedPassword= bCryptPasswordEncoder.encode("secure190");
        Usuario usuario=new Usuario(nombre,email,encodedPassword,Enum.valueOf(UsuarioRol.class,rol.toUpperCase()));


        return usuarioRepository.save(usuario);
    }




}
