/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Francis
 */
@Getter
@Setter
@Entity
@Table(name = "detalle")
public class Detalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_detalle;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_inicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_fin;
    @Column(length = 2)
    private Integer adultos;
    @Column(length = 2)
    private Integer menores;
    private String observaciones;
    @Column(length = 6)
    private Double pago_total;
    
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_reservacion",nullable = false,referencedColumnName = "id_reservacion")
    private Reservacion reservacion;
    
//    @OneToMany(mappedBy = "detalle",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<Servicio> listaServicio=new ArrayList<Servicio>();
    
    @OneToMany(mappedBy = "detalle",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Habitacion> listaHabitacion = new ArrayList<Habitacion>();

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_detalle != null ? id_detalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id_detalle fields are not set
        if (!(object instanceof Detalle)) {
            return false;
        }
        Detalle other = (Detalle) object;
        if ((this.id_detalle == null && other.id_detalle != null) || (this.id_detalle != null && !this.id_detalle.equals(other.id_detalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "od.modelo.Detalle[ id=" + id_detalle + " ]";
    }
    
}
