/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Paises;
import Facade.PaisesFacade;
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
@Named(value = "paisesController")
@SessionScoped
public class PaisesController implements Serializable {

    /**
     * Creates a new instance of PaisesController
     */
    @EJB
    private PaisesFacade paisesFacade;
    private Paises paises = new Paises();

    public List<Paises> findAllTyped() {
        return paisesFacade.findAllTyped();
    }

    public List<Paises> findAllNamed() {
        return paisesFacade.findAllNamed();
    }

    public List<Paises> findPaisesP() {
        return paisesFacade.findPaisesP("Mexico");
    }

    /**
     * @return the paises
     */
    public Paises getPaises() {
        return paises;
    }

    /**
     * @param paises the paises to set
     */
    public void setPaises(Paises paises) {
        this.paises = paises;
    }

    public Paises find(Long id) {
        return paisesFacade.find(id);
    }

    public String save() {
        String temp = paises.getNombre();
        try {
            paisesFacade.save(paises);
            clean();
            addMessage(FacesMessage.SEVERITY_INFO, "Registro Exitoso", temp + " se actualizo exitosamente.");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", temp + " no se ha podido agregar.");
        }
        return "paisesAlta";
    }

    public String update() {
        String temp = paises.getNombre();
        try {
            paisesFacade.update(paises);
            addMessage(FacesMessage.SEVERITY_INFO, "Actualización Exitosa", temp + " se actualizó exitosamente.");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", temp + " no se ha podido actualizar.");
            return "paisesEdit";
        }
        return "paisesEdit";
    }

    public void clean() {
        paises = new Paises();
    }

    public String prepareEdit(Paises paises) {
        this.paises = paises;
        return "paisesEdit";
    }

    public String confirmRemove(Paises pais) {
        try {
            if (pais.getEstados().isEmpty()) {
                paisesFacade.remove(pais);
                addMessage(FacesMessage.SEVERITY_INFO, "Eliminaciòn exitosa", pais.getNombre() + " ha sido eliminado.");
            } else {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", pais.getNombre() + " no se puede eliminar. Depende de otros registros.");
            }
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", pais.getNombre() + " no se puede eliminar.");

        }
        return "paisesList?faces-redirect=true";
    }

    private void addMessage(FacesMessage.Severity serverty, String summary, String detail) {

        FacesMessage message = new FacesMessage(serverty, summary, detail);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, message);
        context.getExternalContext().getFlash().setKeepMessages(true);
    }

    public String cleanCancel() {
        paises = new Paises();
        return "paisesList";
    }

}
