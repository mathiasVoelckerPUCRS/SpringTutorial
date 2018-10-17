/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.component;

import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.service.UsuarioService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author usuario
 */
@Component
public class UsuarioComponent {

    @Autowired
    UsuarioService usuarioService;

    public Usuario loggedUsuario() {
        return Optional
                .ofNullable(loggedUsuarioDetails())
                .orElse(null);
    }

    public Usuario loggedUsuarioDetails() {
        return Optional
                .ofNullable(usuario())
                .map(Usuario::getEmail)
                .map(usuarioService::findByEmail)
                .orElse(null);
    }

    private Usuario usuario() {
        return Optional
                .ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .map(Usuario.class::cast)
                .orElse(null);
    } 
}
