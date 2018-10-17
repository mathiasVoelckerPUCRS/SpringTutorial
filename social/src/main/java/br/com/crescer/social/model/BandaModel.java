/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author user
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BandaModel {
    private String titulo;
    private String descricao;
    private String url_imagem_perfil;
    private String url_video_apresentacao;
    private List<Long> id_usuarios;
}
