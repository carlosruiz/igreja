/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.EnvelopeJson;

import java.util.Date;

/**
 *
 * @author Carlos
 */
public class EntradaEnvelopeJson {
    private Date dataEntrada;
    private Date dataDeInsercao;
    private String observacao;
    private Integer[] membroId;
    private String token;

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataDeInsercao() {
        return dataDeInsercao;
    }

    public void setDataDeInsercao(Date dataDeInsercao) {
        this.dataDeInsercao = dataDeInsercao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer[] getMembroId() {
        return membroId;
    }

    public void setMembroId(Integer[] membroId) {
        this.membroId = membroId;
    }
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
