package br.com.batistaserradinho.swagger.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CrudService {

    private EntityManager entityManager;

    public EntityManager getEntityManager() {
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
    public List obterTodos(String tabela) {
        entityManager = getEntityManager();
        return entityManager.createQuery("SELECT i FROM " + tabela + " i").getResultList();
    }

    public Object obter(Object instancia) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        entityManager = getEntityManager();
        try {
            instancia = entityManager.find(instancia.getClass(), instancia.getClass().getMethod("getId").invoke(instancia));
        } finally {
            entityManager.close();
        }
        return instancia;
    }

    public Object salvar(Object instancia) throws Exception {
        entityManager = getEntityManager();
        try {

            entityManager.getTransaction().begin();
            entityManager.persist(instancia);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return instancia;
    }

    public Object editar(Object instancia) throws Exception {
        if (obter(instancia) != null) {
            entityManager = getEntityManager();
            try {
                entityManager.getTransaction().begin();
                instancia = entityManager.merge(instancia);
                entityManager.getTransaction().commit();
            } finally {
                entityManager.close();
            }
        } else {
            throw new Exception("Registro não encontrado");
        }
        return instancia;
    }

    public void removerPorId(Object instancia) throws Exception {
        instancia = obter(instancia);
        if (instancia != null) {
            entityManager = getEntityManager();
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(instancia);
                entityManager.getTransaction().commit();
            } finally {
                entityManager.close();
            }
        } else {
            throw new Exception("Registro não encontrado");
        }
    }
}
