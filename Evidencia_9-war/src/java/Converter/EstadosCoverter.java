/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import Controllers.EstadosController;
import Entity.Estados;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author angel
 */

@FacesConverter(forClass=Estados.class)
public class EstadosCoverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       EstadosController estadosController = context.getApplication().evaluateExpressionGet(context, "#{estadosController}", EstadosController.class);
       return estadosController.find(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Estados estados = (Estados) value;
        return estados.getId().toString();
    }
    
}
