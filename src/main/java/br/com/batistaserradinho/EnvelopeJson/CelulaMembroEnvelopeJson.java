/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.EnvelopeJson;

/**
 *
 * @author cruiz
 */
public class CelulaMembroEnvelopeJson {

    public int getCelulaId() {
        return celulaId;
    }

    public void setCelulaId(int celulaId) {
        this.celulaId = celulaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCelulaMembroId() {
        return celulaMembroId;
    }

    public void setCelulaMembroId(int celulaMembroId) {
        this.celulaMembroId = celulaMembroId;
    }

    public boolean isEhLider() {
        return ehLider;
    }

    public void setEhLider(boolean ehLider) {
        this.ehLider = ehLider;
    }

    public boolean isEhLiderEmTreinamento() {
        return ehLiderEmTreinamento;
    }

    public void setEhLiderEmTreinamento(boolean ehLiderEmTreinamento) {
        this.ehLiderEmTreinamento = ehLiderEmTreinamento;
    }
    
    int celulaId;
    String nome;
    int celulaMembroId;
    boolean ehLider;
    boolean ehLiderEmTreinamento;
}
