/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Estados;
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
public class EstadosFacade {
    
    @PersistenceContext(unitName="Evidencia_9-ejbPU")
    private EntityManager em;
    
    public List<Estados> findAllTyped(){
        TypedQuery query;
        query = em.createQuery("SELECT e FROM Estados e", Estados.class);
        return query.getResultList();
    }
    
    public List<Estados> findAllNamed(){
        Query query;
        query = em.createNamedQuery("findEstados");
        return query.getResultList();
    }
    
   
    public Estados find(Long id){
        return em.find(Estados.class, id);
    }
   
    public void save(Estados estado){
       em.persist(estado);
    }
    
    public void update(Estados estado){
        em.merge(estado);
    }
    
    public void remove(Estados estado){
        em.remove(em.merge(estado));
    }
    
   
    
}
