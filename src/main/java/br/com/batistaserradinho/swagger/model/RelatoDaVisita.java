/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.swagger.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos
 */
@Entity
@Table(catalog = "igreja", schema = "igreja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RelatoDaVisita.findAll", query = "SELECT r FROM RelatoDaVisita r")
    , @NamedQuery(name = "RelatoDaVisita.findById", query = "SELECT r FROM RelatoDaVisita r WHERE r.id = :id")
    , @NamedQuery(name = "RelatoDaVisita.findByDescricao", query = "SELECT r FROM RelatoDaVisita r WHERE r.descricao = :descricao")})
public class RelatoDaVisita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    @JoinColumn(name = "relatoriocelula_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private CelulaRelatorio relatoriocelulaId;

    public RelatoDaVisita() {
    }

    public RelatoDaVisita(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public CelulaRelatorio getRelatoriocelulaId() {
        return relatoriocelulaId;
    }

    public void setRelatoriocelulaId(CelulaRelatorio relatoriocelulaId) {
        this.relatoriocelulaId = relatoriocelulaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelatoDaVisita)) {
            return false;
        }
        RelatoDaVisita other = (RelatoDaVisita) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.RelatoDaVisita[ id=" + id + " ]";
    }
    
}
