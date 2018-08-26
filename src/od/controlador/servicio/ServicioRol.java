
package od.controlador.servicio;

import java.util.List;
import od.controlador.dao.RolDao;
import od.modelo.Rol;

/**
 * Clase que permite comunicar el modelo con la vista
 * @author Agreda Francisco
 * @author Macas Dennis
 * @author Ortega César
 * @version JDK 1.8
 */
public class ServicioRol {
    private RolDao obj = new RolDao();
    
    /**
     * Permite llamar al obtener rol
     * @return return un rol
     */
    public Rol getRol(){
        return obj.getRol();
    }//Cierre del getRol
    
    /**
     * Metodo que llama al guardar
     * @return devuelve un booleano 
     */
    public boolean guardar(){
        return obj.guardar();
    }//Cierre del metodo guardar
    
    /**
     * Metodo que llama al listar 
     * @return devuelve una lista de rol
     */
    public List<Rol>todos(){
        return obj.listar();
    }//Cierre del metodo todos
    
    /**
     * Metodo que llama al obtener
     * @param id acepta un parametro id de tipo Long
     * @return devuelve el id
     */
    public Rol obtener(Long id){
        return obj.obtener(id);
    }//Cierre del metodo obtener
    
    /**
     * Metodo que permite fijar un rol
     * @param rol acepta un dato rol de tipo Rol
     */
    public void fijarRol(Rol rol){
        obj.setRol(rol);
    }//Cierre del metodo fijarRol
    
    /**
     * Metodo que llama al buscar rol por Nombre
     * @param nombre acepta un dato nombre de tipo String
     * @return devuelve un objeto de rol
     */
    public Rol buscarRolNombre(String nombre){
        return obj.buscarRolNombre(nombre);
    }//Cierre del metodo buscarRolNombre
    
    /**
     * Metodo que permite crear roles
     */
    public void crearRoles(){
        if (obj.listar().isEmpty()) {
            getRol().setNombre("Administrador");
            guardar();
            fijarRol(null);
            
            getRol().setNombre("Recepcionista");
            guardar();
            fijarRol(null);
            
            getRol().setNombre("Cliente");
            guardar();
            fijarRol(null);
        }
    }//Cierre del metodo crearRoles
}//Cierre de la clase ServicioRol
