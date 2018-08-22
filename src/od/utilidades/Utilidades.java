/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.utilidades;

import java.util.Date;
import od.controlador.servicio.ServicioHistorial;
import od.controlador.servicio.ServicioPersona;

/**
 *
 * @author USUARIO
 */
public class Utilidades {
    public static void guardarHistorial(String identificador,String accion, ServicioPersona sp) {
        ServicioHistorial hs = new ServicioHistorial();
        hs.getHistorial().setIdentificador(identificador);
        hs.getHistorial().setAccion(accion);
        hs.getHistorial().setFecha(new Date());
        hs.getHistorial().setPersona(sp.getPersona());
        hs.guardar();
    }
}
