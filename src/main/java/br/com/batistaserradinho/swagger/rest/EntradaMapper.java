/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.swagger.rest;

import br.com.batistaserradinho.EnvelopeJson.EntradaEnvelopeJson;
import br.com.batistaserradinho.EnvelopeJson.ReceitaEnvelopeJson.ReceitaEnvelope;
import br.com.batistaserradinho.Util.Constantes;
import br.com.batistaserradinho.swagger.model.Entrada;
import br.com.batistaserradinho.swagger.model.Especie;
import br.com.batistaserradinho.swagger.model.Membro;
import br.com.batistaserradinho.swagger.model.Receita;
import br.com.batistaserradinho.swagger.model.Situacao;
import br.com.batistaserradinho.swagger.service.CrudService;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author Carlos
 */
public class EntradaMapper {
    public static Entrada retornarEntrada(EntradaEnvelopeJson entradaEnvelopeJson, CrudService crudService) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Collection<Membro> membros = new HashSet();
        for (int i = 0; i < entradaEnvelopeJson.getMembroId().length; i++) {
            Membro membro = new Membro();
            membro.setId(entradaEnvelopeJson.getMembroId()[i]);
            membros.add((Membro) crudService.obter(membro));
        }

        Entrada entrada = new Entrada();
        entrada.setDatadeinsercao(entradaEnvelopeJson.getDataDeInsercao());
        entrada.setDatadaentrada(entradaEnvelopeJson.getDataEntrada());
        entrada.setObservacao(entradaEnvelopeJson.getObservacao());
        entrada.setMembroCollection(membros);

        if (entrada.getSituacaoId() == null) {
            Situacao situacao = new Situacao();
            situacao.setId(Constantes.SITUACAO_ATIVA); //Ativo
            entrada.setSituacaoId(situacao);
        }
        return entrada;
    }
    
    public static Receita retornarReceita(ReceitaEnvelope receitaEnvelope, CrudService crudService) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        
            Entrada entrada = (Entrada) crudService.obter(receitaEnvelope.getEntradaId());
            Especie especie = (Especie) crudService.obter(receitaEnvelope.getEspecieId());
            Membro membro  = (Membro) crudService.obter(receitaEnvelope.getMembroId());
            
            Collection receitaItens = new HashSet();
            
            
            
            Receita receita = new Receita();
            receita.setEntradaId(entrada);
            receita.setEspecieId(especie);
            receita.setMembroId(membro);
            receita.setNome(receitaEnvelope.getNome());
            receita.setObservacao(receitaEnvelope.getObservacao());
            
            
            return receita;
    }
}
