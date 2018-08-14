/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.Consultas;

import br.com.batistaserradinho.swagger.model.Acesso;
import br.com.batistaserradinho.swagger.service.CrudService;
import javax.persistence.EntityManager;

/**
 *
 * @author cruiz
 */
public class AcessoConsultas {
        private final CrudService crudService = new CrudService();

    public boolean existeAcesso(Acesso acesso) {
        EntityManager entityManager = crudService.getEntityManager();
        return entityManager.createQuery("SELECT i FROM igreja.Acesso a where a.cargo_Id = " + acesso.getCargoId().getId() + 
                                                        "and a.descricao = '"+acesso.getDescricao()+"'").getMaxResults() > 0 ;
    }
}
