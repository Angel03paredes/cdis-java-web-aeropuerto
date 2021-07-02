/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author angel
 */
@Entity
@Table(name = "Vuelos")
@NamedQuery(name="findVuelos",query="SELECT v FROM Vuelos v")
public class Vuelos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="numDeVuelo",length = 20,nullable = false)
    private String numDeVuelo;
   
    @JoinColumn(name="numDeAvion",referencedColumnName="id",nullable=false)
    @ManyToOne(optional=false)
    private Aviones aviones;
    
    @JoinColumn(name="origen",referencedColumnName="id",nullable=false)
    @ManyToOne(optional=false)
    private Ciudades origen;
    
   @JoinColumn(name="destino",referencedColumnName="id",nullable=false)
    @ManyToOne(optional=false)
    private Ciudades destino;
    
    @Column(name="numDePasajeros", length= 3,nullable = false)
    private String numDePasajeros;
    
    @Column(name="fechaIniVuelo",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaIniVuelo;
    
    @Column(name="fechaFinVuelo",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date FechaFinVuelo;
    
    @Column(name="horaIniVuelo",nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horaIniVuelo;
    
    @Column(name="horaFinVuelo",nullable=false)
    @Temporal(TemporalType.TIME)
    private Date horaFinVuelo;

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
        if (!(object instanceof Vuelos)) {
            return false;
        }
        Vuelos other = (Vuelos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " Id: " + id + ", Nùmero de vuelo: " + numDeVuelo + ", Nùmero de aviòn: " + aviones.getAerolinea() + ", Origen: " + origen.getNombre() + ", Destino : " + destino.getNombre();
    }

    /**
     * @return the numDeVuelo
     */
    public String getNumDeVuelo() {
        return numDeVuelo;
    }

    /**
     * @param numDeVuelo the numDeVuelo to set
     */
    public void setNumDeVuelo(String numDeVuelo) {
        this.numDeVuelo = numDeVuelo;
    }


    /**
     * @return the numDePasajeros
     */
    public String getNumDePasajeros() {
        return numDePasajeros;
    }

    /**
     * @param numDePasajeros the numDePasajeros to set
     */
    public void setNumDePasajeros(String numDePasajeros) {
        this.numDePasajeros = numDePasajeros;
    }

    /**
     * @return the fechaIniVuelo
     */
    public Date getFechaIniVuelo() {
        return fechaIniVuelo;
    }

    /**
     * @param fechaIniVuelo the fechaIniVuelo to set
     */
    public void setFechaIniVuelo(Date fechaIniVuelo) {
        this.fechaIniVuelo = fechaIniVuelo;
    }

    /**
     * @return the FechaFinVuelo
     */
    public Date getFechaFinVuelo() {
        return FechaFinVuelo;
    }

    /**
     * @param FechaFinVuelo the FechaFinVuelo to set
     */
    public void setFechaFinVuelo(Date FechaFinVuelo) {
        this.FechaFinVuelo = FechaFinVuelo;
    }

    /**
     * @return the horaIniVuelo
     */
    public Date getHoraIniVuelo() {
        return horaIniVuelo;
    }

    /**
     * @param horaIniVuelo the horaIniVuelo to set
     */
    public void setHoraIniVuelo(Date horaIniVuelo) {
        this.horaIniVuelo = horaIniVuelo;
    }

    /**
     * @return the horaFinVuelo
     */
    public Date getHoraFinVuelo() {
        return horaFinVuelo;
    }

    /**
     * @param horaFinVuelo the horaFinVuelo to set
     */
    public void setHoraFinVuelo(Date horaFinVuelo) {
        this.horaFinVuelo = horaFinVuelo;
    }

    /**
     * @return the aviones
     */
    public Aviones getAviones() {
        return aviones;
    }

    /**
     * @param aviones the aviones to set
     */
    public void setAviones(Aviones aviones) {
        this.aviones = aviones;
    }

    /**
     * @return the origen
     */
    public Ciudades getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(Ciudades origen) {
        this.origen = origen;
    }

    /**
     * @return the destino
     */
    public Ciudades getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(Ciudades destino) {
        this.destino = destino;
    }

    
    
}
