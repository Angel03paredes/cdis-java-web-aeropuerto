/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Vuelos;
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
public class VuelosFacade {
    
    @PersistenceContext(unitName="Evidencia_9-ejbPU")
    private EntityManager em;
    
    public List<Vuelos> findAllTyped(){
        TypedQuery query;
        query = em.createQuery("SELECT v FROM Vuelos v", Vuelos.class);
        return query.getResultList();
                
    }
    
    public List<Vuelos> findAllNamed(){
        Query query;
        query = em.createNamedQuery("findVuelos");
        return query.getResultList();
    }
    
    public Vuelos findVuelo(String numDeVuelo){
        TypedQuery query = em.createQuery("SELECT v FROM Vuelos v WHERE v.numDeVuelo = :numDeVuelo", Vuelos.class);
        query.setParameter("numDeVuelo", numDeVuelo);
        return (Vuelos) query.getSingleResult();
    }
    
    public List<Vuelos> findNumDeVuelo(String numDeVuelo){
        TypedQuery query = em.createQuery("SELECT v FROM Vuelos v WHERE UPPER(v.numDeVuelo) = :numDeVuelo",Vuelos.class);
        query.setParameter("numDeVuelo",numDeVuelo.toUpperCase());
        return query.getResultList();
    }
    
    
    
    
    public void save(Vuelos vuelo){
        em.persist(vuelo);
    }
    
    public Vuelos find(Long id){
     return em.find(Vuelos.class,id);   
    }
    
    public void update(Vuelos vuelo){
        em.merge(vuelo);
    }
    
    public void remove(Vuelos vuelo){
        em.remove(em.merge(vuelo));
    }
    
}
