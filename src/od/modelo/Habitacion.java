/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.modelo;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Dennis
 */
@Getter
@Setter
public class Habitacion {
     private String caracteristicas;
    private String estado;
    private Long id;
    private Reservacion reservacion;
    private Integer numero;
    private String tipo;
}
