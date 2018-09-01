/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.servicio;
import java.util.Date;
import java.util.List;
import od.controlador.dao.HabitacionDao;
import od.modelo.Habitacion;

/**
 * Clase que permite utilizar los metodos del ServicioHabitacion
 * @author Agreda Francisco
 * @author Macas Dennis
 * @author Ortega César
 * @version JDK 1.8
 */
public class ServicioHabitacion {
    //inicializacion de una variable privada
    private HabitacionDao obj = new HabitacionDao();
    /**
     * Permite extraer un metodo del getHabitacion
     * @return devuelve un objeto de Habitacion
     */
    public Habitacion getHabitacion(){
        return obj.getHabitacion();
    }//cierre del metodo getHabitacion
    
    /**
     * Metodo que llamar el metodo guardar
     * @return devuelve un objeto tipo booelan
     */
    public boolean guardar(){
        return obj.guardar();
    }//Cierre del metodo guardar
    
    /**
     * Metodo que permite llamar el metodo listar de AdaptadorDao
     * @return devuelve un objeto tipo lista
     */
    public List<Habitacion> todos(){
        return obj.listar();
    }//Cierre del metodo todos
    
    /**
     * Metodo que permite obtener el id del Habitacion
     * @param id acepta un dato tipo Long
     * @return devuelve un objeto  obtener
     */
    public Habitacion obtener(Long id){
        return obj.obtener(id);
    }//Cierre del metodo obtener
    /**
     * Permite llamar al metodo para modificar una Habitacion
     * @param habitacion acepta un dato habitacion de tipo Habitacion
     */
    public void fijarHabitacion(Habitacion habitacion){
        obj.setHabitacion(habitacion);
    }//Cierre del fijarServicio
    
   /**
     * Metodo que permite llamar al metodo para listar las habitaciones por tipo
     * @param tipo acepta un dato tipo String
     * @return devuelve un obejto lista     
     */
    public List<Habitacion> listarTipo(String tipo){
        return obj.listarTipo(tipo);
    }//cierre del metodo listarTipo
    
    /**
     * Metodo que permite llamar al metodo para listar las habitaciones por estado
     * @param estado acepta un dato tipo Boolean
     * @return devuelve un objeto lista
     */
    public List<Habitacion> listarEstado(Boolean estado){
        return obj.listarEstado(estado);
    }//cierre del metodo listarEstado
    
    /**
     * Metodo que permite llamar al metodo para listar las habitaciones por busqueda
     * @param texto acepta un dato tipo String
     * @return devuelve un objeto lista
     */
    public List<Habitacion>listarBusqueda(String texto){
        return obj.listarBusqueda(texto);
    }//cierre del metodo listarBusqueda
    
    /**
     * Metodo que permite llamar al metodo para listar las habitaciones por busqueda por tipo
     * @param tipo acepta un dato tipo String
     * @param texto acepta un dato tipo String
     * @return devuelve un objeto lista
     */
    public List<Habitacion> listarBusquedaTipo(String tipo,String texto){
        return obj.listarBusquedaTipo(tipo, texto);
    }//cierre del metodo listarBusquedaTipo
    
    /**
     * Metodo que permite llamar al metodo para listar las habitaciones por busqueda por estado
     * @param texto acepta un dato tipo String
     * @return devuelve un objeto lista
     */
    public List<Habitacion> listarBusquedaCodigo(String texto){
        return obj.listarBusquedaCodigo(texto);
    }//cierre del metodo listarBusquedaEstado
    
    /**
     * Metodo que permite llamar al metodo para listar las habitaciones por disponibilidad
     * @param inicio acepta un dato tipo Date
     * @param fin acepta un dato tipo Date
     * @return devuelve un objeto lista
     */
    public List<Habitacion> listarDisponibles(Date inicio, Date fin){
        return obj.listarDisponibles(inicio, fin);
    }//cierre del metodo listarDisponibles
    /**
     * Metodo que permite llamar al metodo para listar la cantidad de habitaciones disponibles
     * @param inicio acepta un dato tipo Date
     * @param fin acepta un dato tipo Date
     * @param codigo acepta un dato tipo String
     * @return devuelve un objeto lista
     */
    public Long cantidadDisponibles(Date inicio, Date fin, String codigo){
        return obj.cantidadDisponibles(inicio, fin, codigo);
    }//cierre del metodo cantidadDisponibles
    
    /**
     * Metodo que obtener las habitaciones que estan disponibles en dentro del rango de fecha
     * @param inicio acepta un dato tipo Date
     * @param fin acepta un dato tipo Date
     * @return objeto el numero de habitaciones disponibles en el rengo de fechas
     */
    public Long cantidadDisponibles(Date inicio, Date fin){
        return obj.cantidadDisponibles(inicio, fin);
    }//cierre del meodo cantidadDisponibles
}//cierre de la clase ServicioHabitacion
