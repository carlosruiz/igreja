/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.swagger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e")
    , @NamedQuery(name = "Evento.findById", query = "SELECT e FROM Evento e WHERE e.id = :id")
    , @NamedQuery(name = "Evento.findByNome", query = "SELECT e FROM Evento e WHERE e.nome = :nome")
    , @NamedQuery(name = "Evento.findByDatadeinicio", query = "SELECT e FROM Evento e WHERE e.datadeinicio = :datadeinicio")
    , @NamedQuery(name = "Evento.findByDatadetermino", query = "SELECT e FROM Evento e WHERE e.datadetermino = :datadetermino")
    , @NamedQuery(name = "Evento.findByDescricao", query = "SELECT e FROM Evento e WHERE e.descricao = :descricao")})
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Size(max = 100)
    @Column(length = 100)
    private String nome;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datadeinicio;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datadetermino;
    @Size(max = 256)
    @Column(length = 256)
    private String descricao;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventoId")
    private Collection<Inscricao> inscricaoCollection;
    @JsonIgnore
    @JoinColumn(name = "membro_id", referencedColumnName = "id")
    @ManyToOne
    private Membro membroId;
    @JoinColumn(name = "situacao_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Situacao situacaoId;

    public Evento() {
    }

    public Evento(Integer id) {
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

    public Date getDatadeinicio() {
        return datadeinicio;
    }

    public void setDatadeinicio(Date datadeinicio) {
        this.datadeinicio = datadeinicio;
    }

    public Date getDatadetermino() {
        return datadetermino;
    }

    public void setDatadetermino(Date datadetermino) {
        this.datadetermino = datadetermino;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<Inscricao> getInscricaoCollection() {
        return inscricaoCollection;
    }

    public void setInscricaoCollection(Collection<Inscricao> inscricaoCollection) {
        this.inscricaoCollection = inscricaoCollection;
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
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.Evento[ id=" + id + " ]";
    }
    
}
