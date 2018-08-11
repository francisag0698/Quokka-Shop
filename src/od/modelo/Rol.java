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
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Francis
 */
@Entity
@Getter
@Setter
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_rol;
    
    @Column(length = 60)
    private String nombre;
    
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Persona> listaPersona= new ArrayList<Persona>();

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_rol != null ? id_rol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id_rol fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.id_rol == null && other.id_rol != null) || (this.id_rol != null && !this.id_rol.equals(other.id_rol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "od.modelo.Rol[ id=" + id_rol + " ]";
    }
    
}
