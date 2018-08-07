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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
 * @author Carlos
 */
@Entity
@Table(catalog = "igreja", schema = "igreja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Celula.findAll", query = "SELECT c FROM Celula c")
    , @NamedQuery(name = "Celula.findById", query = "SELECT c FROM Celula c WHERE c.id = :id")
    , @NamedQuery(name = "Celula.findByNome", query = "SELECT c FROM Celula c WHERE c.nome = :nome")
    , @NamedQuery(name = "Celula.findByEndereco", query = "SELECT c FROM Celula c WHERE c.endereco = :endereco")
    , @NamedQuery(name = "Celula.findByDiadereuniao", query = "SELECT c FROM Celula c WHERE c.diadereuniao = :diadereuniao")
    , @NamedQuery(name = "Celula.findByHoradereuniao", query = "SELECT c FROM Celula c WHERE c.horadereuniao = :horadereuniao")})
public class Celula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String nome;
    @Size(max = 256)
    @Column(length = 256)
    private String endereco;
    @Size(max = 100)
    @Column(length = 100)
    private String diadereuniao;
    @Temporal(TemporalType.TIME)
    private Date horadereuniao;
    @JoinColumn(name = "setor_id", referencedColumnName = "id")
    @ManyToOne
    private Setor setorId;
    @JoinColumn(name = "situacao_id", referencedColumnName = "id")
    @ManyToOne
    private Situacao situacaoId;
    @OneToMany(mappedBy = "celulaId")
    private Collection<CelulaMembro> celulaMembroCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "celulaId")
    private Collection<CelulaRelatorio> celulaRelatorioCollection;

    public Celula() {
    }

    public Celula(Integer id) {
        this.id = id;
    }

    public Celula(Integer id, String nome) {
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDiadereuniao() {
        return diadereuniao;
    }

    public void setDiadereuniao(String diadereuniao) {
        this.diadereuniao = diadereuniao;
    }

    public Date getHoradereuniao() {
        return horadereuniao;
    }

    public void setHoradereuniao(Date horadereuniao) {
        this.horadereuniao = horadereuniao;
    }

    public Setor getSetorId() {
        return setorId;
    }

    public void setSetorId(Setor setorId) {
        this.setorId = setorId;
    }

    public Situacao getSituacaoId() {
        return situacaoId;
    }

    public void setSituacaoId(Situacao situacaoId) {
        this.situacaoId = situacaoId;
    }

    @XmlTransient
    public Collection<CelulaMembro> getCelulaMembroCollection() {
        return celulaMembroCollection;
    }

    public void setCelulaMembroCollection(Collection<CelulaMembro> celulaMembroCollection) {
        this.celulaMembroCollection = celulaMembroCollection;
    }

    @XmlTransient
    public Collection<CelulaRelatorio> getCelulaRelatorioCollection() {
        return celulaRelatorioCollection;
    }

    public void setCelulaRelatorioCollection(Collection<CelulaRelatorio> celulaRelatorioCollection) {
        this.celulaRelatorioCollection = celulaRelatorioCollection;
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
        if (!(object instanceof Celula)) {
            return false;
        }
        Celula other = (Celula) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.Celula[ id=" + id + " ]";
    }
    
}
