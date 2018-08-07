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
@Table(catalog = "igreja", schema = "igreja", name = "inscricao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inscricao.findAll", query = "SELECT i FROM Inscricao i")
    , @NamedQuery(name = "Inscricao.findById", query = "SELECT i FROM Inscricao i WHERE i.id = :id")
    , @NamedQuery(name = "Inscricao.findByNome", query = "SELECT i FROM Inscricao i WHERE i.nome = :nome")
    , @NamedQuery(name = "Inscricao.findByDocumento", query = "SELECT i FROM Inscricao i WHERE i.documento = :documento")
    , @NamedQuery(name = "Inscricao.findByTelefone", query = "SELECT i FROM Inscricao i WHERE i.telefone = :telefone")
    , @NamedQuery(name = "Inscricao.findByPagamento", query = "SELECT i FROM Inscricao i WHERE i.pagamento = :pagamento")})
public class Inscricao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "nome")
    private String nome;
    @Size(max = 2147483647)
    @Column(name = "documento")
    private String documento;
    @Size(max = 20)
    @Column(name = "telefone")
    private String telefone;
    @Size(max = 200)
    @Column(name = "pagamento")
    private String pagamento;
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Evento eventoId;

    public Inscricao() {
    }

    public Inscricao(Integer id) {
        this.id = id;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public Evento getEventoId() {
        return eventoId;
    }

    public void setEventoId(Evento eventoId) {
        this.eventoId = eventoId;
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
        if (!(object instanceof Inscricao)) {
            return false;
        }
        Inscricao other = (Inscricao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.Inscricao[ id=" + id + " ]";
    }
    
}
