/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.Consultas;

import br.com.batistaserradinho.swagger.model.Acesso;
import br.com.batistaserradinho.swagger.service.CrudService;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author cruiz
 */
public class AcessoConsultas {
        private final CrudService crudService = new CrudService();

    public boolean existeAcesso(Acesso acesso) {
        EntityManager entityManager = crudService.getEntityManager();
        List<Acesso> acessos =  entityManager.createQuery("SELECT a FROM "+Acesso.class.getName()+" a where a.descricao = '"+acesso.getDescricao()+"'").getResultList();
        for(Acesso ac : acessos){
            if(ac.getCargoId().getId().equals(acesso.getCargoId().getId()))
                return true;
        }
        return false;
    }
}
