/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author angel
 */
@Entity
@Table(name="Ciudades")
@NamedQueries({
    @NamedQuery(name="findCiudades",query="SELECT c FROM Ciudades c"),
    @NamedQuery(name="findCiudadesP",query="SELECT c FROM Ciudades c WHERE c.estados.paises.nombre = :pais")
})

public class Ciudades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre",length=35,nullable=false)
    private String nombre;
    
    @JoinColumn(name="estados",nullable=false,referencedColumnName="id")
    @ManyToOne(optional=false)
    private Estados estados;
    
   @OneToMany(cascade=CascadeType.MERGE,mappedBy="destino")
    private List<Vuelos> vuelosDestino;
   
   @OneToMany(cascade=CascadeType.MERGE,mappedBy="origen")
    private List<Vuelos> vuelosOrigen;
    
  
   
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ciudades)) {
            return false;
        }
        Ciudades other = (Ciudades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Id: " + id + ", Ciudad: " + nombre + ", Estado: " + estados.getNombre();
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    /**
     * @return the vuelosDestino
     */
    public List<Vuelos> getVuelosDestino() {
        return vuelosDestino;
    }

    /**
     * @param vuelosDestino the vuelosDestino to set
     */
    public void setVuelosDestino(List<Vuelos> vuelosDestino) {
        this.vuelosDestino = vuelosDestino;
    }

    /**
     * @return the vuelosOrigen
     */
    public List<Vuelos> getVuelosOrigen() {
        return vuelosOrigen;
    }

    /**
     * @param vuelosOrigen the vuelosOrigen to set
     */
    public void setVuelosOrigen(List<Vuelos> vuelosOrigen) {
        this.vuelosOrigen = vuelosOrigen;
    }


 
    
}
