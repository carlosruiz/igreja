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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(catalog = "igreja", schema = "igreja", name = "receita_tipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReceitaTipo.findAll", query = "SELECT r FROM ReceitaTipo r")
    , @NamedQuery(name = "ReceitaTipo.findById", query = "SELECT r FROM ReceitaTipo r WHERE r.id = :id")
    , @NamedQuery(name = "ReceitaTipo.findByNome", query = "SELECT r FROM ReceitaTipo r WHERE r.nome = :nome")})
public class ReceitaTipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receitaTipoId")
    private Collection<ReceitaItem> receitaItemCollection;
    @JoinColumn(name = "situacao_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Situacao situacaoId;

    public ReceitaTipo() {
    }

    public ReceitaTipo(Integer id) {
        this.id = id;
    }

    public ReceitaTipo(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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
    public Collection<ReceitaItem> getReceitaItemCollection() {
        return receitaItemCollection;
    }

    public void setReceitaItemCollection(Collection<ReceitaItem> receitaItemCollection) {
        this.receitaItemCollection = receitaItemCollection;
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
        if (!(object instanceof ReceitaTipo)) {
            return false;
        }
        ReceitaTipo other = (ReceitaTipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.ReceitaTipo[ id=" + id + " ]";
    }
    
}
