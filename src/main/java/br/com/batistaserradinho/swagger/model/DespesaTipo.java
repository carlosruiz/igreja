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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "despesa_tipo", catalog = "igreja", schema = "igreja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DespesaTipo.findAll", query = "SELECT d FROM DespesaTipo d")
    , @NamedQuery(name = "DespesaTipo.findById", query = "SELECT d FROM DespesaTipo d WHERE d.id = :id")
    , @NamedQuery(name = "DespesaTipo.findByNome", query = "SELECT d FROM DespesaTipo d WHERE d.nome = :nome")})
public class DespesaTipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Size(max = 100)
    @Column(length = 100)
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "despesaTipoId")
    private Collection<Despesa> despesaCollection;

    public DespesaTipo() {
    }

    public DespesaTipo(Integer id) {
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

    @XmlTransient
    public Collection<Despesa> getDespesaCollection() {
        return despesaCollection;
    }

    public void setDespesaCollection(Collection<Despesa> despesaCollection) {
        this.despesaCollection = despesaCollection;
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
        if (!(object instanceof DespesaTipo)) {
            return false;
        }
        DespesaTipo other = (DespesaTipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.DespesaTipo[ id=" + id + " ]";
    }
    
}