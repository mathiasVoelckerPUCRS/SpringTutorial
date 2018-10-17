/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service;

import br.com.crescer.social.entity.Amizade;
import br.com.crescer.social.entity.Postagem;
import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.model.PostagemModel;
import br.com.crescer.social.repository.AmizadeRepository;
import br.com.crescer.social.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.crescer.social.repository.PostagemRepository;
import static java.util.Arrays.sort;
import java.util.Date;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author user
 */
@Service
public class PostagemService {
    
    @Autowired
    private PostagemRepository postagemRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AmizadeRepository amizadeRepository;
    
    public Postagem getById(Long idPostagem) {
        return postagemRepository.findById(idPostagem);
    }
    
    public Page<Postagem> loadByUsuarioId(Long usuarioId, Pageable pageable) {
        Usuario usuario = usuarioRepository.findById(usuarioId);
        return  postagemRepository.findByUsuarioOrderByDataPostagemDesc(usuario, pageable);
    }
    
    public Page<Postagem> loadByAmigos(Long usuarioId, Pageable pageable) {
        List<Usuario> amigos = listAmigos(usuarioId);
        Usuario usuario = usuarioRepository.findById(usuarioId);
        amigos.add(usuario);
        System.out.println(amigos.size());
        return postagemRepository.findByUsuarioInAndPublicoOrUsuarioOrderByDataPostagemDesc(amigos, true, usuario, pageable);
    }
    
    public Postagem save(PostagemModel postagemModel) {
        Postagem post = Postagem.builder().descricao(postagemModel.getDescricao())
                .usuario(usuarioRepository.findById(postagemModel.getIdUsuario()))
                .curtidas(new ArrayList<Usuario>())
                .dataPostagem(new Date())
                .publico(postagemModel.isPublico()).build();
        postagemRepository.save(post);
        return post;
    }

    public Postagem update(Long id, PostagemModel postagemModel) {
        List<Usuario> List;
        Postagem post = Postagem.builder().id(id).descricao(postagemModel.getDescricao())
                .usuario(usuarioRepository.findById(postagemModel.getIdUsuario()))
                .curtidas(new ArrayList<Usuario>())
                .dataPostagem(new Date())
                .publico(postagemModel.isPublico()).build();
        postagemRepository.save(post);
        return post;
    }
    
    private List<Usuario> listAmigos(Long id) {
        List<Usuario> amigos = new ArrayList<Usuario>();
        Usuario usuario = usuarioRepository.findById(id);
        Iterable<Amizade> amizadesComoSolicitado = amizadeRepository.findByUsuarioSolicitado(usuario);
        for (Amizade amizade : amizadesComoSolicitado) {
            if (amizade.isAprovado()) {
                amigos.add(amizade.getUsuarioSolicitante());
            }
        }
        Iterable<Amizade> amizadesComoSolicitante = amizadeRepository.findByUsuarioSolicitante(usuario);
        for (Amizade amizade : amizadesComoSolicitante) {
            if (amizade.isAprovado()) {
                amigos.add(amizade.getUsuarioSolicitado());
            }
        }
        return amigos;
    }
    
    public Postagem delete(Long id) {
        Postagem postagem = postagemRepository.findById(id);
        if(postagem == null)
            return null;
        postagemRepository.delete(postagem);
        return postagem;
    }

    public Postagem createCurtida(Long idUsuario, Long idPostagem) {
        Postagem postagem = postagemRepository.findById(idPostagem);
        List<Usuario> usuarios = postagem.getCurtidas();
        usuarios.add(usuarioRepository.findById(idUsuario));
        postagem.setCurtidas(usuarios);
        postagemRepository.save(postagem);
        return postagem;
    }

    public Postagem deleteCurtida(Long idUsuario, Long idPostagem) {
        Postagem postagem = postagemRepository.findById(idPostagem);
        List<Usuario> usuarios = postagem.getCurtidas();
        usuarios.remove(usuarioRepository.findById(idUsuario));
        postagem.setCurtidas(usuarios);
        postagemRepository.save(postagem);
        return postagem;
    }

//    public Postagem getCurtidas(Long idPostagem) {
////        return postagemRepository.findById(idPostagem).;
//    }

}
