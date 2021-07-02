/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Vuelos;
import Facade.VuelosFacade;
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
@Named(value = "vuelosController")
@SessionScoped
public class VuelosController implements Serializable {

    /**
     * Creates a new instance of VuelosController
     */
    @EJB
    private VuelosFacade vuelosFacade;
    private Vuelos vuelos = new Vuelos();

    public List<Vuelos> findAllTyped() {
        return vuelosFacade.findAllTyped();
    }

    public List<Vuelos> findAllNamed() {
        return vuelosFacade.findAllNamed();
    }

    public Vuelos findVuelo() {
        return vuelosFacade.findVuelo("300");
    }

    /**
     * @return the vuelos
     */
    public Vuelos getVuelos() {
        return vuelos;
    }

    /**
     * @param vuelos the vuelos to set
     */
    public void setVuelos(Vuelos vuelos) {
        this.vuelos = vuelos;
    }

    public String save() {
        long hourCompare = vuelos.getHoraFinVuelo().getTime() - vuelos.getHoraIniVuelo().getTime();

        String temp = vuelos.getNumDeVuelo();
        try {
            if (vuelosFacade.findNumDeVuelo(temp).isEmpty()) {
                if (!vuelos.getDestino().equals(vuelos.getOrigen())) {
                    if (vuelos.getFechaFinVuelo().after(vuelos.getFechaIniVuelo()) || vuelos.getFechaFinVuelo().equals(vuelos.getFechaIniVuelo())) {
                        if (vuelos.getFechaFinVuelo().equals(vuelos.getFechaIniVuelo()) && hourCompare < 3600000) {
                            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "La hora de fin tiene que ser minimo una hora de direrencia.");
                        } else {
                            vuelosFacade.save(vuelos);
                            clean();
                            addMessage(FacesMessage.SEVERITY_INFO, "Registro Exitoso", temp + " ha sido agregado exitosamente. ");

                        }
                    } else {
                        addMessage(FacesMessage.SEVERITY_ERROR, "Error", "La fecha de inicio es mayor a la final.");
                    }
                } else {

                    addMessage(FacesMessage.SEVERITY_ERROR, "Error", "El origen y destino tienen el mismo lugar.");
                }
            } else {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", "El vuelo " + temp + " ya existe.");

            }
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problemas al agregar");
        }
        return "vuelosAlta";
    }

    public Vuelos find(Long id) {
        return vuelosFacade.find(id);
    }

    public String prepareEdit(Vuelos vuelos) {
        this.vuelos = vuelos;
        return "vuelosEdit";
    }

    public String update() {
        String temp = vuelos.getNumDeVuelo();
        long hourCompare = vuelos.getHoraFinVuelo().getTime() - vuelos.getHoraIniVuelo().getTime();
        try {
           
                if (!vuelos.getDestino().equals(vuelos.getOrigen())) {
                    if (vuelos.getFechaFinVuelo().after(vuelos.getFechaIniVuelo()) || vuelos.getFechaFinVuelo().equals(vuelos.getFechaIniVuelo())) {
                        if (vuelos.getFechaFinVuelo().equals(vuelos.getFechaIniVuelo()) && hourCompare < 3600000) {
                            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "La hora de fin tiene que ser minimo una hora de direrencia.");
                        } else {
                            vuelosFacade.update(vuelos);
                           
                            addMessage(FacesMessage.SEVERITY_INFO, "Actualización Exitosa", temp + " ha sido actualizado.");

                        }
                    } else {
                        addMessage(FacesMessage.SEVERITY_ERROR, "Error", "La fecha de inicio es mayor a la final.");
                    }
                } else {

                    addMessage(FacesMessage.SEVERITY_ERROR, "Error", "El origen y destino tienen el mismo lugar.");
                }
           
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "ERROR", temp + " no se ha actualizado.");
            return "vuelosEdit";
        }
        return "vuelosEdit";
    }

    public void clean() {
        vuelos = new Vuelos();

    }

    public String confirmRemove(Vuelos vuelo) {
        try {
            vuelosFacade.remove(vuelo);
            addMessage(FacesMessage.SEVERITY_INFO, "Eliminaciòn exitosa", vuelo.getNumDeVuelo() + " ha sido eliminado.");

        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "El vuelo " + vuelo.getNumDeVuelo() + " no se puede eliminar.");

        }
        return "vuelosList?faces-redirect=true";
    }

    private void addMessage(FacesMessage.Severity serverty, String summary, String detail) {

        FacesMessage message = new FacesMessage(serverty, summary, detail);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, message);
        context.getExternalContext().getFlash().setKeepMessages(true);
    }

    public String cleanCancel() {
        vuelos = new Vuelos();
        return "vuelosList";
    }

}
