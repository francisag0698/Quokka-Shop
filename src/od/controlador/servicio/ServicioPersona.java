
package od.controlador.servicio;

import java.util.List;
import od.controlador.dao.PersonaDao;
import od.modelo.Persona;

/**
 * Clase que permite comunicar el modelo con la vista
 * @author Agreda Francisco
 * @author Macas Dennis
 * @author Ortega César
 * @version JDK 1.8
 */
public class ServicioPersona {
    private PersonaDao obj= new PersonaDao();
    
    /**
     * Permite llamar al obtener persona
     * @return return una persona
     */
    public Persona getPersona(){
        return obj.getPersona();
    }//Cierre del getPersona
    
    /**
     * Metodo que llama al guardar
     * @return devuelve un booleano 
     */
    public boolean guardar(){
        return obj.guardar();
    }//Cierre del metodo guadar
    
    /**
     * Metodo que llama al listar
     * @return devuelve una lista de personas
     */
    public List<Persona>todos(){
        return obj.listar();
    }//cierre del metodo todos
    
    /**
     * Metodo que llama al obtener
     * @param id acepta un parametro id de tipo Long
     * @return devuelve el id
     */
    public Persona obtener(Long id){
        return obj.obtener(id);
    }//Cierre del metodo obtener
    
    /**
     * Metodo que permite fijar una persona
     * @param persona acepta un dato persona de tipo Persona
     */
    public void fijarPersona(Persona persona){
        obj.setPersona(persona);
    }//Cierre del metodo fijarPersona
    
    /**
     * Metodo para llamar al obtenerPersonaCedula
     * @param cedula acepta una dato cedula de tipo String
     *@return devuelve la cedula de la persona de la base de datos 
     */
    public Persona ObtenerPersonaCedula(String cedula){
        return obj.obtenerPersonaCedula(cedula);
    }//Cierre del metodo obtenerPersonaCedula
    
    /**
     * Metodo que llama al  listar personas sin administrador
     * @return devuelve una lista con las personas que se encuentra dentro de la base de datos 
     */
    public List<Persona> listarSinAdministrador(){
        return obj.listarSinAdministrador();
    }//Cierre del metodo listarSinAdministrador
    
    /**
     * Metodo para llamar al listar personas sin administrador mediante una busqueda 
     * @param texto acepta una dato texto de tipo String
     * @return devuelve una lista de personas buscadas por el texto de la base de datos
     */
    public List<Persona> listarSinAdministradorBusqueda(String texto){
        return obj.listarSinAdministradorBusqueda(texto);
    }//Cierre del metodo listarSinAdministradorBusqueda
    
    /**
     * Metodo que permite buscar personas mediante el DNI
     * @param DNI acepta un dato DNI de tipo String
     * @return devuelve una lista de personas de la base de datos 
     */
    public List<Persona> listarSinAdministradorDNIBusqueda(String DNI){
        return obj.listarSinAdministradorDNIBusqueda(DNI);
    }//Cierre del metodo listarSinAdministradorDNIBusqueda
    
    /**
     * Metodo que permite listar personas dependiendo de su genero mediante una busqueda
     * @param texto acepta un dato texto de tipo String 
     * @param genero acpta una dato genero de tipo String
     * @return devuelve una lista de las personas con el genero seleccionado en el parametro de la base de datos
     */
    public List<Persona> listarSinAdministradorGeneroBusqueda(String texto, String genero){
        return obj.listarSinAdministradorGeneroBusqueda(texto, genero);
    }//Cierre del metodo listarSinAdministradorGeneroBusqueda
}//Cierre de la clase ServicioPersona
