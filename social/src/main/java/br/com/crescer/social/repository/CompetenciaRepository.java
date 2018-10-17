/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.repository;

import br.com.crescer.social.entity.Comentario;
import br.com.crescer.social.entity.Competencia;
import br.com.crescer.social.entity.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author user
 */
public interface CompetenciaRepository extends PagingAndSortingRepository<Competencia, Long> {

    Iterable<Competencia> findByUsuario(Usuario usuario);
    
}
