/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.swagger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cruiz
 */
@Entity
@Table( schema = "igreja", name = "membro_formadeentrada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MembroFormaDeEntrada.findAll", query = "SELECT m FROM MembroFormaDeEntrada m")
    , @NamedQuery(name = "MembroFormaDeEntrada.findById", query = "SELECT m FROM MembroFormaDeEntrada m WHERE m.id = :id")
    , @NamedQuery(name = "MembroFormaDeEntrada.findByDescricao", query = "SELECT m FROM MembroFormaDeEntrada m WHERE m.descricao = :descricao")})
public class MembroFormaDeEntrada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "descricao")
    private String descricao;
    @JsonIgnore
    @OneToMany(mappedBy = "membroFormadeentradaId")
    private Collection<Membro> membroCollection;

    public MembroFormaDeEntrada() {
    }

    public MembroFormaDeEntrada(Integer id) {
        this.id = id;
    }

    public MembroFormaDeEntrada(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
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

    @XmlTransient
    public Collection<Membro> getMembroCollection() {
        return membroCollection;
    }

    public void setMembroCollection(Collection<Membro> membroCollection) {
        this.membroCollection = membroCollection;
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
        if (!(object instanceof MembroFormaDeEntrada)) {
            return false;
        }
        MembroFormaDeEntrada other = (MembroFormaDeEntrada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.MembroFormaDeEntrada[ id=" + id + " ]";
    }
    
}
