/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.swagger.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cruiz
 */
@Entity
@Table(catalog = "igreja", schema = "igreja", name = "situacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Situacao.findAll", query = "SELECT s FROM Situacao s")
    , @NamedQuery(name = "Situacao.findById", query = "SELECT s FROM Situacao s WHERE s.id = :id")
    , @NamedQuery(name = "Situacao.findByNome", query = "SELECT s FROM Situacao s WHERE s.nome = :nome")})
public class Situacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "nome")
    private String nome;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "situacao")
    private Setor setor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "situacaoId")
    private Collection<Despesa> despesaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "situacaoId")
    private Collection<MembroCargo> membroCargoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "situacaoId")
    private Collection<Especie> especieCollection;
    @OneToMany(mappedBy = "situacaoId")
    private Collection<Celula> celulaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "situacaoId")
    private Collection<Evento> eventoCollection;
    @OneToMany(mappedBy = "situacaoId")
    private Collection<CelulaMembro> celulaMembroCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "situacaoId")
    private Collection<Cargo> cargoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "situacaoId")
    private Collection<Ministerio> ministerioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "situacaoId")
    private Collection<Membro> membroCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "situacaoId")
    private Collection<ReceitaTipo> receitaTipoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "situacaoId")
    private Collection<Entrada> entradaCollection;
    @OneToMany(mappedBy = "situacaoId")
    private Collection<Usuario> usuarioCollection;

    public Situacao() {
    }

    public Situacao(Integer id) {
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

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    @XmlTransient
    public Collection<Despesa> getDespesaCollection() {
        return despesaCollection;
    }

    public void setDespesaCollection(Collection<Despesa> despesaCollection) {
        this.despesaCollection = despesaCollection;
    }

    @XmlTransient
    public Collection<MembroCargo> getMembroCargoCollection() {
        return membroCargoCollection;
    }

    public void setMembroCargoCollection(Collection<MembroCargo> membroCargoCollection) {
        this.membroCargoCollection = membroCargoCollection;
    }

    @XmlTransient
    public Collection<Especie> getEspecieCollection() {
        return especieCollection;
    }

    public void setEspecieCollection(Collection<Especie> especieCollection) {
        this.especieCollection = especieCollection;
    }

    @XmlTransient
    public Collection<Celula> getCelulaCollection() {
        return celulaCollection;
    }

    public void setCelulaCollection(Collection<Celula> celulaCollection) {
        this.celulaCollection = celulaCollection;
    }

    @XmlTransient
    public Collection<Evento> getEventoCollection() {
        return eventoCollection;
    }

    public void setEventoCollection(Collection<Evento> eventoCollection) {
        this.eventoCollection = eventoCollection;
    }

    @XmlTransient
    public Collection<CelulaMembro> getCelulaMembroCollection() {
        return celulaMembroCollection;
    }

    public void setCelulaMembroCollection(Collection<CelulaMembro> celulaMembroCollection) {
        this.celulaMembroCollection = celulaMembroCollection;
    }

    @XmlTransient
    public Collection<Cargo> getCargoCollection() {
        return cargoCollection;
    }

    public void setCargoCollection(Collection<Cargo> cargoCollection) {
        this.cargoCollection = cargoCollection;
    }

    @XmlTransient
    public Collection<Ministerio> getMinisterioCollection() {
        return ministerioCollection;
    }

    public void setMinisterioCollection(Collection<Ministerio> ministerioCollection) {
        this.ministerioCollection = ministerioCollection;
    }

    @XmlTransient
    public Collection<Membro> getMembroCollection() {
        return membroCollection;
    }

    public void setMembroCollection(Collection<Membro> membroCollection) {
        this.membroCollection = membroCollection;
    }

    @XmlTransient
    public Collection<ReceitaTipo> getReceitaTipoCollection() {
        return receitaTipoCollection;
    }

    public void setReceitaTipoCollection(Collection<ReceitaTipo> receitaTipoCollection) {
        this.receitaTipoCollection = receitaTipoCollection;
    }

    @XmlTransient
    public Collection<Entrada> getEntradaCollection() {
        return entradaCollection;
    }

    public void setEntradaCollection(Collection<Entrada> entradaCollection) {
        this.entradaCollection = entradaCollection;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
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
        if (!(object instanceof Situacao)) {
            return false;
        }
        Situacao other = (Situacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.Situacao[ id=" + id + " ]";
    }
    
}
