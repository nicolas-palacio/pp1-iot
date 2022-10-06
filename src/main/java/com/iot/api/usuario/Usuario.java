package com.iot.api.usuario;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="usuarios")
public class Usuario {
    @SequenceGenerator(name = "user_sequence",sequenceName = "user_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_registered_sequence")
    private Long id;
    private String nombre;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UsuarioRol usuarioRol;

    public Usuario(String nombre, String email, String password, UsuarioRol usuarioRol) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.usuarioRol = usuarioRol;
    }

/*@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority= new SimpleGrantedAuthority(usuarioRol.name());
        return Collections.singletonList(authority);
    }*/

}
