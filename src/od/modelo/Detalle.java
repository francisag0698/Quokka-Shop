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
public class Detalle {
    private Integer adultos;
    private Date fechaFin;
    private Date fechaInicio;
    private Long id;
    private Integer ninios;
    private Servicio servicio;
    private Reservacion reservacion;
}
