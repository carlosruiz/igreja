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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cruiz
 */
@Entity
@Table( schema = "igreja", name = "prestacaodeconta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrestacaoDeConta.findAll", query = "SELECT p FROM PrestacaoDeConta p")
    , @NamedQuery(name = "PrestacaoDeConta.findById", query = "SELECT p FROM PrestacaoDeConta p WHERE p.id = :id")
    , @NamedQuery(name = "PrestacaoDeConta.findByMotivodafalta", query = "SELECT p FROM PrestacaoDeConta p WHERE p.motivodafalta = :motivodafalta")})
public class PrestacaoDeConta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 256)
    @Column(name = "motivodafalta")
    private String motivodafalta;
    @JoinColumn(name = "membrocelula_id", referencedColumnName = "id")
    @ManyToOne
    private CelulaMembro membrocelulaId;
    @JoinColumn(name = "relatoriocelula_id", referencedColumnName = "id")
    @ManyToOne
    private CelulaRelatorio relatoriocelulaId;

    public PrestacaoDeConta() {
    }

    public PrestacaoDeConta(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMotivodafalta() {
        return motivodafalta;
    }

    public void setMotivodafalta(String motivodafalta) {
        this.motivodafalta = motivodafalta;
    }

    public CelulaMembro getMembrocelulaId() {
        return membrocelulaId;
    }

    public void setMembrocelulaId(CelulaMembro membrocelulaId) {
        this.membrocelulaId = membrocelulaId;
    }

    public CelulaRelatorio getRelatoriocelulaId() {
        return relatoriocelulaId;
    }

    public void setRelatoriocelulaId(CelulaRelatorio relatoriocelulaId) {
        this.relatoriocelulaId = relatoriocelulaId;
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
        if (!(object instanceof PrestacaoDeConta)) {
            return false;
        }
        PrestacaoDeConta other = (PrestacaoDeConta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.PrestacaoDeConta[ id=" + id + " ]";
    }
    
}
