
package od.utilidades;

import od.modelo.Cuenta;

/**
 * Clase que permite realizar la sesion
 * @author Agreda Francisco
 * @author Macas Dennis
 * @author Ortega César
 * @version JDK 1.8
 */
public class Sesiones {
    //Inicializacion de la variable privada
    private static Cuenta cuenta;
    
    /**
     * Permite extraer la cuenta del servicioCuenta
     * @return devuelve la cuenta
     */
    public static Cuenta getCuenta() {
        return cuenta;
    }//Cierre del getCuenta
    
    /**
     * Permite modificar la cuenta del servicioCuenta
     * @param cuenta acepta un dato cuenta de tipo Cuenta 
     */
    public static void setCuenta(Cuenta cuenta) {
        Sesiones.cuenta = cuenta;
    }//Cierre del setCuenta
}//Cierre de la clase Sesiones
