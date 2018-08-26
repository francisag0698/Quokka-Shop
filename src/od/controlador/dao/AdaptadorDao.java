
package od.controlador.dao;

import od.controlador.Conexion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Clase que hereda todos los metodos abstactos de la Interface
 * @author Agreda Francisco
 * @author Macas Dennis
 * @author Ortega César
 * @version JDK 1.8
 */
public class AdaptadorDao <T> implements InterfazDao<T>{
    //Inicializacion de un variable privada
    private Class clazz;
    
    /**
     * Constructor para la variable clazz
     * @param clazz define metodos que se van ingresar desde el modelo
     */
    public AdaptadorDao(Class clazz){
        this.clazz = clazz;
    }//Cierre del constructor  
    
    /**
     * Metodo para gestionar las entidades
     * @return devuelve la conexion 
     */
    public EntityManager getManager(){
        return Conexion.getManager();
    }//Cierre del metodo 
    
    /**
     * Metodo que permite guardar cualquier tipo de dato
     * @param obj define que se puede guardar cualquier tipo de dato
     */
    @Override
    public void guardar(T obj) throws Exception {
        getManager().persist(obj);
    }//Cierre del metodo
    
    /**
     * Metodo que permite Modificar cualquier tipo de dato
     * @param obj define que se puede modificar cualquier tipo de dato
     */
    @Override
    public void modificar(T obj) throws Exception {
        getManager().merge(obj);
    }//Cierre del metodo
    
    /**
     * Metodo que permite listar cualquier tipo de dato 
     * @return devuelve una lista producto del listado de cualquier tipo de elemento
     */
    @Override
    public List<T> listar() {
        List<T> lista = new ArrayList<>();
        try{
            Query query = getManager().createQuery("SELECT a FROM "+clazz.getSimpleName() + " a");
            lista = query.getResultList();
        }catch(Exception e){
            System.out.println(" Error al listar datos " + e);
        }
        return lista;
     }//Cierre del metodo listar
    
    /**
     * Metodo que permite obtener un id
     * @param id define el id a ingresar
     * @return devuelve un dato obj con el id
     */
    @Override
    public T obtener(Long id) {
    T obj = null;
    try {
        obj = (T) getManager().find(clazz, id);
    }catch(Exception e){
        System.out.println("No se encuentra el identificador " +e);
    }
    return obj;
    }//Cierre del metodo obtener  
}//Cierre de la clase AdaptadorDao
