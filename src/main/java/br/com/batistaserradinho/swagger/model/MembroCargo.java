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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cruiz
 */
@Entity
@Table(catalog = "igreja", schema = "igreja", name = "membro_cargo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MembroCargo.findAll", query = "SELECT m FROM MembroCargo m")
    , @NamedQuery(name = "MembroCargo.findById", query = "SELECT m FROM MembroCargo m WHERE m.id = :id")
    , @NamedQuery(name = "MembroCargo.findByDatadeentrada", query = "SELECT m FROM MembroCargo m WHERE m.datadeentrada = :datadeentrada")
    , @NamedQuery(name = "MembroCargo.findByDatadesaida", query = "SELECT m FROM MembroCargo m WHERE m.datadesaida = :datadesaida")})
public class MembroCargo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datadeentrada")
    @Temporal(TemporalType.DATE)
    private Date datadeentrada;
    @Column(name = "datadesaida")
    @Temporal(TemporalType.DATE)
    private Date datadesaida;
    @JoinColumn(name = "cargo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cargo cargoId;
    @JoinColumn(name = "membro_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Membro membroId;
    @JoinColumn(name = "situacao_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Situacao situacaoId;

    public MembroCargo() {
    }

    public MembroCargo(Integer id) {
        this.id = id;
    }

    public MembroCargo(Integer id, Date datadeentrada) {
        this.id = id;
        this.datadeentrada = datadeentrada;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatadeentrada() {
        return datadeentrada;
    }

    public void setDatadeentrada(Date datadeentrada) {
        this.datadeentrada = datadeentrada;
    }

    public Date getDatadesaida() {
        return datadesaida;
    }

    public void setDatadesaida(Date datadesaida) {
        this.datadesaida = datadesaida;
    }

    public Cargo getCargoId() {
        return cargoId;
    }

    public void setCargoId(Cargo cargoId) {
        this.cargoId = cargoId;
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
        if (!(object instanceof MembroCargo)) {
            return false;
        }
        MembroCargo other = (MembroCargo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.MembroCargo[ id=" + id + " ]";
    }
    
}
