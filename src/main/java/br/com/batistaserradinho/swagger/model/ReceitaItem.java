/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.swagger.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "receita_item", catalog = "igreja", schema = "igreja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReceitaItem.findAll", query = "SELECT r FROM ReceitaItem r")
    , @NamedQuery(name = "ReceitaItem.findById", query = "SELECT r FROM ReceitaItem r WHERE r.id = :id")
    , @NamedQuery(name = "ReceitaItem.findByValor", query = "SELECT r FROM ReceitaItem r WHERE r.valor = :valor")})
public class ReceitaItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;
    @JoinColumn(name = "receita_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Receita receitaId;
    @JoinColumn(name = "receita_tipo_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private ReceitaTipo receitaTipoId;

    public ReceitaItem() {
    }

    public ReceitaItem(Integer id) {
        this.id = id;
    }

    public ReceitaItem(Integer id, BigDecimal valor) {
        this.id = id;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Receita getReceitaId() {
        return receitaId;
    }

    public void setReceitaId(Receita receitaId) {
        this.receitaId = receitaId;
    }

    public ReceitaTipo getReceitaTipoId() {
        return receitaTipoId;
    }

    public void setReceitaTipoId(ReceitaTipo receitaTipoId) {
        this.receitaTipoId = receitaTipoId;
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
        if (!(object instanceof ReceitaItem)) {
            return false;
        }
        ReceitaItem other = (ReceitaItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.ReceitaItem[ id=" + id + " ]";
    }
    
}
