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
 * @author Carlos
 */
@Entity
@Table(catalog = "igreja", schema = "igreja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LeituraDiaria.findAll", query = "SELECT l FROM LeituraDiaria l")
    , @NamedQuery(name = "LeituraDiaria.findByData", query = "SELECT l FROM LeituraDiaria l WHERE l.data = :data")
    , @NamedQuery(name = "LeituraDiaria.findByReferencia", query = "SELECT l FROM LeituraDiaria l WHERE l.referencia = :referencia")
    , @NamedQuery(name = "LeituraDiaria.findByVersiculo", query = "SELECT l FROM LeituraDiaria l WHERE l.versiculo = :versiculo")})
public class LeituraDiaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String referencia;
    @Size(max = 500)
    @Column(length = 500)
    private String versiculo;

    public LeituraDiaria() {
    }

    public LeituraDiaria(Date data) {
        this.data = data;
    }

    public LeituraDiaria(Date data, String referencia) {
        this.data = data;
        this.referencia = referencia;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getVersiculo() {
        return versiculo;
    }

    public void setVersiculo(String versiculo) {
        this.versiculo = versiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (data != null ? data.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LeituraDiaria)) {
            return false;
        }
        LeituraDiaria other = (LeituraDiaria) object;
        if ((this.data == null && other.data != null) || (this.data != null && !this.data.equals(other.data))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.LeituraDiaria[ data=" + data + " ]";
    }
    
}
