/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.EnvelopeJson;

import java.math.BigDecimal;

/**
 *
 * @author Carlos
 */
public class ReceitaEnvelopeJson {

    public class ReceitaEnvelope {

        private int entradaId;
        private String nome;
        private int membroId;
        private int especieId;
        private String observacao;
        private BigDecimal valorTotal;
        private int receitaTipoId;
        private ReceitaItemEnvelope[] itens;
        private String token;

        public ReceitaItemEnvelope[] getItens() {
            return itens;
        }

        public void setItens(ReceitaItemEnvelope[] itens) {
            this.itens = itens;
        }

        public int getEntradaId() {
            return entradaId;
        }

        public void setEntradaId(int entradaId) {
            this.entradaId = entradaId;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public int getMembroId() {
            return membroId;
        }

        public void setMembroId(int membroId) {
            this.membroId = membroId;
        }

        public int getEspecieId() {
            return especieId;
        }

        public void setEspecieId(int especieId) {
            this.especieId = especieId;
        }

        public String getObservacao() {
            return observacao;
        }

        public void setObservacao(String observacao) {
            this.observacao = observacao;
        }

        public BigDecimal getValorTotal() {
            return valorTotal;
        }

        public void setValorTotal(BigDecimal valorTotal) {
            this.valorTotal = valorTotal;
        }

        public int getReceitaTipoId() {
            return receitaTipoId;
        }

        public void setReceitaTipoId(int receitaTipoId) {
            this.receitaTipoId = receitaTipoId;
        }
            
        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    public static class ReceitaItemEnvelope {

        private BigDecimal valor;
        private int receitaTipoId;

        public BigDecimal getValor() {
            return valor;
        }

        public void setValor(BigDecimal valor) {
            this.valor = valor;
        }

        public int getReceitaTipoId() {
            return receitaTipoId;
        }

        public void setReceitaTipoId(int receitaTipoId) {
            this.receitaTipoId = receitaTipoId;
        }
    }
}
