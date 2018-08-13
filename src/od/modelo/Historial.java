/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "historial")
public class Historial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_historial;
    private String accion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @Column(length = 50)
    private String identificador;
    
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona",nullable = false,referencedColumnName = "id_persona")
    private Persona persona;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_historial != null ? id_historial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id_historial fields are not set
        if (!(object instanceof Historial)) {
            return false;
        }
        Historial other = (Historial) object;
        if ((this.id_historial == null && other.id_historial != null) || (this.id_historial != null && !this.id_historial.equals(other.id_historial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "od.modelo.Historial[ id=" + id_historial + " ]";
    }
    
}
