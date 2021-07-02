/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Usuarios;
import Facade.UsuariosFacade;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author angel
 */
@Named(value = "usuariosController")
@SessionScoped
public class UsuariosController implements Serializable {

    @EJB
    private UsuariosFacade usuariosFacade;
    private Usuarios usuarios = new Usuarios();
    private String contra = "";
    private String email = "";
    private String perfil = "";

    public List<Usuarios> findAll() {
        return usuariosFacade.findAll();
    }

    public String prepareEdit(Usuarios usuario) {
        setUsuarios(usuario);
        return "usuariosEdit";
    }

    public String save() {
        String temp = getUsuarios().getNombre();
        try {
            usuariosFacade.save(getUsuarios());
            clean();
            addMessage(FacesMessage.SEVERITY_INFO, "Registro Exitoso", temp + " ha sido agregado.");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", temp + " no se puede agregar.");
        }
        return "usuariosAlta";
    }

    public String update() {
        String temp = usuarios.getNombre();
        try {
            usuariosFacade.update(usuarios);
            addMessage(FacesMessage.SEVERITY_INFO, "Actualización Exitosa", temp + " ha sido actualizado.");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", temp + " no se ha podido actualizar.");
            return "usuariosEdit";
        }
        return "usuariosEdit";
    }

    public String confirmRemove(Usuarios usuario) {
        try {

            usuariosFacade.remove(usuario);
            addMessage(FacesMessage.SEVERITY_INFO, "Eliminaciòn exitosa", usuario.getNombre() + " ha sido eliminado.");

        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", usuario.getNombre() + " no se puede eliminar.");

        }
        return "usuariosList?faces-redirect=true";
    }

    public void clean() {
        setUsuarios(new Usuarios());
    }

    private void addMessage(FacesMessage.Severity serverty, String summary, String detail) {

        FacesMessage message = new FacesMessage(serverty, summary, detail);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, message);
        context.getExternalContext().getFlash().setKeepMessages(true);
    }

    /**
     * @return the usuarios
     */
    public Usuarios getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public String cleanCancel() {
        usuarios = new Usuarios();
        return "usuariosList";
    }

    public String iniciarSesion() {
        List<Usuarios> user = usuariosFacade.iniciarSesion(this.getEmail(), this.getContra());
        if (user.isEmpty()) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se ha podido iniciar sesión. Vuelva a intentar.");
            return "index";
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nombre", user.get(0).getNombre());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("email", user.get(0).getEmail());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("perfil", user.get(0).getPerfil());
          
            return "home";
        }

    }

    public String mostrarNombre() {
        String nombre = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
        return nombre;
    }

    public void auth() {
        String nombre = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
        if (nombre == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (IOException ex) {

            }
        }

    }
    


    public String logOut() {
        this.contra = "";
        this.email = "";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        return "/index.xhtml";
    }

    public boolean showAuth() {
        String nombre = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
        if (nombre == null) {
            return false;
        } else {
            return true;
        }

    }

    public boolean showBtn() {
        int perfil = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfil");
        if (perfil == 3) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * @return the contra
     */
    public String getContra() {
        return contra;
    }

    /**
     * @param contra the contra to set
     */
    public void setContra(String contra) {
        this.contra = contra;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
