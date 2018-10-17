/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.controller;

import br.com.crescer.social.entity.Postagem;
import br.com.crescer.social.entity.Comentario;
import br.com.crescer.social.model.ComentarioModel;
import br.com.crescer.social.model.PostagemModel;
import br.com.crescer.social.service.ComentarioService;
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
@RequestMapping("/comentario")
public class ComentarioController {
    
    @Autowired
    private ComentarioService comentarioService;
    @Autowired
    private PostagemService postService;
    
    @GetMapping
    public List<Comentario> getByPostagemId(@RequestParam Long postagemId) {
        return comentarioService.loadByPostagemId(postagemId);
    }
    
    @ResponseBody
    @RequestMapping(value = "/registro", method = RequestMethod.POST)
    public Comentario create(@RequestBody ComentarioModel comentarioModel) {
        return comentarioService.save(comentarioModel);
    }
    
    @ResponseBody
    @RequestMapping(value = "/registro", method = RequestMethod.PUT)
    public Comentario update(Long id, @RequestBody ComentarioModel comentarioModel) {
        return comentarioService.save(id, comentarioModel);
    }
    
    @ResponseBody
    @RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE)
    public Comentario delete(@PathVariable Long id) {
        return comentarioService.delete(id);
    }
}
