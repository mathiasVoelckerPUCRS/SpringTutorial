package br.com.crescer.social.security;

import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.service.UsuarioService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author carloshenrique
 */
@Service
public class SocialUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    public static class CustomUserDetails extends User {

        @Getter
        @Setter
        private String email;
        @Getter
        @Setter
        private String password;

        public CustomUserDetails(Usuario usuario, Collection<? extends GrantedAuthority> authorities
        ) {
            super(usuario.getEmail(), usuario.getSenha(), authorities);
            this.email = usuario.getEmail();
            this.password = usuario.getSenha();
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final Usuario usuario = usuarioService.findByEmail(username);
        if (usuario == null) {
            return null;
        }
        final List<GrantedAuthority> grants = new ArrayList<>();
        grants.add(() -> "ROLE_ADMIN");
        return new CustomUserDetails(usuario, grants);
    }
}
