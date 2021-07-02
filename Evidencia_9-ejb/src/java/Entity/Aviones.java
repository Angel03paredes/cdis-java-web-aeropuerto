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
@Table(name="Aviones")
@NamedQueries({
    @NamedQuery(name="findAviones",query="SELECT a FROM Aviones a"),
    @NamedQuery(name="findAvionesP", query="SELECT a FROM Aviones a WHERE a.numDeAvion =:numDeAvion AND a.capacidadDePasajeros = :capacidadDePasajeros")
})

        

public class Aviones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @Column(name="numDeAvion", length=20 , nullable = false)
    private String numDeAvion;
    
    @Column(name="capacidadDePasajeros",length=3,nullable=false)
    private String capacidadDePasajeros;
    
    @Column(name="modelo",length=25,nullable=false)
    private String modelo;
    
    @Column(name="aerolinea",length=35,nullable=false)
    private String aerolinea;
    
    @OneToMany(cascade=CascadeType.MERGE,mappedBy="Aviones")
    private List<Vuelos> vuelos;
    

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
        if (!(object instanceof Aviones)) {
            return false;
        }
        Aviones other = (Aviones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Id : " + id + ", Nùmero de Aviòn: " + numDeAvion + ", Capacidad de pasajeros: " + capacidadDePasajeros;
    }

    /**
     * @return the numDeAvion
     */
    public String getNumDeAvion() {
        return numDeAvion;
    }

    /**
     * @param numDeAvion the numDeAvion to set
     */
    public void setNumDeAvion(String numDeAvion) {
        this.numDeAvion = numDeAvion;
    }

    /**
     * @return the capacidadDePasajeros
     */
    public String getCapacidadDePasajeros() {
        return capacidadDePasajeros;
    }

    /**
     * @param capacidadDePasajeros the capacidadDePasajeros to set
     */
    public void setCapacidadDePasajeros(String capacidadDePasajeros) {
        this.capacidadDePasajeros = capacidadDePasajeros;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the aerolinea
     */
    public String getAerolinea() {
        return aerolinea;
    }

    /**
     * @param aerolinea the aerolinea to set
     */
    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    /**
     * @return the vuelos
     */
    public List<Vuelos> getVuelos() {
        return vuelos;
    }

    /**
     * @param vuelos the vuelos to set
     */
    public void setVuelos(List<Vuelos> vuelos) {
        this.vuelos = vuelos;
    }

    
    
}
