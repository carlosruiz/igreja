/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.swagger.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cruiz
 */
@Entity
@Table(catalog = "igreja", schema = "igreja", name = "acesso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acesso.findAll", query = "SELECT a FROM Acesso a")
    , @NamedQuery(name = "Acesso.findById", query = "SELECT a FROM Acesso a WHERE a.id = :id")
    , @NamedQuery(name = "Acesso.findByToken", query = "SELECT a FROM Acesso a WHERE a.token = :token")
    , @NamedQuery(name = "Acesso.findByDescricao", query = "SELECT a FROM Acesso a WHERE a.descricao = :descricao")})
public class Acesso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "token")
    private String token;
    @Size(max = 100)
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "cargo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cargo cargoId;

    public Acesso() {
    }

    public Acesso(Integer id) {
        this.id = id;
    }

    public Acesso(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Cargo getCargoId() {
        return cargoId;
    }

    public void setCargoId(Cargo cargoId) {
        this.cargoId = cargoId;
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
        if (!(object instanceof Acesso)) {
            return false;
        }
        Acesso other = (Acesso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.Acesso[ id=" + id + " ]";
    }
    
}
