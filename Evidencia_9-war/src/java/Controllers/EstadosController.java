/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Estados;
import Facade.EstadosFacade;
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
@Named(value = "estadosController")
@SessionScoped
public class EstadosController implements Serializable {

    /**
     * Creates a new instance of EstadosController
     */
    
    @EJB
    private EstadosFacade estadosFacade;
     private Estados estados = new Estados();
    
    public List<Estados> findAllTyped(){
        return estadosFacade.findAllTyped();
    }
    
    public List<Estados> findAllNamed(){
        return estadosFacade.findAllNamed();
    }
    

    /**
     * @return the estados
     */
    public Estados getEstados() {
        return estados;
    }

    /**
     * @param estados the estados to set
     */
    public void setEstados(Estados estados) {
        this.estados = estados;
    }
    
    public Estados find(Long id){
      return  estadosFacade.find(id);
    }
    
    public void clean(){
        estados = new Estados();
    }
    
    public String prepareEdit(Estados estados){
        this.estados = estados;
        return "estadosEdit";
    }
    
    public String save(){
        String temp = estados.getNombre();
        try{
            estadosFacade.save(estados);
            clean();
            addMessage(FacesMessage.SEVERITY_INFO,"Registro Exitoso", temp + " ha sido agregado.");
        }catch(Exception e){
             addMessage(FacesMessage.SEVERITY_ERROR,"Error", temp + " no se puede agregar.");
        }
        return "estadosAlta";
    }
    
    public String update(){
        String temp = estados.getNombre();
        try{
            estadosFacade.update(estados);
            addMessage(FacesMessage.SEVERITY_INFO,"Actualización Exitosa", temp + " ha sido actualizado.");
        }catch(Exception e){
            addMessage(FacesMessage.SEVERITY_ERROR,"Error", temp + " no se ha podido actualizar.");
            return "estadosEdit";
        }
        return "estadosEdit";
    }
    
         public String confirmRemove(Estados estado){
       try{
           if(estado.getCiudades().isEmpty()){
            estadosFacade.remove(estado);
           addMessage(FacesMessage.SEVERITY_INFO,"Eliminaciòn exitosa",estado.getNombre() + " ha sido eliminado.");
           }else{
                addMessage(FacesMessage.SEVERITY_ERROR,"Error", estado.getNombre() + " no se puede eliminar. Depende de otros registros.");
           }
        }catch(Exception e){
           addMessage(FacesMessage.SEVERITY_ERROR,"Error", estado.getNombre() + " no se puede eliminar.");
            
        }
        return "estadosList?faces-redirect=true";
    }
    
    private void addMessage(FacesMessage.Severity serverty ,String summary,String detail){
       
        FacesMessage message = new FacesMessage(serverty, summary, detail);
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, message);
        context.getExternalContext().getFlash().setKeepMessages(true);
    }
    
     public String cleanCancel() {
        estados = new Estados();
        return "estadosList";
    }

    
}
