/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.controller;

import br.com.crescer.social.component.UsuarioComponent;
import br.com.crescer.social.entity.Amizade;
import br.com.crescer.social.entity.Banda;
import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.service.AmizadeService;
import br.com.crescer.social.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
 * @author mathias.voelcker
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public UserDetails getUser(Authentication authentication) {
        UserDetails usuario = (UserDetails) authentication.getPrincipal();
        System.out.println(usuario);
        return usuario;
    }

    @GetMapping("/list")
    public Page<Usuario> getUserList(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "9") int size
    ) {
        return usuarioService.list(new PageRequest(page, size));
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Long id) {
        return usuarioService.loadById(id);
    }

    @GetMapping("/login")
    public List<Usuario> getUsuarioByLogin(@RequestParam String login) {
        return usuarioService.loadByLogin(login);
    }

    @GetMapping("/email")
    public Usuario getUsuarioByEmail(@RequestParam String email) {
        return usuarioService.loadByEmail(email);
    }

    @ResponseBody
    @RequestMapping(value = "/registro", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Usuario usuario) {
        if(usuarioService.loadByEmail(usuario.getEmail()) != null)
            return new ResponseEntity<Error>(new Error("Email ja utilizado"), HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(usuarioService.save(usuario));
    }

    @ResponseBody
    @RequestMapping(value = "/registro/{id}", method = RequestMethod.PUT)
    public Usuario update(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuarioService.update(id, usuario);
        return usuario;
    }

    @ResponseBody
    @RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE)
    public Usuario delete(@PathVariable Long id) {
        return usuarioService.delete(id);
    }

    @GetMapping("/amigos")
    public List<Usuario> getAmigosById(
            @RequestParam Long id,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size
    ) {
        return usuarioService.listAmigos(new PageRequest(page, size), id);
    }
    
    @GetMapping("/amigo")
    public Amizade getAmigoById(
            @RequestParam Long idUsuarioSolicitante,
            @RequestParam Long idUsuarioSolicitado){
        System.out.println(idUsuarioSolicitante);
        System.out.println(idUsuarioSolicitado);
        return usuarioService.getAmigoById(idUsuarioSolicitante, idUsuarioSolicitado);
    }

    @ResponseBody
    @RequestMapping(value = "/amigos", method = RequestMethod.POST)
    public ResponseEntity solicitarAmizade(
            @RequestParam(required = true) Long idSolicitante,
            @RequestParam(required = true) Long idSolicitado
    ) {
        return ResponseEntity.ok(usuarioService.SolicitarAmizade(idSolicitante, idSolicitado));
    }

    @GetMapping("/solicitacoes")
    public List<Usuario> getSolicitacoes(
            @RequestParam Long id,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        return usuarioService.listSolicitacoes(new PageRequest(page, size), id);
    }

    @ResponseBody
    @RequestMapping(value = "/aprovar", method = RequestMethod.PUT)
    public ResponseEntity aprovarAmizade(
            @RequestParam(required = true) Long idSolicitante,
            @RequestParam(required = true) Long idSolicitado
    ) {
        return ResponseEntity.ok(usuarioService.AprovarAmizade(idSolicitante, idSolicitado));
    }

    @ResponseBody
    @RequestMapping(value = "/desfazerAmizade", method = RequestMethod.DELETE)
    public ResponseEntity desfazerAmizade(
            @RequestParam(required = true) Long idSolicitante,
            @RequestParam(required = true) Long idSolicitado
    ) {
        return ResponseEntity.ok(usuarioService.DesfazerAmizade(idSolicitante, idSolicitado));
    }

}
