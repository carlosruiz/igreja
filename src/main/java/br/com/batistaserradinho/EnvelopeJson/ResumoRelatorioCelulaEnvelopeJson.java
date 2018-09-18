/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.EnvelopeJson;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Carlos
 */
public class ResumoRelatorioCelulaEnvelopeJson {
    public static class ResumoRelatorio{
        
        private int id;
        private int celulaId;
        private Date data;
        private int totalDeMembros;
        private int totalDeVisitantes;
        private int totalDeFrequentadoresAssiduos;
        private int totalDePresentes;
        private BigDecimal oferta;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCelulaId() {
            return celulaId;
        }

        public void setCelulaId(int celulaId) {
            this.celulaId = celulaId;
        }

        public Date getData() {
            return data;
        }

        public void setData(Date data) {
            this.data = data;
        }

        public int getTotalDeMembros() {
            return totalDeMembros;
        }

        public void setTotalDeMembros(int totalDeMembros) {
            this.totalDeMembros = totalDeMembros;
        }

        public int getTotalDeVisitantes() {
            return totalDeVisitantes;
        }

        public void setTotalDeVisitantes(int totalDeVisitantes) {
            this.totalDeVisitantes = totalDeVisitantes;
        }

        public int getTotalDeFrequentadoresAssiduos() {
            return totalDeFrequentadoresAssiduos;
        }

        public void setTotalDeFrequentadoresAssiduos(int totalDeFrequentadoresAssiduos) {
            this.totalDeFrequentadoresAssiduos = totalDeFrequentadoresAssiduos;
        }

        public int getTotalDePresentes() {
            return totalDePresentes;
        }

        public void setTotalDePresentes(int totalDePresentes) {
            this.totalDePresentes = totalDePresentes;
        }

        public BigDecimal getOferta() {
            return oferta;
        }

        public void setOferta(BigDecimal oferta) {
            this.oferta = oferta;
        }   
    }
}
