/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import od.controlador.servicio.ServicioCuenta;
import od.controlador.servicio.ServicioReservacion;
import od.controlador.servicio.ServicioRol;

/**
 *
 * @author Francis
 */
public class Conexion {
    private static EntityManager manager;
    private static final String NAME_EMPU = "OldDeusPU";
    
    
    public static EntityManagerFactory sesion(){
        return Persistence.createEntityManagerFactory(NAME_EMPU);
    }
    
    public static EntityManager getManager(){
        if (manager == null) 
            manager = sesion().createEntityManager();
        return manager;
    }
    
    public static void initManager(){
        new ServicioRol().crearRoles();
        new ServicioCuenta().crearCuentaAdmin();
        
       // new ServicioReservacion().crearReserva();
    }
}
