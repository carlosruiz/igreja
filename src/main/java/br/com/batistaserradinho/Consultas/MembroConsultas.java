/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.Consultas;

import br.com.batistaserradinho.swagger.model.Membro;
import br.com.batistaserradinho.swagger.model.Usuario;
import br.com.batistaserradinho.swagger.service.CrudService;
import javax.persistence.EntityManager;

/**
 *
 * @author cruiz
 */
public class MembroConsultas {

    private final CrudService crudService = new CrudService();

    public Membro obterMembroPorUsuario(Usuario usuario) {
        EntityManager entityManager = crudService.getEntityManager();
        return (Membro) entityManager.createQuery("SELECT i FROM Membro i where login = " + usuario.getId()).getSingleResult();
    }
}
