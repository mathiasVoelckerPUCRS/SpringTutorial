/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.repository;

import br.com.crescer.social.entity.Comentario;
import br.com.crescer.social.entity.Postagem;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author user
 */
public interface ComentarioRepository extends PagingAndSortingRepository<Comentario, Long> {

    Iterable<Comentario> findByPostagem(Postagem postagem);
    
}
