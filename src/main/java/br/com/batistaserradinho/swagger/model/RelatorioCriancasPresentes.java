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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "relatorio_criancaspresentes", catalog = "igreja", schema = "igreja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RelatorioCriancasPresentes.findAll", query = "SELECT r FROM RelatorioCriancasPresentes r")
    , @NamedQuery(name = "RelatorioCriancasPresentes.findById", query = "SELECT r FROM RelatorioCriancasPresentes r WHERE r.id = :id")
    , @NamedQuery(name = "RelatorioCriancasPresentes.findByNome", query = "SELECT r FROM RelatorioCriancasPresentes r WHERE r.nome = :nome")})
public class RelatorioCriancasPresentes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;
    @Size(max = 100)
    @Column(length = 100)
    private String nome;
    @JoinColumn(name = "celula_relatorio_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private CelulaRelatorio celulaRelatorioId;

    public RelatorioCriancasPresentes() {
    }

    public RelatorioCriancasPresentes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CelulaRelatorio getCelulaRelatorioId() {
        return celulaRelatorioId;
    }

    public void setCelulaRelatorioId(CelulaRelatorio celulaRelatorioId) {
        this.celulaRelatorioId = celulaRelatorioId;
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
        if (!(object instanceof RelatorioCriancasPresentes)) {
            return false;
        }
        RelatorioCriancasPresentes other = (RelatorioCriancasPresentes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.RelatorioCriancasPresentes[ id=" + id + " ]";
    }
    
}
