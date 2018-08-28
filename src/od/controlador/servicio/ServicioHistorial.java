
package od.controlador.servicio;

import java.util.List;
import od.controlador.dao.HistorialDao;
import od.modelo.Historial;

/**
* Clase que permite utilizar los metodos del ServicioDao
 * @author Agreda Francisco
 * @author Macas Dennis
 * @author Ortega César
 * @version JDK 1.8
 */
public class ServicioHistorial {
    //inicializacion de una variable privada
    private HistorialDao obj = new HistorialDao();
    
    /**
     * Permite extraer un metodo del getHistorial
     * @return devuelve un objeto de Historial
     */
    public Historial getHistorial(){
        return obj.getHistorial();
    }//cierre del metodo get historial
    
    /**
     * Permite extraer una lista de objetos tipo Historial
     * @return devuelve una lista de Historial
     */
    public List<Historial> todos(){
        return obj.listar();
    }//cierre del metodo todos
    
    /**
     * Metodo que llamar el metodo guardar
     * @return devuelve un objeto tipo booelan
     */
    public boolean guardar(){
        return obj.guardar();
    }//Cierre del metodo guardar
    
    public List<Historial> listarPorPersona(Long id){
        return obj.listarPorPersona(id);
    }
}//cierre de la clase ServicioHistorial