/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.modelo;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Dennis
 */
@Getter
@Setter
public class Persona {
    private Long id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String direccion;
    private Date fechaNacimiento;
    private String numeroIdentificacion;
    private String pais;
    private String ciudad;
    private Historial historial;
    private Reservacion reservacion;
    private String sexo;
    private String telefono;
    private String tipoIdentificador;
    
    private Rol rol;
    private Cuenta cuenta;
}
