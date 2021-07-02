/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Ciudades;
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
public class CiudadesFacade {
    
    @PersistenceContext(unitName="Evidencia_9-ejbPU")
    private EntityManager em;
    
    public List<Ciudades> findAllTyped(){
        TypedQuery query;
        query = em.createQuery("SELECT c FROM Ciudades c", Ciudades.class);
       return query.getResultList();
    }
    
    public List<Ciudades> findAllNamed(){
        Query query;
        query = em.createNamedQuery("findCiudades");
        return query.getResultList();
    }
    
    public List<Ciudades> findCiudades(String nombre){
        TypedQuery query;
       query = em.createQuery("SELECT c FROM Ciudades c WHERE c.estados.nombre = :nombre", Ciudades.class);
       query.setParameter("nombre",nombre);
       return query.getResultList();
    }
    
    public List<Ciudades> findCiudadesE(String nombre,Long idEstado){
        TypedQuery query;
        query = em.createQuery("SELECT c FROM Ciudades c WHERE c.estados.id = :idEstado AND c.nombre = :nombre", Ciudades.class);
        query.setParameter("nombre",nombre);
        query.setParameter("idEstado",idEstado);
        return query.getResultList();
    } 
    
    public List<Ciudades> findCiudadesP(String pais){
        Query query;
        query = em.createNamedQuery("findCiudadesP");
        query.setParameter("pais", pais);
        return query.getResultList();
    }
    
    public Ciudades find(Long id){
        return em.find(Ciudades.class, id);
    }
    
    public void save(Ciudades ciudad){
        em.persist(ciudad);
    }
    
    public void update(Ciudades ciudad){
        em.merge(ciudad);
    }
    
    public void remove(Ciudades ciudad){
        em.remove(em.merge(ciudad));
    }
    
    
}
