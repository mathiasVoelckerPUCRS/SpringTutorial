/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.controller;

import br.com.crescer.social.entity.Banda;
import br.com.crescer.social.model.BandaModel;
import br.com.crescer.social.service.BandaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/banda")
public class BandaController {

    @Autowired
    private BandaService bandaService;

    @GetMapping("/{id}")
    public Banda getBandaById(@PathVariable Long id) {
        return bandaService.getById(id);
    }

    @ResponseBody
    @GetMapping("/usuario")
    public List<Banda> getBandaByUsuario(
            @RequestParam Long idUsuario) {
        return bandaService.getByUsuario(idUsuario);
    }
    
    

    @ResponseBody
    @GetMapping
    public Page<Banda> getList(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return bandaService.list(new PageRequest(page, size));
    }

    @ResponseBody
    @RequestMapping(value = "/registro", method = RequestMethod.POST)
    public Banda create(@RequestBody BandaModel bandaModel) {
        return bandaService.save(bandaModel);
    }

    @ResponseBody
    @RequestMapping(value = "/registro/{id}", method = RequestMethod.PUT)
    public Banda update(@PathVariable Long id, @RequestBody BandaModel bandaModel) {
        return bandaService.save(id, bandaModel);
    }

    @ResponseBody
    @RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE)
    public Banda delete(@PathVariable Long id) {
        return bandaService.deletar(id);
    }
}
