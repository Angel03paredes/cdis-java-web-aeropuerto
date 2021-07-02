/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Ciudades;
import Entity.Estados;
import Facade.CiudadesFacade;
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
@Named(value = "ciudadesController")
@SessionScoped
public class CiudadesController implements Serializable {

    /**
     * Creates a new instance of CiudadesController
     */
    @EJB
    private CiudadesFacade ciudadesFacade;
    private Ciudades ciudades = new Ciudades();

    public List<Ciudades> findAllTyped() {
        return ciudadesFacade.findAllTyped();
    }

    public List<Ciudades> findAllNamed() {
        return ciudadesFacade.findAllNamed();
    }

    public List<Ciudades> findCiudades() {
        return ciudadesFacade.findCiudades("Jalisco");
    }

    public List<Ciudades> findCiudadesP() {

        return ciudadesFacade.findCiudadesP("Mexico");
    }

    /**
     * @return the ciudades
     */
    public Ciudades getCiudades() {
        return ciudades;
    }

    /**
     * @param ciudades the ciudades to set
     */
    public void setCiudades(Ciudades ciudades) {
        this.ciudades = ciudades;
    }

    public Ciudades find(Long id) {
        return ciudadesFacade.find(id);
    }

    public String save() {
        String temp = ciudades.getNombre();
        try {
            if (ciudadesFacade.findCiudadesE(temp, ciudades.getEstados().getId()).isEmpty()) {
                ciudadesFacade.save(ciudades);
                clean();
                addMessage(FacesMessage.SEVERITY_INFO, "Registro Exitoso", temp + " ha sido agregado.");
            } else {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", temp + " ya existe en el estado de " + ciudades.getEstados().getNombre() + " .");
            }

        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", temp + " no se puede agregar.");
        }
        return "ciudadesAlta";
    }

    public void clean() {
        ciudades = new Ciudades();
    }

    public String update() {
        String temp = ciudades.getNombre();
        try {
            
                ciudadesFacade.update(ciudades);
                addMessage(FacesMessage.SEVERITY_INFO, "Actualización Exitosa", temp + " ha sido actualizado.");
           
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", temp + " no se ha podido actualizar.");
            return "ciudadesEdit";
        }
        return "ciudadesEdit";
    }

    public String prepareEdit(Ciudades ciudades) {
        this.ciudades = ciudades;
        return "ciudadesEdit";
    }

    public String confirmRemove(Ciudades ciudad) {
        try {
            if (ciudad.getVuelosOrigen().isEmpty() && ciudad.getVuelosDestino().isEmpty()) {
                ciudadesFacade.remove(ciudad);
                addMessage(FacesMessage.SEVERITY_INFO, "Eliminaciòn exitosa", ciudad.getNombre() + " ha sido eliminado.");
            } else {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", ciudad.getNombre() + " no se puede eliminar. Depende de otros registros.");
            }
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", ciudad.getNombre() + " no se puede eliminar.");

        }
        return "ciudadesList?faces-redirect=true";
    }

    private void addMessage(FacesMessage.Severity serverty, String summary, String detail) {

        FacesMessage message = new FacesMessage(serverty, summary, detail);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, message);
        context.getExternalContext().getFlash().setKeepMessages(true);
    }

    public String cleanCancel() {
        ciudades = new Ciudades();
        return "ciudadesList";
    }

}
