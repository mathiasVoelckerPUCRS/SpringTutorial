/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.entity;

import java.util.List;
import javax.persistence.Basic;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@Table(name = "BANDA")
public class Banda {

    private static final String SQ_NAME = "SQ_BANDA";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
    @SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
    private Long id;
    
    @Basic(optional = false)
    private String titulo;
    
    @Basic(optional = false)
    private String descricao;
    
    private String url_imagem_perfil;
    
    private String url_video_apresentacao;
    
    @ManyToMany
    @JoinTable(name = "banda_usuario",
            joinColumns
            = @JoinColumn(name = "id_usuario", referencedColumnName = "id"),
            inverseJoinColumns
            = @JoinColumn(name = "id_banda", referencedColumnName = "id"))
    private List<Usuario> usuarios;
}
