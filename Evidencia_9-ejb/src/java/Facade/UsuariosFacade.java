/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author angel
 */
@Stateless
@LocalBean
public class UsuariosFacade {

   @PersistenceContext(unitName="Evidencia_9-ejbPU")
   private EntityManager em;
   
   public  List<Usuarios> findAll(){
       TypedQuery query;
       query = em.createQuery("SELECT u FROM Usuarios u", Usuarios.class);
       return query.getResultList();
   }
   
   public List<Usuarios> iniciarSesion(String email,String contra){
       TypedQuery query;
       query = em.createQuery("SELECT u FROM Usuarios u WHERE u.email = :email AND u.contrasena = :contra",Usuarios.class);
       query.setParameter("email", email);
       query.setParameter("contra", contra);
       return query.getResultList();
   }
   
   public Usuarios find(Long id){
       return em.find(Usuarios.class, id);
   }
   
   public void save(Usuarios usuario){
       em.persist(usuario);
   }
   
   public void update(Usuarios usuario){
       em.merge(usuario);
   }
   
   public void remove (Usuarios usuario){
       em.remove(em.merge(usuario));
   }
   
}
