/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service;

import br.com.crescer.social.entity.Competencia;
import br.com.crescer.social.entity.Instrumento;
import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.model.CompetenciaModel;
import br.com.crescer.social.repository.CompetenciaRepository;
import br.com.crescer.social.repository.InstrumentoRepository;
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
public class InstrumentoService {

    @Autowired
    private InstrumentoRepository instrumentoRepository;

    public Instrumento getById(Long idInstrumento) {
        return instrumentoRepository.findOne(idInstrumento);
    }

    public Instrumento save(Instrumento instrumento) {
        return instrumentoRepository.save(instrumento);
    }
    
    public Instrumento save(Long id, Instrumento instrumento) {
        instrumento.setId(id);
        return instrumentoRepository.save(instrumento);
    }
    
    public Instrumento deletar(Long id) {
        Instrumento instrumento = instrumentoRepository.findOne(id);
        instrumentoRepository.delete(instrumento);
        return instrumento;
    }

    public List<Instrumento> getInstrumentos() {
        List<Instrumento> instrumentos = new ArrayList<Instrumento>();
        Iterable<Instrumento> competenciasIterable = instrumentoRepository.findAll();
        competenciasIterable.forEach(instrumentos::add);
        return instrumentos;
    }
}
