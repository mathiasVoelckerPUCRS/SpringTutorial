/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service;

import br.com.crescer.social.entity.Banda;
import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.model.BandaModel;
import br.com.crescer.social.repository.BandaRepository;
import br.com.crescer.social.repository.InstrumentoRepository;
import br.com.crescer.social.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author user
 */
@Service
public class BandaService {
    @Autowired
    private BandaRepository bandaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private InstrumentoRepository instrumentoRepository;

    public Banda getById(Long id) {
        return bandaRepository.findById(id);
    }
    
    public Page<Banda> list(Pageable pageable) {
       return bandaRepository.findAll(pageable);
    }
    
    public List<Banda> getByUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario);
        List<Banda> bandas = new ArrayList<Banda>();
        Iterable<Banda> bandasIterable = bandaRepository.findAll();
        bandasIterable.forEach(banda->{
            if(banda.getUsuarios().contains(usuario))
                bandas.add(banda);
        });
        return bandas;
    }

    public Banda save(BandaModel bandaModel) {
        List<Usuario> usuariosBanda = new ArrayList<Usuario>();
        for(Long id : bandaModel.getId_usuarios()){
            usuariosBanda.add(usuarioRepository.findById(id));
        }
        Banda banda = Banda.builder()
                .titulo(bandaModel.getTitulo())
                .descricao(bandaModel.getDescricao())
                .url_imagem_perfil(bandaModel.getUrl_imagem_perfil())
                .url_video_apresentacao(bandaModel.getUrl_video_apresentacao())
                .usuarios(usuariosBanda).build();
        bandaRepository.save(banda);
        return banda;
    }
    
    public Banda save(Long id, BandaModel bandaModel) {
        List<Usuario> usuariosBanda = new ArrayList<Usuario>();
        for(Long idBandas : bandaModel.getId_usuarios()){
            usuariosBanda.add(usuarioRepository.findById(idBandas));
        }
        Banda banda = Banda.builder()
                .id(id)
                .titulo(bandaModel.getTitulo())
                .descricao(bandaModel.getDescricao())
                .url_imagem_perfil(bandaModel.getUrl_imagem_perfil())
                .url_video_apresentacao(bandaModel.getUrl_video_apresentacao())
                .usuarios(usuariosBanda).build();
        bandaRepository.save(banda);
        return banda;
    }
    
    public Banda deletar(Long id) {
        Banda banda = bandaRepository.findOne(id);
        bandaRepository.delete(banda);
        return banda;
    }
}
