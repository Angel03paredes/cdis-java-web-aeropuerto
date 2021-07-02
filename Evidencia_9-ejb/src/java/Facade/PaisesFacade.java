/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Paises;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author angel
 */
@Stateless
@LocalBean
public class PaisesFacade {

    
    @PersistenceContext(unitName="Evidencia_9-ejbPU")
    private EntityManager em;
    
    public List<Paises> findAllTyped(){
        TypedQuery query;
        query = em.createQuery("SELECT p FROM Paises p", Paises.class);
        return query.getResultList();
    }
    
    public List<Paises> findAllNamed(){
        Query query;
        query = em.createNamedQuery("findPaises");
        return query.getResultList();
    }
    
    public List<Paises> findPaisesP(String nombre){
        Query query;
        query = em.createNamedQuery("findPaisesP");
        query.setParameter("nombre", nombre);
        return query.getResultList();
    }
    
    public Paises find(Long id){
        return em.find(Paises.class, id);
    }
    
    public void save(Paises pais){
        em.persist(pais);
    }
    
    public void update(Paises pais){
        em.merge(pais);
    }
    
    public void remove(Paises pais){
        em.remove(em.merge(pais));
    }
    
}
