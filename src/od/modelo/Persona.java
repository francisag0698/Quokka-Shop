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
@Table(name = "persona")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_persona;
    @Column(length = 15,unique = true)
    private String dni;
    @Column(length = 20)
    private String tipo_dni;
    @Column(length = 60)
    private String nombres;
    @Column(length = 60)
    private String apellidos;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_nacimiento;
    @Column(length = 60)
    private String pais;
    @Column(length = 60)
    private String ciudad;
    private String direccion;
    @Column(length = 9)
    private String sexo;
    private String telefono;
    
    @OneToOne(mappedBy = "persona",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Cuenta cuenta;
    
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_rol",nullable = false,referencedColumnName = "id_rol")
    private Rol rol;
    
    @OneToMany(mappedBy = "persona",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Historial> listaHistorial= new ArrayList<Historial>();
    
    @OneToMany(mappedBy = "persona",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Reservacion> listaReservacion= new ArrayList<Reservacion>();
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_persona != null ? id_persona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id_persona fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.id_persona == null && other.id_persona != null) || (this.id_persona != null && !this.id_persona.equals(other.id_persona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombres + " " + apellidos;
    }
    
}
