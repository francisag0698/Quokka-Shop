/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "habitacion")
public class Habitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_habitacion;
    private String codigo;
    private String nombre;
    @Column(length = 2)
    private Integer capacidad;
    @Column(length = 2)
    private Integer nro_camas;
    @Column(length = 6)
    private Double precio;
    private String tipo;
    private Boolean estado;
    private String condiciones;
    private String descripcion;
    @Column(length = 3)
    private Integer cantidad;
    
    @OneToMany(mappedBy = "habitacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservacion> listaReservacion = new ArrayList<>(); 

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_habitacion != null ? id_habitacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id_habitacion fields are not set
        if (!(object instanceof Habitacion)) {
            return false;
        }
        Habitacion other = (Habitacion) object;
        if ((this.id_habitacion == null && other.id_habitacion != null) || (this.id_habitacion != null && !this.id_habitacion.equals(other.id_habitacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "od.modelo.Habitacion[ id=" + id_habitacion + " ]";
    }
    
}
