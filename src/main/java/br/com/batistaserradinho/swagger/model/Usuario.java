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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(catalog = "igreja", schema = "igreja", name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id")
    , @NamedQuery(name = "Usuario.findBySenha", query = "SELECT u FROM Usuario u WHERE u.senha = :senha")
    , @NamedQuery(name = "Usuario.findByDatadeultimoacesso", query = "SELECT u FROM Usuario u WHERE u.datadeultimoacesso = :datadeultimoacesso")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "senha")
    private String senha;
    @Column(name = "datadeultimoacesso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datadeultimoacesso;
    @JoinColumn(name = "membro_id", referencedColumnName = "id")
    @ManyToOne
    private Membro membroId;
    @JoinColumn(name = "situacao_id", referencedColumnName = "id")
    @ManyToOne
    private Situacao situacaoId;

    public Usuario() {
    }

    public Usuario(String id) {
        this.id = id;
    }

    public Usuario(String id, String senha) {
        this.id = id;
        this.senha = senha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDatadeultimoacesso() {
        return datadeultimoacesso;
    }

    public void setDatadeultimoacesso(Date datadeultimoacesso) {
        this.datadeultimoacesso = datadeultimoacesso;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.Usuario[ id=" + id + " ]";
    }
    
}
