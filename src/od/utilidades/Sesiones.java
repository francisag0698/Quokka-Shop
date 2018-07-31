/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.utilidades;

import od.modelo.Cuenta;

/**
 *
 * @author Francis
 */
public class Sesiones {
    private static Cuenta sesion;
    
    public static Cuenta getSesion(){
        return sesion;
    }
    
    public static void setSesion(Cuenta sesion){
        Sesiones.sesion = sesion;
    }
}
