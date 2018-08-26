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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    @Column(length = 2)
    private Integer adultos;
    @Column(length = 2)
    private Integer menores;
    private String observaciones;
    @Column(length = 10)
    private Double pago_subtotal;
    @Column(length = 3)
    private Integer cant_habitaciones;
    
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_reservacion",nullable = false,referencedColumnName = "id_reservacion")
    private Reservacion reservacion;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "detalle_servicio",
        joinColumns = @JoinColumn(name = "id_detalle"),
        inverseJoinColumns = @JoinColumn(name = "id_servicio")
    )
    private List<Servicio> servicios = new ArrayList<Servicio>();

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
