/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import Controllers.AvionesController;
import Entity.Aviones;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author angel
 */

@FacesConverter(forClass=Aviones.class)
public class AvionesConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       AvionesController avionesController = context.getApplication().evaluateExpressionGet(context, "#{avionesController}", AvionesController.class);
       return avionesController.find(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
      Aviones aviones = (Aviones)value;
      return aviones.getId().toString();
    }
    
}
