package com.iot.api.usuario;


import com.iot.api.seguridad.excepciones.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UserDetailsService{
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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

    public Usuario saveUser(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }




}
