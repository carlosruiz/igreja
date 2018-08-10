/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.EnvelopeJson;

import br.com.batistaserradinho.swagger.model.Inscricao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class InscricaoEnvelopeJson {

    public static class Inscricoes{
        private List<Inscricao> list = new ArrayList();
        private int count = 0;

        public void addInscricao(Inscricao i){
            list.add(i);
            count = list.size();
        }
    }
}

