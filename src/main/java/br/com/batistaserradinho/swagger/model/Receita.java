/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.swagger.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cruiz
 */
@Entity
@Table( schema = "igreja", name = "receita")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Receita.findAll", query = "SELECT r FROM Receita r")
    , @NamedQuery(name = "Receita.findById", query = "SELECT r FROM Receita r WHERE r.id = :id")
    , @NamedQuery(name = "Receita.findByNome", query = "SELECT r FROM Receita r WHERE r.nome = :nome")
    , @NamedQuery(name = "Receita.findByObservacao", query = "SELECT r FROM Receita r WHERE r.observacao = :observacao")
    , @NamedQuery(name = "Receita.findByImagem", query = "SELECT r FROM Receita r WHERE r.imagem = :imagem")
    , @NamedQuery(name = "Receita.findByValortotal", query = "SELECT r FROM Receita r WHERE r.valortotal = :valortotal")})
public class Receita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "nome")
    private String nome;
    @Size(max = 2147483647)
    @Column(name = "observacao")
    private String observacao;
    @Size(max = 100)
    @Column(name = "imagem")
    private String imagem;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valortotal")
    private BigDecimal valortotal;
    @JoinColumn(name = "entrada_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Entrada entradaId;
    @JoinColumn(name = "especie_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Especie especieId;
    @JoinColumn(name = "membro_id", referencedColumnName = "id")
    @ManyToOne
    private Membro membroId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receitaId")
    private Collection<ReceitaItem> receitaItemCollection;

    public Receita() {
    }

    public Receita(Integer id) {
        this.id = id;
    }

    public Receita(Integer id, BigDecimal valortotal) {
        this.id = id;
        this.valortotal = valortotal;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public BigDecimal getValortotal() {
        return valortotal;
    }

    public void setValortotal(BigDecimal valortotal) {
        this.valortotal = valortotal;
    }

    public Entrada getEntradaId() {
        return entradaId;
    }

    public void setEntradaId(Entrada entradaId) {
        this.entradaId = entradaId;
    }

    public Especie getEspecieId() {
        return especieId;
    }

    public void setEspecieId(Especie especieId) {
        this.especieId = especieId;
    }

    public Membro getMembroId() {
        return membroId;
    }

    public void setMembroId(Membro membroId) {
        this.membroId = membroId;
    }

    @XmlTransient
    public Collection<ReceitaItem> getReceitaItemCollection() {
        return receitaItemCollection;
    }

    public void setReceitaItemCollection(Collection<ReceitaItem> receitaItemCollection) {
        this.receitaItemCollection = receitaItemCollection;
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
        if (!(object instanceof Receita)) {
            return false;
        }
        Receita other = (Receita) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.Receita[ id=" + id + " ]";
    }
    
}
