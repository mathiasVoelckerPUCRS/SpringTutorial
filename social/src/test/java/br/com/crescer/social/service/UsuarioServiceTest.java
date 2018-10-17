/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service;

import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.repository.UsuarioRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author mathias.voelcker
 */
@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private Usuario mock = Mockito.mock(Usuario.class);
    
    
    @Test
    public void testSave() {
        Usuario usuario = Usuario.builder().senha("teste").build();
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        final Usuario results = usuarioService.save(usuario);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        assertTrue(encoder.matches(results.getSenha(), encoder.encode(usuario.getSenha())));
        Mockito.verify(usuarioRepository).save(usuario);
    }

    @Test
    public void testFindByEmail() {
        String email = "teste";
        when(usuarioRepository.findByEmailIgnoreCase(email)).thenReturn(mock);
        final Usuario usuario = usuarioService.findByEmail(email);
        assertEquals(mock, usuario);
        Mockito.verify(usuarioRepository).findByEmailIgnoreCase(email);
    }
    
//    @Test
//    public void testUpdate() {
//        Usuario usuario = Usuario.builder()
//                .login("teste")
//                .nome_completo("teste")
//                .url_imagem_perfil("teste")
//                .url_video_apresentacao("teste")
//                .build();
//        when(usuarioRepository.save(mock)).thenReturn(mock);
//        final Usuario results = usuarioService.update(1L, usuario);
//        Mockito.verify(usuarioRepository).save(mock);
//    }
//    
//    @Test
//    public void testListPage(){
//        Page<Usuario> usuarios = new PageRequest<Usuario>();
//        Pageable pg = new PageRequest(0, 9);
//        when(usuarioRepository.findAll(pg)).thenReturn(usuarios);
//        final Usuario usuario = usuarioService.list(pg);
//        assertEquals(mock, usuario);
//        Mockito.verify(usuarioRepository).findByEmailIgnoreCase(email);
//    }
}
//Usuario usuario = Usuario.builder().id(999L).admin(false)
//                .data_nascimento(new Date()).email("teste")
//                .login("teste").senha("senha").nome_completo("teste").build();
//        