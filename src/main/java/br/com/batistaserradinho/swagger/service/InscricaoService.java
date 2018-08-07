/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.swagger.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Carlos
 */
public class InscricaoService {
    private EntityManager entityManager;
       
    private EntityManager getEntityManager() {
        EntityManagerFactory factory = null;
        try {
            factory = Persistence.createEntityManagerFactory("IgrejaPU");
            entityManager = factory.createEntityManager();
        } finally {
             //factory.close();
        }
        return entityManager;
    }

    @SuppressWarnings("unchecked")
    public List consultarTodos(String tabela) {
        entityManager =  getEntityManager();
        return entityManager.createQuery("SELECT i FROM "+tabela+" i").getResultList();
    }

    public Object consultarPorId(Object instancia) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        entityManager =  getEntityManager();
        try {
            instancia = entityManager.find(instancia.getClass(), instancia.getClass().getMethod("getId").invoke(instancia));
        } finally {
            entityManager.close();
        }
        return instancia;
    }

    public Object salvar(Object instancia) throws Exception {
        entityManager =  getEntityManager();
        try {
            entityManager.getTransaction().begin();
            if (instancia.getClass().getMethod("getId").invoke(instancia) != null)
                instancia.getClass().getMethod("setId", null);
                
            entityManager.persist(instancia);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return instancia;
    }
    
        public Object editar(Object instancia) throws Exception {
        entityManager =  getEntityManager();
        try {
            entityManager.getTransaction().begin();
            if (instancia.getClass().getMethod("getId").invoke(instancia) != null) 
                instancia = entityManager.merge(instancia);
            else
                throw new Exception("Registro não encontrado");

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return instancia;
    }

    public void removerPorId(final int id) throws Exception {
        entityManager =  getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Object instancia = consultarPorId(id);
            entityManager.remove(instancia);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
