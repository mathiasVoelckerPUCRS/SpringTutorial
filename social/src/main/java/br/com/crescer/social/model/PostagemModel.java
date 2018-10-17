/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.model;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
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
public class PostagemModel {
    
    private String descricao;
    private Long idUsuario;
    private boolean publico;
    
}
