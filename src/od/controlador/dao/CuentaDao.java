package od.controlador.dao;

import javax.persistence.Query;
import od.modelo.Cuenta;

/**
 * Clase que permite utilizar los metodos del adaptador
 * @author Agreda Francisco
 * @author Macas Dennis
 * @author Ortega César
 * @version JDK 1.8
 */
public class CuentaDao extends AdaptadorDao<Cuenta> {

    //inicializacion de una variable privada
    private Cuenta cuenta;

    /**
     *Constructor que encapsula la transferencia de datos desde el modelo Cuenta
     */
    public CuentaDao() {
        super(Cuenta.class);
    }//Cierre del constructor

    /**
     * Permite obtener una nueva cuenta
     * @return devueve una cuenta
     */
    public Cuenta getCuenta() {
        if (cuenta == null) {
            cuenta = new Cuenta();
        }
        return cuenta;
    }//Cierre del getCuenta

    /**
     * Permite modificar una cuenta
     * @param cuenta acepta un dato cuenta de tipo Cuenta
     */
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }//Cierre del setCuenta

    /**
     * Constructor para la variable cuenta
     * @param cuenta acepta un dato cuenta de tipo Cuenta 
     */
    public void Cuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }//Cierre del constructor cuenta

    /**
     * Metodo que permite guardar y modificar la cuenta
     * @return devuelve un valor de tipo booleano
     */
    public boolean guardar() {
        boolean verificar = false;
        try {
            getManager().getTransaction().begin();
            if (cuenta.getId_cuenta() != null) {
                modificar(cuenta);
            } else {
                guardar(cuenta);
            }
            getManager().getTransaction().commit();
            verificar = true;
        } catch (Exception e) {
            System.out.println("No se ha podido guardar o modificar " + e);
            e.printStackTrace();
        }
        return verificar;
    }//Cierre del metodo guardar

    /**
     * Metodo para iniciar sesion dentro del sistema
     * @param usuario acepta un parametro usuario de tipo String
     * @param clave acepta un parametro clave de tipo String
     * @return devuelve una cuenta
     */
    public Cuenta inicioSesion(String usuario, String clave) {
        Cuenta c = null;
        try {
            Query q = getManager().createQuery("SELECT c FROM Cuenta c where c.usuario = :user");//si no funciona mira el nombre de la cuenta en la base de datos
            q.setParameter("user", usuario);
            Cuenta aux = (Cuenta) q.getSingleResult();
            if (aux != null && aux.getClave().equals(clave)) {
                c = aux;
            }
        } catch (Exception e) {

        }
        return c;
    }//Cierre del metodo inicioSesion
    
    /**
     * Metodo para obtener un correo de una cuenta
     * @param correo acepta un dato correo de tipo String
     * @return devuelve una cuenta seleccionada 
     */
    public Cuenta ObtenerCuentaCorreo(String correo) {
        Cuenta c = null;
        try {
            Query q = getManager().createQuery("SELECT c FROM Cuenta c WHERE c.correo = :correo");
            q.setParameter("correo", correo);
            c = (Cuenta) q.getSingleResult();
        } catch (Exception e) {
        }
        return c;
    }//Cierre del metodo obtenerCuentaCorreo
    
    /**
     * Metodo para obtener un usuario de una cuenta
     * @param usuario acepta un dato usuario de tipo String
     * @return devuelve una cuenta seleccionada 
     */
    public Cuenta ObtenerCuentaUsuario(String usuario) {
        Cuenta c = null;
        try {
            Query q = getManager().createQuery("SELECT c FROM Cuenta c WHERE c.usuario = :usuario");
            q.setParameter("usuario", usuario);
            c = (Cuenta) q.getSingleResult();
        } catch (Exception e) {
        }
        return c;
    }//Cierre del metodo obtenerCuentaUsuario
}//Cierre de la clase CuentaDao
