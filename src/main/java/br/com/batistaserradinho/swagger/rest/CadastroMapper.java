/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.swagger.rest;

import br.com.batistaserradinho.EnvelopeJson.CadastroEnvelopeJson.Cadastro;
import br.com.batistaserradinho.Util.Criptografia;
import br.com.batistaserradinho.swagger.model.Membro;
import br.com.batistaserradinho.swagger.model.Situacao;
import br.com.batistaserradinho.swagger.model.Usuario;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author cruiz
 */
public class CadastroMapper {
    
    public static Membro retornarMembro(Cadastro cadastro){
        Situacao situacao = new Situacao();
        situacao.setId(1); //Ativo

        Usuario usuario = new Usuario();
        usuario.setId(cadastro.getLogin());
        usuario.setSenha(Criptografia.criptografar(cadastro.getSenha()));
        usuario.setSituacaoId(situacao);
                
        Collection usuarios = new HashSet();
        usuarios.add(usuario);
                               
        Membro membro = new Membro();
        membro.setNome(cadastro.getNome());
        membro.setEmail(cadastro.getEmail());
        membro.setTelefone(cadastro.getTelefone());
        membro.setEndereco(cadastro.getEndereco());
        membro.setBairro(cadastro.getBairro());
        membro.setCidade(cadastro.getCidade());
        membro.setUf(cadastro.getUf());    
        membro.setUsuarioCollection(usuarios);    
        membro.setSituacaoId(situacao);
        usuario.setMembroId(membro);      
        
        return membro;
    }   
}
