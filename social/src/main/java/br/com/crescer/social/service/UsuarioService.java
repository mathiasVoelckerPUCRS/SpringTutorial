/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service;

import br.com.crescer.social.entity.Amizade;
import br.com.crescer.social.repository.UsuarioRepository;
import br.com.crescer.social.repository.AmizadeRepository;
import br.com.crescer.social.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author mathias.voelcker
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AmizadeRepository amizadeRepository;

    public Usuario findByEmail(String username) {
        return usuarioRepository.findByEmailIgnoreCase(username);
    }

    public Page<Usuario> list(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Iterable<Usuario> list() {
        return usuarioRepository.findAll();
    }

    public Usuario loadById(Long id) {
        return usuarioRepository.findOne(id);
    }
    
    public List<Usuario> loadByLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }
    
    public Usuario loadByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario save(Usuario usuario) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }
    
    public Usuario update(Long id, Usuario usuarioNovo){
        Usuario usuario = usuarioRepository.findById(id);
        usuario.setLogin(usuarioNovo.getLogin());
        usuario.setNome_completo(usuarioNovo.getNome_completo());
        usuario.setUrl_imagem_perfil(usuarioNovo.getUrl_imagem_perfil());
        usuario.setUrl_video_apresentacao(usuarioNovo.getUrl_video_apresentacao());
            usuarioRepository.save(usuario);
        return usuario;
    }

    public Usuario delete(Long id) {
        Usuario usuario = usuarioRepository.findOne(id);
        usuarioRepository.delete(id);
        return usuario;
    }

    //AMIZADE
    public List<Usuario> listAmigos(Pageable pageable, Long id) {
        List<Usuario> amigos = new ArrayList<Usuario>();
        Usuario usuario = usuarioRepository.findById(id);
        Page<Amizade> amizadesComoSolicitado = amizadeRepository.findByUsuarioSolicitado(pageable, usuario);
        for (Amizade amizade : amizadesComoSolicitado) {
            if (amizade.isAprovado()) {
                amigos.add(amizade.getUsuarioSolicitante());
            }
        }
        Page<Amizade> amizadesComoSolicitante = amizadeRepository.findByUsuarioSolicitante(pageable, usuario);
        for (Amizade amizade : amizadesComoSolicitante) {
            if (amizade.isAprovado()) {
                amigos.add(amizade.getUsuarioSolicitado());
            }
        }
        return amigos;
    }
    
    public List<Usuario> listContatos(Pageable pageable, Long id) {
        List<Usuario> contatos = new ArrayList<Usuario>();
        Usuario usuario = usuarioRepository.findById(id);
        Page<Amizade> amizadesComoSolicitado = amizadeRepository.findByUsuarioSolicitado(pageable, usuario);
        for (Amizade amizade : amizadesComoSolicitado) {
            if (amizade.isAprovado()) {
                contatos.add(amizade.getUsuarioSolicitante());
            }
        }
        Page<Amizade> amizadesComoSolicitante = amizadeRepository.findByUsuarioSolicitante(pageable, usuario);
        for (Amizade amizade : amizadesComoSolicitante) {
            if (amizade.isAprovado()) {
                contatos.add(amizade.getUsuarioSolicitado());
            }
        }
        return contatos;
    }

    public List<Usuario> listSolicitacoes(Pageable pageable, Long id) {
        List<Usuario> amigos = new ArrayList<Usuario>();
        Usuario usuario = usuarioRepository.findById(id);
        Page<Amizade> amizadesComoSolicitado = amizadeRepository.findByUsuarioSolicitado(pageable, usuario);
        for (Amizade amizade : amizadesComoSolicitado) {
            if (!amizade.isAprovado()) {
                amigos.add(amizade.getUsuarioSolicitante());
            }
        }
        return amigos;
    }
    

    public String SolicitarAmizade(Long idSolicitante, Long idSolicitado) {
        Usuario usuarioSolicitante = usuarioRepository.findById(idSolicitante);
        Usuario usuarioSolicitado = usuarioRepository.findById(idSolicitado);
        if (isAmigo(usuarioSolicitante, usuarioSolicitado)) {
            return "Usuarios já são amigos";
        }
        Amizade amizade = Amizade.builder().usuarioSolicitante(usuarioSolicitante)
                .usuarioSolicitado(usuarioSolicitado).aprovado(false).build();
        amizadeRepository.save(amizade);
        return "Sucesso";
    }
    
    public Amizade getAmigoById(Long idSolicitante, Long idSolicitado){
        Usuario usuarioSolicitante = usuarioRepository.findById(idSolicitante);
        Usuario usuarioSolicitado = usuarioRepository.findById(idSolicitado);
        return getAmigo(usuarioSolicitante, usuarioSolicitado);
    }

    private boolean isAmigo(Usuario usuarioSolicitante, Usuario usuarioSolicitado) {
        if (amizadeRepository
                .findByUsuarioSolicitado(usuarioSolicitado)
                .stream()
                .map(Amizade::getUsuarioSolicitante)
                .collect(Collectors.toList())
                .contains(usuarioSolicitante)) {
            return true;
        }
        if (amizadeRepository
                .findByUsuarioSolicitante(usuarioSolicitante)
                .stream()
                .map(Amizade::getUsuarioSolicitado)
                .collect(Collectors.toList())
                .contains(usuarioSolicitado)) {
            return true;
        }
        return false;
    }
    
     private Amizade getAmigo(Usuario usuarioSolicitante, Usuario usuarioSolicitado) {
        Amizade amizade = amizadeRepository.findByUsuarioSolicitanteInAndUsuarioSolicitadoIn(usuarioSolicitante, usuarioSolicitado);
        if(amizade != null)
            return amizade;
        amizade = amizadeRepository.findByUsuarioSolicitadoInAndUsuarioSolicitanteIn(usuarioSolicitante, usuarioSolicitado);
        return amizade;
    }

    public Amizade AprovarAmizade(Long idSolicitante, Long idSolicitado) {
        Usuario usuarioSolicitante = usuarioRepository.findById(idSolicitante);
        Usuario usuarioSolicitado = usuarioRepository.findById(idSolicitado);

        Amizade amizade = amizadeRepository.findByUsuarioSolicitanteInAndUsuarioSolicitadoIn(usuarioSolicitante, usuarioSolicitado);
        amizade.setAprovado(true);
        amizadeRepository.save(amizade);
        return amizade;
    }
    
    public String DesfazerAmizade(Long idSolicitante, Long idSolicitado) {
        Usuario usuarioSolicitante = usuarioRepository.findById(idSolicitante);
        Usuario usuarioSolicitado = usuarioRepository.findById(idSolicitado);

        Amizade amizade = amizadeRepository.findByUsuarioSolicitanteInAndUsuarioSolicitadoIn(usuarioSolicitante, usuarioSolicitado);
        System.out.println(amizade);
        if(amizade == null)
            amizade = amizadeRepository.findByUsuarioSolicitanteInAndUsuarioSolicitadoIn(usuarioSolicitado, usuarioSolicitante);
        System.out.println(amizade);
        amizadeRepository.delete(amizade);
        return "Sucesso";
    }
}
