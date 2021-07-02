/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;


import Controllers.CiudadesController;
import Entity.Ciudades;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author angel
 */

@FacesConverter(forClass=Ciudades.class)
public class CiudadesConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       CiudadesController ciudadesController = context.getApplication().evaluateExpressionGet(context, "#{ciudadesController}", CiudadesController.class);
       return ciudadesController.find(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Ciudades ciudades = (Ciudades) value;
        return ciudades.getId().toString();
    }
    
}
