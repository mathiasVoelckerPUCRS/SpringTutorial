/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service;

import br.com.crescer.social.entity.Comentario;
import br.com.crescer.social.entity.Postagem;
import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.model.ComentarioModel;
import br.com.crescer.social.repository.AmizadeRepository;
import br.com.crescer.social.repository.ComentarioRepository;
import br.com.crescer.social.repository.PostagemRepository;
import br.com.crescer.social.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author user
 */
@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PostagemRepository postagemRepository;

    public List<Comentario> loadByPostagemId(Long postagemId) {
        Postagem postagem = postagemRepository.findOne(postagemId);
        Iterable<Comentario> comentariosIterable = comentarioRepository.findByPostagem(postagem);
        List<Comentario> comentarios = new ArrayList<Comentario>();
        comentariosIterable.forEach(comentarios::add);
        return comentarios;
    }

    public Comentario save(ComentarioModel comentarioModel) {
        Comentario comentario = Comentario.builder()
                .usuario(usuarioRepository.findById(comentarioModel.getId_usuario()))
                .postagem(postagemRepository.findOne(comentarioModel.getId_postagem()))
                .descricao(comentarioModel.getDescricao()).build();
        comentarioRepository.save(comentario);
        return comentario;
    }
    
    public Comentario save(Long id, ComentarioModel comentarioModel) {
        Comentario comentario = Comentario.builder().id(id)
                .usuario(usuarioRepository.findById(comentarioModel.getId_usuario()))
                .postagem(postagemRepository.findOne(comentarioModel.getId_postagem()))
                .descricao(comentarioModel.getDescricao()).build();
        comentarioRepository.save(comentario);
        return comentario;
    }
    
    public Comentario delete(Long id) {
        Comentario comentario = comentarioRepository.findOne(id);
        comentarioRepository.delete(comentario);
        return comentario;
    }
}
