/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.swagger.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cruiz
 */
@Entity
@Table( schema = "igreja", name = "facilitacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facilitacao.findAll", query = "SELECT f FROM Facilitacao f")
    , @NamedQuery(name = "Facilitacao.findById", query = "SELECT f FROM Facilitacao f WHERE f.id = :id")
    , @NamedQuery(name = "Facilitacao.findByTitulo", query = "SELECT f FROM Facilitacao f WHERE f.titulo = :titulo")
    , @NamedQuery(name = "Facilitacao.findByDatadereferencia", query = "SELECT f FROM Facilitacao f WHERE f.datadereferencia = :datadereferencia")})
public class Facilitacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datadereferencia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datadereferencia;
    @Lob
    @Column(name = "documentoempdf")
    private Byte[] documentoempdf;
    @JoinColumn(name = "membro_id", referencedColumnName = "id")
    @ManyToOne
    private Membro membroId;

    public Facilitacao() {
    }

    public Facilitacao(Integer id) {
        this.id = id;
    }

    public Facilitacao(Integer id, String titulo, Date datadereferencia) {
        this.id = id;
        this.titulo = titulo;
        this.datadereferencia = datadereferencia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDatadereferencia() {
        return datadereferencia;
    }

    public void setDatadereferencia(Date datadereferencia) {
        this.datadereferencia = datadereferencia;
    }

    public Byte[] getDocumentoempdf() {
        return documentoempdf;
    }

    public void setDocumentoempdf(Byte[] documentoempdf) {
        this.documentoempdf = documentoempdf;
    }

    public Membro getMembroId() {
        return membroId;
    }

    public void setMembroId(Membro membroId) {
        this.membroId = membroId;
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
        if (!(object instanceof Facilitacao)) {
            return false;
        }
        Facilitacao other = (Facilitacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.Facilitacao[ id=" + id + " ]";
    }
    
}
