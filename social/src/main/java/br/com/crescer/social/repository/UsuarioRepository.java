/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.repository;

import br.com.crescer.social.entity.Usuario;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author mathias.voelcker
 */
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

    Usuario findByEmailIgnoreCase(String email);

    Usuario findByLoginIgnoreCase(String login);

    Usuario findById(Long id);

    List<Usuario> findByLogin(String login);

    Usuario findByEmail(String email);
}
