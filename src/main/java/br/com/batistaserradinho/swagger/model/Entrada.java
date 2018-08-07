/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.swagger.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cruiz
 */
@Entity
@Table(catalog = "igreja", schema = "igreja", name = "entrada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entrada.findAll", query = "SELECT e FROM Entrada e")
    , @NamedQuery(name = "Entrada.findById", query = "SELECT e FROM Entrada e WHERE e.id = :id")
    , @NamedQuery(name = "Entrada.findByDatadaentrada", query = "SELECT e FROM Entrada e WHERE e.datadaentrada = :datadaentrada")
    , @NamedQuery(name = "Entrada.findByObservacao", query = "SELECT e FROM Entrada e WHERE e.observacao = :observacao")
    , @NamedQuery(name = "Entrada.findByDatadeinsercao", query = "SELECT e FROM Entrada e WHERE e.datadeinsercao = :datadeinsercao")})
public class Entrada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datadaentrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datadaentrada;
    @Size(max = 2147483647)
    @Column(name = "observacao")
    private String observacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datadeinsercao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datadeinsercao;
    @JoinTable(name = "entrada_membro", joinColumns = {
        @JoinColumn(name = "entrada_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "membro_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Membro> membroCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entradaId")
    private Collection<Receita> receitaCollection;
    @JoinColumn(name = "situacao_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Situacao situacaoId;

    public Entrada() {
    }

    public Entrada(Integer id) {
        this.id = id;
    }

    public Entrada(Integer id, Date datadaentrada, Date datadeinsercao) {
        this.id = id;
        this.datadaentrada = datadaentrada;
        this.datadeinsercao = datadeinsercao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatadaentrada() {
        return datadaentrada;
    }

    public void setDatadaentrada(Date datadaentrada) {
        this.datadaentrada = datadaentrada;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDatadeinsercao() {
        return datadeinsercao;
    }

    public void setDatadeinsercao(Date datadeinsercao) {
        this.datadeinsercao = datadeinsercao;
    }

    @XmlTransient
    public Collection<Membro> getMembroCollection() {
        return membroCollection;
    }

    public void setMembroCollection(Collection<Membro> membroCollection) {
        this.membroCollection = membroCollection;
    }

    @XmlTransient
    public Collection<Receita> getReceitaCollection() {
        return receitaCollection;
    }

    public void setReceitaCollection(Collection<Receita> receitaCollection) {
        this.receitaCollection = receitaCollection;
    }

    public Situacao getSituacaoId() {
        return situacaoId;
    }

    public void setSituacaoId(Situacao situacaoId) {
        this.situacaoId = situacaoId;
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
        if (!(object instanceof Entrada)) {
            return false;
        }
        Entrada other = (Entrada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.Entrada[ id=" + id + " ]";
    }
    
}
