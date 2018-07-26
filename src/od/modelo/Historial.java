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
public class Historial {
    private String actividad;
    private Date fechaHora;
    private Long id;
    private Persona persona;
    private String tipo;
}
