/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.controller;

import br.com.crescer.social.entity.Comentario;
import br.com.crescer.social.entity.Competencia;
import br.com.crescer.social.entity.Postagem;
import br.com.crescer.social.model.ComentarioModel;
import br.com.crescer.social.model.CompetenciaModel;
import br.com.crescer.social.service.CompetenciaService;
import br.com.crescer.social.service.PostagemService;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author user
 */
@RestController
@RequestMapping("/competencia")
public class CompetenciaController {
    
    @Autowired
    private CompetenciaService competenciaService;
    
    @ResponseBody
    @GetMapping
    public Competencia getCompetenciaById(
            @RequestParam Long idCompetencia) {
        return competenciaService.getById(idCompetencia);
    }
    
    @ResponseBody
    @GetMapping("/usuario")
    public List<Competencia> getCompetenciaByUsuario(
            @RequestParam Long idUsuario) {
        return competenciaService.getByUsuario(idUsuario);
    }
    
    @ResponseBody
    @RequestMapping(value = "/registro", method = RequestMethod.POST)
    public Competencia create(@RequestBody CompetenciaModel competenciaModel) {
        return competenciaService.save(competenciaModel);
    }
    
    @ResponseBody
    @RequestMapping(value = "/registro/{id}", method = RequestMethod.PUT)
    public Competencia update(@PathVariable Long id, @RequestBody CompetenciaModel competenciaModel) {
        return competenciaService.save(id, competenciaModel);
    }
    
    @ResponseBody
    @RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE)
    public Competencia delete(@PathVariable Long id) {
        return competenciaService.deletar(id);
    }
    
}
