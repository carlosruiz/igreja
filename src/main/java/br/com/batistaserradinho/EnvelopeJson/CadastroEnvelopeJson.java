/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.EnvelopeJson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cruiz
 */
public class CadastroEnvelopeJson {
        public static class Cadastro{
        
        private String nome;
        private String login;
        private String senha;
        private String telefone;
        private String email;
        private String endereco;
        private String bairro;
        private String cidade;
        private String uf;
        private Integer celulaId;

        public Integer getCelulaId() {
            return celulaId;
        }

        public void setCelulaId(Integer celulaId) {
            this.celulaId = celulaId;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
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

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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
    }

    public static class Cadastros{
        private List<Cadastro> cadastros = new ArrayList(); 
        private int count = 0;

        public void addUser(Cadastro u){
            cadastros.add(u);
            count = cadastros.size();
        }
    }
}
