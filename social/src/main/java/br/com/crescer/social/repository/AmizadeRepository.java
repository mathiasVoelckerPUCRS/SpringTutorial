/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.repository;

import br.com.crescer.social.entity.Amizade;
import br.com.crescer.social.entity.Usuario;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author mathias.voelcker
 */
public interface AmizadeRepository extends PagingAndSortingRepository<Amizade, Long> {

    Page<Amizade> findByUsuarioSolicitado(Pageable pageable, Usuario usuario);

    Page<Amizade> findByUsuarioSolicitante(Pageable pageable, Usuario usuario);
    
    List<Amizade> findByUsuarioSolicitado(Usuario usuario);
    
    List<Amizade> findByUsuarioSolicitante(Usuario usuario);
    
    Amizade findByUsuarioSolicitanteInAndUsuarioSolicitadoIn(Usuario usuarioSolicitante, Usuario usuarioSolicitado);

    Amizade findByUsuarioSolicitadoInAndUsuarioSolicitanteIn(Usuario usuarioSolicitado, Usuario usuarioSolicitante);
    
}
