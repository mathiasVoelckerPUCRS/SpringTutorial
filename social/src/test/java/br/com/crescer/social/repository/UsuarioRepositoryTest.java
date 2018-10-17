/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.repository;

import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.repository.UsuarioRepository;
import java.util.Date;
import static java.util.stream.Collectors.toList;
import java.util.stream.StreamSupport;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.REQUIRED)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {
    @Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private UsuarioRepository usuarioRepository;

	/**
	 * Test of save method, of class UsuarioService.
	 */
        
        private final Usuario usuario = Usuario.builder().id(999L).admin(false)
                .data_nascimento(new Date()).email("teste")
                .login("teste").senha("senha").nome_completo("teste").build();
        
//	@Test
//	public void testSave() {
//		usuarioRepository.save(usuario);
//		assertEquals(usuario.getLogin(), usuarioRepository.findOne(usuario.getId()).getLogin());
//	}

	/**
	 * Test of findAll method, of class UsuarioService.
	 */
//	@Test
//	public void testFindAll() {
//		final Usuario usuario = new Usuario();
//                
//		usuarioRepository.save(usuario);
//
//		assertTrue(StreamSupport.stream(usuarioRepository.findAll().spliterator(), false)
//				.map(Usuario::getLogin)
//				.collect(toList())
//				.contains(usuario.getLogin()));
//
//	}

	/**
	 * Test of findOne method, of class UsuarioService.
	 */
	@Test
	public void testFindOne() {
		final Usuario usuario = new Usuario();
		usuario.setLogin("Mathias");
		testEntityManager.persist(usuario);
		assertEquals(usuario.getLogin(), usuarioRepository.findOne(usuario.getId()).getLogin());
	}
}
