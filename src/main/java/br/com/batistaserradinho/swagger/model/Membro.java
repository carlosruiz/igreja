/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.swagger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos
 */
@Entity
@Table(catalog = "igreja", schema = "igreja", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})
    , @UniqueConstraint(columnNames = {"login"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Membro.findAll", query = "SELECT m FROM Membro m")
    , @NamedQuery(name = "Membro.findById", query = "SELECT m FROM Membro m WHERE m.id = :id")
    , @NamedQuery(name = "Membro.findByNome", query = "SELECT m FROM Membro m WHERE m.nome = :nome")
    , @NamedQuery(name = "Membro.findByRg", query = "SELECT m FROM Membro m WHERE m.rg = :rg")
    , @NamedQuery(name = "Membro.findByCpf", query = "SELECT m FROM Membro m WHERE m.cpf = :cpf")
    , @NamedQuery(name = "Membro.findBySexo", query = "SELECT m FROM Membro m WHERE m.sexo = :sexo")
    , @NamedQuery(name = "Membro.findByDatadenascimento", query = "SELECT m FROM Membro m WHERE m.datadenascimento = :datadenascimento")
    , @NamedQuery(name = "Membro.findByEstadocivil", query = "SELECT m FROM Membro m WHERE m.estadocivil = :estadocivil")
    , @NamedQuery(name = "Membro.findByEndereco", query = "SELECT m FROM Membro m WHERE m.endereco = :endereco")
    , @NamedQuery(name = "Membro.findByBairro", query = "SELECT m FROM Membro m WHERE m.bairro = :bairro")
    , @NamedQuery(name = "Membro.findByCep", query = "SELECT m FROM Membro m WHERE m.cep = :cep")
    , @NamedQuery(name = "Membro.findByCidade", query = "SELECT m FROM Membro m WHERE m.cidade = :cidade")
    , @NamedQuery(name = "Membro.findByUf", query = "SELECT m FROM Membro m WHERE m.uf = :uf")
    , @NamedQuery(name = "Membro.findByPais", query = "SELECT m FROM Membro m WHERE m.pais = :pais")
    , @NamedQuery(name = "Membro.findByTelefone", query = "SELECT m FROM Membro m WHERE m.telefone = :telefone")
    , @NamedQuery(name = "Membro.findByCelular", query = "SELECT m FROM Membro m WHERE m.celular = :celular")
    , @NamedQuery(name = "Membro.findByEmail", query = "SELECT m FROM Membro m WHERE m.email = :email")
    , @NamedQuery(name = "Membro.findByDatadeentrada", query = "SELECT m FROM Membro m WHERE m.datadeentrada = :datadeentrada")
    , @NamedQuery(name = "Membro.findByLogin", query = "SELECT m FROM Membro m WHERE m.login = :login")
    , @NamedQuery(name = "Membro.findBySenha", query = "SELECT m FROM Membro m WHERE m.senha = :senha")
    , @NamedQuery(name = "Membro.findByDatadoultimoacesso", query = "SELECT m FROM Membro m WHERE m.datadoultimoacesso = :datadoultimoacesso")
    , @NamedQuery(name = "Membro.findByDatadecancelamento", query = "SELECT m FROM Membro m WHERE m.datadecancelamento = :datadecancelamento")
    , @NamedQuery(name = "Membro.findByMotivocancelamento", query = "SELECT m FROM Membro m WHERE m.motivocancelamento = :motivocancelamento")})
