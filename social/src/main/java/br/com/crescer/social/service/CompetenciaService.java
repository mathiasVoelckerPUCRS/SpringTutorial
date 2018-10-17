/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service;

import br.com.crescer.social.entity.Comentario;
import br.com.crescer.social.entity.Competencia;
import br.com.crescer.social.entity.Postagem;
import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.model.CompetenciaModel;
import br.com.crescer.social.repository.ComentarioRepository;
import br.com.crescer.social.repository.CompetenciaRepository;
import br.com.crescer.social.repository.InstrumentoRepository;
import br.com.crescer.social.repository.PostagemRepository;
import br.com.crescer.social.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author user
 */
@Service
public class CompetenciaService {
    @Autowired
    private CompetenciaRepository competenciaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private InstrumentoRepository instrumentoRepository;

    public Competencia getById(Long idCompetencia) {
        return competenciaRepository.findOne(idCompetencia);
    }

    public Competencia save(CompetenciaModel competenciaModel) {
        Competencia competencia = Competencia.builder()
                .usuario(usuarioRepository.findById(competenciaModel.getId_usuario()))
                .instrumento(instrumentoRepository.findOne(competenciaModel.getId_instrumento()))
                .nivel(competenciaModel.getNivel())
                .anos_pratica(competenciaModel.getAnos_pratica()).build();
        competenciaRepository.save(competencia);
        return competencia;
    }
    
    public Competencia save(Long id, CompetenciaModel competenciaModel) {
        Competencia competencia = Competencia.builder().id(id)
                .usuario(usuarioRepository.findById(competenciaModel.getId_usuario()))
                .instrumento(instrumentoRepository.findOne(competenciaModel.getId_instrumento()))
                .nivel(competenciaModel.getNivel())
                .anos_pratica(competenciaModel.getAnos_pratica()).build();
        competenciaRepository.save(competencia);
        return competencia;
    }
    
    public Competencia deletar(Long id) {
        Competencia competencia = competenciaRepository.findOne(id);
        competenciaRepository.delete(competencia);
        return competencia;
    }

    public List<Competencia> getByUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario);
        List<Competencia> competencias = new ArrayList<Competencia>();
        Iterable<Competencia> competenciasIterable = competenciaRepository.findByUsuario(usuario);
        competenciasIterable.forEach(competencias::add);
        return competencias;
    }
    
}
