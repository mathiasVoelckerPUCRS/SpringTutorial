/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service;

import br.com.crescer.social.entity.Amizade;
import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.repository.AmizadeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author mathias.voelcker
 */
public class AmizadeService {
    
    @Autowired
    AmizadeRepository amizadeRepository;
    
    public Page<Amizade> list(Pageable pageable) {
        return amizadeRepository.findAll(pageable);
    }

//    public List<Usuario> listAmigos(Pageable pageable, Long id) {
//        List<Usuario> amigos = new ArrayList<Usuario>();
//        Usuario usuario = usuarioRepository.findById(id);
//        Page<Amizade> amizades = amizadeRepository.findByUsuarioSolicitado(pageable, id);
//        for(Amizade amizade : amizades){
//            amigos.add(amizade.getUsuarioSolicitante());
//        }
//        return amigos;
//    }

    public void save(Amizade amizade) {
        amizadeRepository.save(amizade);
    }
    
    public Amizade delete(Long id){
        Amizade amizade = amizadeRepository.findOne(id);
        amizadeRepository.delete(id);
        return amizade;
    }
}
