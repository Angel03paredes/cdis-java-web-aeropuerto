/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Aviones;
import Facade.AvionesFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author angel
 */
@Named(value = "avionesController")
@SessionScoped
public class AvionesController implements Serializable {

    @EJB
    private AvionesFacade avionesFacade;
    private Aviones aviones = new Aviones();
    
    public List<Aviones> findAllTyped(){
        return avionesFacade.findAllTyped();
    }
    
    public List<Aviones> findAllNamed(){
        return avionesFacade.findAllNamed();
    }
    
    public List<Aviones> findAviones(){
        return avionesFacade.findAviones("200");
    }

    
     public List<Aviones> findAvionesP(){
        return avionesFacade.findAvionesP("200","200");
    }
     
    /**
     * @return the aviones
     */
    public Aviones getAviones() {
        return aviones;
    }

    /**
     * @param aviones the aviones to set
     */
    public void setAviones(Aviones aviones) {
        this.aviones = aviones;
    }
    
   public Aviones find(Long id){
       return avionesFacade.find(id);
   }
   
   public String save(){
       String temp = aviones.getNumDeAvion();
       try{
           if(avionesFacade.findAviones(temp).isEmpty()){
                avionesFacade.save(aviones);
           clean();
           addMessage(FacesMessage.SEVERITY_INFO,"Registro Exitoso", temp + " ha sido agregado.");
           }else{
               addMessage(FacesMessage.SEVERITY_ERROR,"Error", temp + " ya existe.");
           }
          
       }catch(Exception e){
           addMessage(FacesMessage.SEVERITY_ERROR,"Error", temp + " no se puede agregar.");
       }
       return "avionesAlta";
   }
   
   public String preparedEdit(Aviones aviones){
       this.aviones = aviones;
       return "avionesEdit";
   }
   
   public void clean(){
       aviones = new Aviones();
   }
    
   public String update(){
       String temp = aviones.getNumDeAvion();
       try{
       avionesFacade.update(aviones);
       addMessage(FacesMessage.SEVERITY_INFO,"Actualización Exitosa", temp + " ha sido actualizado.");
       }catch(Exception e){
       addMessage(FacesMessage.SEVERITY_ERROR,"Error", temp + " no se ha podido actualizar.");
       return "avionesEdit";
   }
       return "avionesEdit";
   }
   
     public String confirmRemove(Aviones avion){
       try{
           if(avion.getVuelos().isEmpty()){
            avionesFacade.remove(avion);
           addMessage(FacesMessage.SEVERITY_INFO,"Eliminación exitosa",avion.getNumDeAvion() + " ha sido eliminado.");
           }else{
                addMessage(FacesMessage.SEVERITY_ERROR,"Error", avion.getNumDeAvion() + " no se puede eliminar. Depende de otros registros.");
           }
        }catch(Exception e){
           addMessage(FacesMessage.SEVERITY_ERROR,"Error", avion.getNumDeAvion() + " no se puede eliminar.");
            
        }
        return "avionesList?faces-redirect=true";
    }
    
    private void addMessage(FacesMessage.Severity serverty ,String summary,String detail){
       
        FacesMessage message = new FacesMessage(serverty, summary, detail);
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, message);
        context.getExternalContext().getFlash().setKeepMessages(true);
    }
  
     public String cleanCancel() {
        aviones = new Aviones();
        return "avionesList";
    }

  
   
}
