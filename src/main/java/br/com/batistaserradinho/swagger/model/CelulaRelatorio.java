/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.swagger.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author cruiz
 */
@Entity
@Table( schema = "igreja", name = "celula_relatorio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CelulaRelatorio.findAll", query = "SELECT c FROM CelulaRelatorio c")
    , @NamedQuery(name = "CelulaRelatorio.findById", query = "SELECT c FROM CelulaRelatorio c WHERE c.id = :id")
    , @NamedQuery(name = "CelulaRelatorio.findByDiscipulador", query = "SELECT c FROM CelulaRelatorio c WHERE c.discipulador = :discipulador")
    , @NamedQuery(name = "CelulaRelatorio.findByDatadereuniao", query = "SELECT c FROM CelulaRelatorio c WHERE c.datadereuniao = :datadereuniao")
    , @NamedQuery(name = "CelulaRelatorio.findByValordaoferta", query = "SELECT c FROM CelulaRelatorio c WHERE c.valordaoferta = :valordaoferta")
    , @NamedQuery(name = "CelulaRelatorio.findByTotadepresentes", query = "SELECT c FROM CelulaRelatorio c WHERE c.totadepresentes = :totadepresentes")
    , @NamedQuery(name = "CelulaRelatorio.findByTotaldemembrospresentes", query = "SELECT c FROM CelulaRelatorio c WHERE c.totaldemembrospresentes = :totaldemembrospresentes")
    , @NamedQuery(name = "CelulaRelatorio.findByTotaldefrequentadoresassiduos", query = "SELECT c FROM CelulaRelatorio c WHERE c.totaldefrequentadoresassiduos = :totaldefrequentadoresassiduos")
    , @NamedQuery(name = "CelulaRelatorio.findByTotaldevisitantes", query = "SELECT c FROM CelulaRelatorio c WHERE c.totaldevisitantes = :totaldevisitantes")
    , @NamedQuery(name = "CelulaRelatorio.findByTotaldecriancas", query = "SELECT c FROM CelulaRelatorio c WHERE c.totaldecriancas = :totaldecriancas")
    , @NamedQuery(name = "CelulaRelatorio.findByQuantidadedevisitas", query = "SELECT c FROM CelulaRelatorio c WHERE c.quantidadedevisitas = :quantidadedevisitas")})
public class CelulaRelatorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "discipulador")
    private String discipulador;
    @Column(name = "datadereuniao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datadereuniao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valordaoferta")
    private BigDecimal valordaoferta;
    @Column(name = "totadepresentes")
    private Integer totadepresentes;
    @Column(name = "totaldemembrospresentes")
    private Integer totaldemembrospresentes;
    @Column(name = "totaldefrequentadoresassiduos")
    private Integer totaldefrequentadoresassiduos;
    @Column(name = "totaldevisitantes")
    private Integer totaldevisitantes;
    @Column(name = "totaldecriancas")
    private Integer totaldecriancas;
    @Column(name = "quantidadedevisitas")
    private Integer quantidadedevisitas;
    @OneToMany(mappedBy = "relatoriocelulaId")
    private Collection<PrestacaoDeConta> prestacaoDeContaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "relatoriocelulaId")
    private Collection<RelatoDaVisita> relatoDaVisitaCollection;
    @JoinColumn(name = "celula_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Celula celulaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "celulaRelatorioId")
    private Collection<RelatorioCriancasPresentes> relatorioCriancasPresentesCollection;

    public CelulaRelatorio() {
    }

    public CelulaRelatorio(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiscipulador() {
        return discipulador;
    }

    public void setDiscipulador(String discipulador) {
        this.discipulador = discipulador;
    }

    public Date getDatadereuniao() {
        return datadereuniao;
    }

    public void setDatadereuniao(Date datadereuniao) {
        this.datadereuniao = datadereuniao;
    }

    public BigDecimal getValordaoferta() {
        return valordaoferta;
    }

    public void setValordaoferta(BigDecimal valordaoferta) {
        this.valordaoferta = valordaoferta;
    }

    public Integer getTotadepresentes() {
        return totadepresentes;
    }

    public void setTotadepresentes(Integer totadepresentes) {
        this.totadepresentes = totadepresentes;
    }

    public Integer getTotaldemembrospresentes() {
        return totaldemembrospresentes;
    }

    public void setTotaldemembrospresentes(Integer totaldemembrospresentes) {
        this.totaldemembrospresentes = totaldemembrospresentes;
    }

    public Integer getTotaldefrequentadoresassiduos() {
        return totaldefrequentadoresassiduos;
    }

    public void setTotaldefrequentadoresassiduos(Integer totaldefrequentadoresassiduos) {
        this.totaldefrequentadoresassiduos = totaldefrequentadoresassiduos;
    }

    public Integer getTotaldevisitantes() {
        return totaldevisitantes;
    }

    public void setTotaldevisitantes(Integer totaldevisitantes) {
        this.totaldevisitantes = totaldevisitantes;
    }

    public Integer getTotaldecriancas() {
        return totaldecriancas;
    }

    public void setTotaldecriancas(Integer totaldecriancas) {
        this.totaldecriancas = totaldecriancas;
    }

    public Integer getQuantidadedevisitas() {
        return quantidadedevisitas;
    }

    public void setQuantidadedevisitas(Integer quantidadedevisitas) {
        this.quantidadedevisitas = quantidadedevisitas;
    }

    @XmlTransient
    public Collection<PrestacaoDeConta> getPrestacaoDeContaCollection() {
        return prestacaoDeContaCollection;
    }

    public void setPrestacaoDeContaCollection(Collection<PrestacaoDeConta> prestacaoDeContaCollection) {
        this.prestacaoDeContaCollection = prestacaoDeContaCollection;
    }

    @XmlTransient
    public Collection<RelatoDaVisita> getRelatoDaVisitaCollection() {
        return relatoDaVisitaCollection;
    }

    public void setRelatoDaVisitaCollection(Collection<RelatoDaVisita> relatoDaVisitaCollection) {
        this.relatoDaVisitaCollection = relatoDaVisitaCollection;
    }

    public Celula getCelulaId() {
        return celulaId;
    }

    public void setCelulaId(Celula celulaId) {
        this.celulaId = celulaId;
    }

    @XmlTransient
    public Collection<RelatorioCriancasPresentes> getRelatorioCriancasPresentesCollection() {
        return relatorioCriancasPresentesCollection;
    }

    public void setRelatorioCriancasPresentesCollection(Collection<RelatorioCriancasPresentes> relatorioCriancasPresentesCollection) {
        this.relatorioCriancasPresentesCollection = relatorioCriancasPresentesCollection;
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
        if (!(object instanceof CelulaRelatorio)) {
            return false;
        }
        CelulaRelatorio other = (CelulaRelatorio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.CelulaRelatorio[ id=" + id + " ]";
    }
    
}
