/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Aviones;
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
public class AvionesFacade {
    
    @PersistenceContext(unitName="Evidencia_9-ejbPU")
    private EntityManager em;
    
    public List<Aviones> findAllTyped(){
        TypedQuery<Aviones> query;
        query = em.createQuery("SELECT a FROM Aviones a", Aviones.class);
        return query.getResultList();
    }
    
    public List<Aviones> findAllNamed(){
        Query query;
        query = em.createNamedQuery("findAviones");
        return query.getResultList();
    }
    
    public List<Aviones> findAviones(String numDeAvion){
        TypedQuery query;
        query = em.createQuery("SELECT a FROM Aviones a WHERE a.numDeAvion =:numDeAvion ", Aviones.class);
        query.setParameter("numDeAvion", numDeAvion);
        return query.getResultList();
    }
    
   
    public List<Aviones> findAvionesP(String numDeAvion , String capacidadDePasajeros){
        Query query;
        query = em.createNamedQuery("findAvionesP");
        query.setParameter("numDeAvion", numDeAvion);
        query.setParameter("capacidadDePasajeros",capacidadDePasajeros);
        return query.getResultList();
    }
   
    public Aviones find(Long id){
      return  em.find(Aviones.class,id);
    }
    
    public void save(Aviones avion){
        em.persist(avion);
    }
    
    public void update(Aviones avion){
        em.merge(avion);
    }
    
    public void remove(Aviones avion){
        em.remove(em.merge(avion));
    }
    
    
    
}