public class Membro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String nome;
    @Size(max = 30)
    @Column(length = 30)
    private String rg;
    @Size(max = 14)
    @Column(length = 14)
    private String cpf;
    @Size(max = 10)
    @Column(length = 10)
    private String sexo;
    @Temporal(TemporalType.DATE)
    private Date datadenascimento;
    @Size(max = 30)
    @Column(length = 30)
    private String estadocivil;
    @Size(max = 60)
    @Column(length = 60)
    private String endereco;
    @Size(max = 30)
    @Column(length = 30)
    private String bairro;
    @Size(max = 9)
    @Column(length = 9)
    private String cep;
    @Size(max = 100)
    @Column(length = 100)
    private String cidade;
    @Size(max = 2)
    @Column(length = 2)
    private String uf;
    @Size(max = 60)
    @Column(length = 60)
    private String pais;
    @Size(max = 14)
    @Column(length = 14)
    private String telefone;
    @Size(max = 14)
    @Column(length = 14)
    private String celular;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(length = 50)
    private String email;
    @Temporal(TemporalType.DATE)
    private Date datadeentrada;
    @Lob
    private Byte[] foto;
    @Size(max = 20)
    @Column(length = 20)
    private String login;
    @Size(max = 128)
    @Column(length = 128)
    private String senha;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datadoultimoacesso;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datadecancelamento;
    @Size(max = 100)
    @Column(length = 100)
    private String motivocancelamento;
    @ManyToMany(mappedBy = "membroCollection")
    private Collection<Entrada> entradaCollection;
    @OneToMany(mappedBy = "lider")
    private Collection<Setor> setorCollection;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membroId")
    private Collection<Despesa> despesaCollection;
    @JsonIgnore
    @OneToMany(mappedBy = "membroId")
    private Collection<Facilitacao> facilitacaoCollection;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membroId")
    private Collection<MembroCargo> membroCargoCollection;
    @JsonIgnore
    @OneToMany(mappedBy = "membroId")
    private Collection<Evento> eventoCollection;
    @JsonIgnore
    @OneToMany(mappedBy = "membroId")
    private Collection<CelulaMembro> celulaMembroCollection;
    @JsonIgnore
    @OneToMany(mappedBy = "membroId")
    private Collection<Receita> receitaCollection;
    @JoinColumn(name = "membro_formadeentrada_id", referencedColumnName = "id")
    @ManyToOne
    private MembroFormaDeEntrada membroFormadeentradaId;
    @JoinColumn(name = "membro_tipo_id", referencedColumnName = "id")
    @ManyToOne
    private MembroTipo membroTipoId;
    @JoinColumn(name = "situacao_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Situacao situacaoId;

    public Membro() {
    }

    public Membro(Integer id) {
        this.id = id;
    }

    public Membro(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDatadenascimento() {
        return datadenascimento;
    }

    public void setDatadenascimento(Date datadenascimento) {
        this.datadenascimento = datadenascimento;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDatadeentrada() {
        return datadeentrada;
    }

    public void setDatadeentrada(Date datadeentrada) {
        this.datadeentrada = datadeentrada;
    }

    public Byte[] getFoto() {
        return foto;
    }

    public void setFoto(Byte[] foto) {
        this.foto = foto;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDatadoultimoacesso() {
        return datadoultimoacesso;
    }

    public void setDatadoultimoacesso(Date datadoultimoacesso) {
        this.datadoultimoacesso = datadoultimoacesso;
    }

    public Date getDatadecancelamento() {
        return datadecancelamento;
    }

    public void setDatadecancelamento(Date datadecancelamento) {
        this.datadecancelamento = datadecancelamento;
    }

    public String getMotivocancelamento() {
        return motivocancelamento;
    }

    public void setMotivocancelamento(String motivocancelamento) {
        this.motivocancelamento = motivocancelamento;
    }

    @XmlTransient
    public Collection<Entrada> getEntradaCollection() {
        return entradaCollection;
    }

    public void setEntradaCollection(Collection<Entrada> entradaCollection) {
        this.entradaCollection = entradaCollection;
    }

    @XmlTransient
    public Collection<Setor> getSetorCollection() {
        return setorCollection;
    }

    public void setSetorCollection(Collection<Setor> setorCollection) {
        this.setorCollection = setorCollection;
    }

    @XmlTransient
    public Collection<Despesa> getDespesaCollection() {
        return despesaCollection;
    }

    public void setDespesaCollection(Collection<Despesa> despesaCollection) {
        this.despesaCollection = despesaCollection;
    }

    @XmlTransient
    public Collection<Facilitacao> getFacilitacaoCollection() {
        return facilitacaoCollection;
    }

    public void setFacilitacaoCollection(Collection<Facilitacao> facilitacaoCollection) {
        this.facilitacaoCollection = facilitacaoCollection;
    }

    @XmlTransient
    public Collection<MembroCargo> getMembroCargoCollection() {
        return membroCargoCollection;
    }

    public void setMembroCargoCollection(Collection<MembroCargo> membroCargoCollection) {
        this.membroCargoCollection = membroCargoCollection;
    }

    @XmlTransient
    public Collection<Evento> getEventoCollection() {
        return eventoCollection;
    }

    public void setEventoCollection(Collection<Evento> eventoCollection) {
        this.eventoCollection = eventoCollection;
    }

    @XmlTransient
    public Collection<CelulaMembro> getCelulaMembroCollection() {
        return celulaMembroCollection;
    }

    public void setCelulaMembroCollection(Collection<CelulaMembro> celulaMembroCollection) {
        this.celulaMembroCollection = celulaMembroCollection;
    }

    @XmlTransient
    public Collection<Receita> getReceitaCollection() {
        return receitaCollection;
    }

    public void setReceitaCollection(Collection<Receita> receitaCollection) {
        this.receitaCollection = receitaCollection;
    }

    public MembroFormaDeEntrada getMembroFormadeentradaId() {
        return membroFormadeentradaId;
    }

    public void setMembroFormadeentradaId(MembroFormaDeEntrada membroFormadeentradaId) {
        this.membroFormadeentradaId = membroFormadeentradaId;
    }

    public MembroTipo getMembroTipoId() {
        return membroTipoId;
    }

    public void setMembroTipoId(MembroTipo membroTipoId) {
        this.membroTipoId = membroTipoId;
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
        if (!(object instanceof Membro)) {
            return false;
        }
        Membro other = (Membro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.batistaserradinho.swagger.model.Membro[ id=" + id + " ]";
    }
    
}
