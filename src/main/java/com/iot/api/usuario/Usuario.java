package com.iot.api.usuario;


import com.iot.api.ticket.Ticket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name = "solicitudes_usuario",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    List<Ticket> solicitudes;


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

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", usuarioRol=" + usuarioRol +
                ", solicitudes=" + solicitudes +
                '}';
    }
}
