
package od.controlador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import od.controlador.servicio.ServicioCuenta;
import od.controlador.servicio.ServicioReservacion;
import od.controlador.servicio.ServicioRol;

/**
 * Clase que permite la conexión con la base de datos
 * @author Agreda Francisco
 * @author Macas Dennis
 * @author Ortega César
 * @version JDK 1.8
 */
public class Conexion {
    //inicializacion de variables privadas 
    private static EntityManager manager;
    private static final String NAME_EMPU = "OldDeusPU";
    
    /**
     * Metodo para interactuar con las entidades de la persistencia
     * @return devuelve la unidad de persistencia nombrada
     */
    public static EntityManagerFactory sesion(){
        return Persistence.createEntityManagerFactory(NAME_EMPU);
    }//cierre del metodo
    
    /**
     * Metodo para crear la conexion con las entidades
     * @return devuelve una EntityManager
     */
    public static EntityManager getManager(){
        if (manager == null) 
            manager = sesion().createEntityManager();
        return manager;
    }//cierre del metodo
    
    /**
     * Metodo main para la creacion de Roles y una cuenta Administrador 
     */
    public static void initManager(){
        new ServicioRol().crearRoles();
        new ServicioCuenta().crearCuentaAdmin();
        
       // new ServicioReservacion().crearReserva();
    }//Cierre del metodo
}//Cierre de la clase Conexion
