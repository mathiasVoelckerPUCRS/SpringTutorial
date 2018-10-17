/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.model;

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
public class ComentarioModel {
    private Long id_usuario;
    private Long id_postagem;
    private String descricao;
}
