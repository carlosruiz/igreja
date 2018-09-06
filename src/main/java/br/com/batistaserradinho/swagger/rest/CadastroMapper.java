/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.swagger.rest;

import br.com.batistaserradinho.EnvelopeJson.CadastroEnvelopeJson.Cadastro;
import br.com.batistaserradinho.Util.Criptografia;
import br.com.batistaserradinho.swagger.model.Celula;
import br.com.batistaserradinho.swagger.model.CelulaMembro;
import br.com.batistaserradinho.swagger.model.Membro;
import br.com.batistaserradinho.swagger.model.Situacao;
import br.com.batistaserradinho.swagger.model.Usuario;
import br.com.batistaserradinho.swagger.service.CrudService;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author cruiz
 */
public class CadastroMapper {
    
    public static Membro retornarMembro(Cadastro cadastro) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Situacao situacao = new Situacao();
        situacao.setId(1); //Ativo
        
        Usuario usuario = new Usuario();
        Collection usuarios = new HashSet();
        
        usuario.setId(cadastro.getLogin());
        usuario.setSenha(Criptografia.criptografar(cadastro.getSenha()));
        usuario.setSituacaoId(situacao);
        usuarios.add(usuario);
        
        CelulaMembro celulaMembro = new CelulaMembro();
        Collection celulaMembros = new HashSet();
        if(cadastro.getCelulaId() > 0){
            Celula celula = new Celula();
            celula.setId(cadastro.getCelulaId());
            celula = (Celula) new CrudService().obter(celula);
                
            if(celula != null){
                celulaMembro.setCelulaId(celula);
                celulaMembro.setSituacaoId(situacao); 
                celulaMembros.add(celulaMembro);
            }          
        }
                                       
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
        membro.setCelulaMembroCollection(celulaMembros);
        celulaMembro.setMembroId(membro);
        
        return membro;
    }   
}
