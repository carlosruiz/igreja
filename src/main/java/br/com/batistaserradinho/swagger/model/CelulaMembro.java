/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.swagger.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cruiz
 */
@Entity
@Table(catalog = "igreja", schema = "igreja", name = "celula_membro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CelulaMembro.findAll", query = "SELECT c FROM CelulaMembro c")
    , @NamedQuery(name = "CelulaMembro.findById", query = "SELECT c FROM CelulaMembro c WHERE c.id = :id")
    , @NamedQuery(name = "CelulaMembro.findByEhlider", query = "SELECT c FROM CelulaMembro c WHERE c.ehlider = :ehlider")
    , @NamedQuery(name = "CelulaMembro.findByEhLiderEmTreinamento", query = "SELECT c FROM CelulaMembro c WHERE c.ehLiderEmTreinamento = :ehLiderEmTreinamento")})
public class CelulaMembro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ehlider")
    private Boolean ehlider;
    @Column(name = "ehLiderEmTreinamento")
    private Boolean ehLiderEmTreinamento;
    @OneToMany(mappedBy = "membrocelulaId")
    private Collection<PrestacaoDeConta> prestacaoDeContaCollection;
    @JoinColumn(name = "celula_id", referencedColumnName = "id")
    @ManyToOne
    private Celula celulaId;
    @JoinColumn(name = "membro_id", referencedColumnName = "id")
    @ManyToOne
    private Membro membroId;
    @JoinColumn(name = "situacao_id", referencedColumnName = "id")
    @ManyToOne
    private Situacao situacaoId;

    public CelulaMembro() {
    }

    public CelulaMembro(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEhlider() {
        return ehlider;
    }

    public void setEhlider(Boolean ehlider) {
        this.ehlider = ehlider;
    }

    public Boolean getEhLiderEmTreinamento() {
        return ehLiderEmTreinamento;
    }

    public void setEhLiderEmTreinamento(Boolean ehLiderEmTreinamento) {
        this.ehLiderEmTreinamento = ehLiderEmTreinamento;
    }

    @XmlTransient
    public Collection<PrestacaoDeConta> getPrestacaoDeContaCollection() {
        return prestacaoDeContaCollection;
    }

    public void setPrestacaoDeContaCollection(Collection<PrestacaoDeConta> prestacaoDeContaCollection) {
        this.prestacaoDeContaCollection = prestacaoDeContaCollection;
    }

    public Celula getCelulaId() {
        return celulaId;
    }

    public void setCelulaId(Celula celulaId) {
        this.celulaId = celulaId;
    }

    public Membro getMembroId() {
        return membroId;
    }

    public void setMembroId(Membro membroId) {
        this.membroId = membroId;
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
        if (!(object instanceof CelulaMembro)) {
            return false;
        }
        CelulaMembro other = (CelulaMembro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.CelulaMembro[ id=" + id + " ]";
    }
    
}
