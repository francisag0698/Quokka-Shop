/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Francis
 */
@Getter
@Setter
@Entity
@Table(name = "servicio")
public class Servicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_servicio;
    
    private String nombre_servicio;
    
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_habitacion",nullable = false,referencedColumnName = "id_habitacion")
    private Habitacion habitacion;
    
    

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_servicio != null ? id_servicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id_servicio fields are not set
        if (!(object instanceof Servicio)) {
            return false;
        }
        Servicio other = (Servicio) object;
        if ((this.id_servicio == null && other.id_servicio != null) || (this.id_servicio != null && !this.id_servicio.equals(other.id_servicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "od.modelo.Servicio[ id=" + id_servicio + " ]";
    }
    
}
