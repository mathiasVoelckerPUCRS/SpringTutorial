/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.controller;

import br.com.crescer.social.entity.Postagem;
import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.model.PostagemModel;
import br.com.crescer.social.service.AmizadeService;
import br.com.crescer.social.service.PostagemService;
import br.com.crescer.social.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/postagem")
public class PostagemController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PostagemService postService;

    @GetMapping("/{idPostagem}")
    public Postagem getPostById(@PathVariable Long idPostagem) {
        return postService.getById(idPostagem);
    }
    
    @GetMapping("/usuarios")
    public Page<Postagem> getPostagemByUsuario(@RequestParam Long usuarioId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        return postService.loadByUsuarioId(usuarioId, new PageRequest(page, size));
    }

    @GetMapping("/amigos")
    public Page<Postagem> getPostByAmigos(@RequestParam Long usuarioId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size
    ) {
        return postService.loadByAmigos(usuarioId, new PageRequest(page, size));
    }

    @ResponseBody
    @RequestMapping(value = "/registro", method = RequestMethod.POST)
    public Postagem create(@RequestBody PostagemModel postagemModel) {
        return postService.save(postagemModel);
    }

    @ResponseBody
    @RequestMapping(value = "/registro/{id}", method = RequestMethod.PUT)
    public Postagem update(@PathVariable Long id, @RequestBody PostagemModel postModel) {
        return postService.update(id, postModel);
    }

    @ResponseBody
    @RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE)
    public Postagem delete(@PathVariable Long id) {
        return postService.delete(id);
    }

    @ResponseBody
    @RequestMapping(value = "/curtida", method = RequestMethod.POST)
    public Postagem createCurtida(
            @RequestParam Long idUsuario,
            @RequestParam Long idPostagem) {
        return postService.createCurtida(idUsuario, idPostagem);
    }
    
    @ResponseBody
    @RequestMapping(value = "/curtida", method = RequestMethod.DELETE)
    public Postagem deleteCurtida(
            @RequestParam Long idUsuario,
            @RequestParam Long idPostagem) {
        return postService.deleteCurtida(idUsuario, idPostagem);
    }
    
}
