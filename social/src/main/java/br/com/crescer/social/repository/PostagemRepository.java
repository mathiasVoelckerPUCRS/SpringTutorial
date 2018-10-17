/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.repository;

import br.com.crescer.social.entity.Amizade;
import br.com.crescer.social.entity.Postagem;
import br.com.crescer.social.entity.Usuario;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author user
 */
public interface PostagemRepository extends PagingAndSortingRepository<Postagem, Long> {

    Page<Postagem> findByUsuario(Usuario usuario, Pageable pageable);

    Postagem findById(Long id);

    Page<Postagem> findByUsuarioOrderByDataPostagemDesc(Usuario usuario, Pageable pageable);
    
    Page<Postagem> findByUsuarioInAndPublicoOrUsuarioOrderByDataPostagemDesc(List<Usuario> usuarios, boolean publico, Usuario usuario, Pageable pageable);

    
}
