/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.swagger.service;

import br.com.batistaserradinho.swagger.model.Evento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Carlos
 */
public class EventoService {
    private EntityManager entityManager;
    
    private EntityManager getEntityManager() {
        EntityManagerFactory factory = null;
        try {
            factory = Persistence.createEntityManagerFactory("IgrejaPU");
            entityManager = factory.createEntityManager();
        } finally {
            // factory.close();
        }
        return entityManager;
    }

    @SuppressWarnings("unchecked")
    public List<Evento> consultarTodos() {
        entityManager =  getEntityManager();
        return entityManager.createQuery("SELECT i FROM Evento i").getResultList();
    }

    public Evento consultarPorId(int id) {
        Evento evento = null;
        entityManager =  getEntityManager();
        try {
            evento = entityManager.find(Evento.class, id);
        }catch (Exception e){
         e.printStackTrace();
        } 
        
        finally {
            entityManager.close();
        }
        return evento;
    }

    public Evento salvar(Evento evento) throws Exception {
        entityManager =  getEntityManager();
        try {
            entityManager.getTransaction().begin();
            if (evento.getId() != null)
                evento.setId(null);
                
            entityManager.persist(evento);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return evento;
    }
    
        public Evento editar(Evento evento) throws Exception {
        entityManager =  getEntityManager();
        try {
            entityManager.getTransaction().begin();
            if (evento.getId() != null) 
                evento = entityManager.merge(evento);
            else
                throw new Exception("Registro não encontrado");

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return evento;
    }

    public void removerPorId(final int id) throws Exception {
        entityManager =  getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Evento evento = entityManager.find(Evento.class, id);
            entityManager.remove(evento);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
