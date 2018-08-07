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
 * @author Carlos
 */
@Entity
@Table(catalog = "igreja", schema = "igreja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Despesa.findAll", query = "SELECT d FROM Despesa d")
    , @NamedQuery(name = "Despesa.findById", query = "SELECT d FROM Despesa d WHERE d.id = :id")
    , @NamedQuery(name = "Despesa.findByNome", query = "SELECT d FROM Despesa d WHERE d.nome = :nome")
    , @NamedQuery(name = "Despesa.findByValor", query = "SELECT d FROM Despesa d WHERE d.valor = :valor")
    , @NamedQuery(name = "Despesa.findByDatadevencimento", query = "SELECT d FROM Despesa d WHERE d.datadevencimento = :datadevencimento")
    , @NamedQuery(name = "Despesa.findByDatadepagamento", query = "SELECT d FROM Despesa d WHERE d.datadepagamento = :datadepagamento")
    , @NamedQuery(name = "Despesa.findByObservacao", query = "SELECT d FROM Despesa d WHERE d.observacao = :observacao")})
public class Despesa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int valor;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datadevencimento;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datadepagamento;
    @Size(max = 256)
    @Column(length = 256)
    private String observacao;
    @JoinColumn(name = "despesa_tipo_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DespesaTipo despesaTipoId;
    @JoinColumn(name = "especie_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Especie especieId;
    @JoinColumn(name = "membro_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Membro membroId;
    @JoinColumn(name = "situacao_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Situacao situacaoId;

    public Despesa() {
    }

    public Despesa(Integer id) {
        this.id = id;
    }

    public Despesa(Integer id, String nome, int valor, Date datadevencimento) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.datadevencimento = datadevencimento;
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

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Date getDatadevencimento() {
        return datadevencimento;
    }

    public void setDatadevencimento(Date datadevencimento) {
        this.datadevencimento = datadevencimento;
    }

    public Date getDatadepagamento() {
        return datadepagamento;
    }

    public void setDatadepagamento(Date datadepagamento) {
        this.datadepagamento = datadepagamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public DespesaTipo getDespesaTipoId() {
        return despesaTipoId;
    }

    public void setDespesaTipoId(DespesaTipo despesaTipoId) {
        this.despesaTipoId = despesaTipoId;
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
        if (!(object instanceof Despesa)) {
            return false;
        }
        Despesa other = (Despesa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.Despesa[ id=" + id + " ]";
    }
    
}
