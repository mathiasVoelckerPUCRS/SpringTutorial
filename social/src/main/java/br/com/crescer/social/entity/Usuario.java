/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author mathias.voelcker
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "USUARIO")
public class Usuario {
    private static final String SQ_NAME = "SQ_USUARIO";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
    @SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
    private Long id;

    @Basic(optional = false)
    private String login;

    @Basic(optional = false)
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
    @Basic(optional = false)
    private String nome_completo;
    
    @Basic(optional = false)
    private String senha;

    @Basic(optional = false)
    private Date data_nascimento;

    private String url_imagem_perfil;

    private String url_video_apresentacao;

    private String descricao;
    
    private boolean admin;

}
