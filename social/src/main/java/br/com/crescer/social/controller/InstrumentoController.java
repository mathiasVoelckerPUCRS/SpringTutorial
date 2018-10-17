/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.controller;

import br.com.crescer.social.entity.Instrumento;
import br.com.crescer.social.service.InstrumentoService;
import java.util.List;
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
@RequestMapping("/instrumento")
public class InstrumentoController {
    @Autowired
    private InstrumentoService instrumentoService;
    
    @ResponseBody
    @GetMapping("/{id}")
    public Instrumento getInstrumentoById(@PathVariable Long id) {
        return instrumentoService.getById(id);
    }
    
    @ResponseBody
    @GetMapping
    public List<Instrumento> list(){
        return instrumentoService.getInstrumentos();
    }
    
    @ResponseBody
    @RequestMapping(value = "/registro", method = RequestMethod.POST)
    public Instrumento create(@RequestBody Instrumento instrumento) {
        return instrumentoService.save(instrumento);
    }
    
    @ResponseBody
    @RequestMapping(value = "/registro/{id}", method = RequestMethod.PUT)
    public Instrumento update(@PathVariable Long id, @RequestBody Instrumento instrumento) {
        return instrumentoService.save(id, instrumento);
    }
    
    @ResponseBody
    @RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE)
    public Instrumento delete(@PathVariable Long id) {
        return instrumentoService.deletar(id);
    }
}
