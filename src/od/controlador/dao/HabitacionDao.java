/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import od.modelo.Habitacion;

/**
 * Clase que permite utilizar los metodos del adaptador
 * @author Agreda Francisco
 * @author Macas Dennis
 * @author Ortega César
 * @version JDK 1.8
 */
public class HabitacionDao extends AdaptadorDao<Habitacion>{
    //inicializacion de una variable privada
    private Habitacion habitacion;

    /**
     * Constructor que encapsula la transferencia de datos desde el modelo habitacion
     */
    public HabitacionDao() {
        super(Habitacion.class);
    }//cierre del constructor habitacion
    
     /**
     * Permite obtener una nueva habitacion
     * @return devueve una habitacion
     */
    public Habitacion getHabitacion() {
        if(habitacion==null)
           habitacion= new Habitacion();
        return habitacion;
    }//Cierre del getDetalle
    
    /**
     * Permite modificar una habitacion
     * @param habitacion acepta un dato habitacion de tipo Habitacion
     */
   public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }//Cierre del setHabitacion
   
   /**
     * Constructor para la variable habitacion
     * @param habitacion acepta un dato habitacion de tipo Habitacion 
     */
    public void Habitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }//Cierre del constructor habitacion
    
    /**
     * Metodo que permite guardar y modificar las habitaciones
     * @return devuelve un valor de tipo booleano
     */
    public boolean guardar (){
        boolean verificar = false;
        try{
            getManager().getTransaction().begin();
            if(habitacion.getId_habitacion()!=null){
                modificar(habitacion);
            }else{
                guardar(habitacion);
            }
            getManager().getTransaction().commit();
            verificar = true;
        }catch(Exception e){
            System.out.println("No se ha podido guardar o modificar " + e);
        }
        return verificar;
    }//cierre del metodo guardar
    /**
     * Metodo para listar las habitaciones por tipo
     * @param tipo acepta un dato tipo String
     * @return devuelve una lista
     
     */
    public List<Habitacion> listarTipo(String tipo){
        List<Habitacion> lista= new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT h FROM Habitacion h where h.tipo = :tipo");  
            q.setParameter("tipo", tipo);
            lista = q.getResultList();
        } catch (Exception e) {
            System.out.println("error"+e);
        }
        return lista;
    }//cierre del metodo listarTipo
    
    /**
     * Metodo para listar las habitaciones por estado
     * @param estado acepta un dato tipo booelan
     * @return devuelve una lista
     */
    public List<Habitacion> listarEstado(Boolean estado){
       List<Habitacion> lista= new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT h FROM Habitacion h where h.estado = :estado");  
            q.setParameter("estado", estado);
            lista = q.getResultList();
        } catch (Exception e) {
            System.out.println("error"+e);
        }
        return lista;
    }//cierre del metodo listarEstado
    
    /**
     * Metodo para listar las habitaciones por busqueda
     * @param texto acepta un dato tipo String
     * @return devuelve una lista
     */
    public List<Habitacion>listarBusqueda(String texto){
        List<Habitacion> lista= new ArrayList<>();
        try {
            Query q = getManager()
                    .createQuery("SELECT h FROM Habitacion h where (LOWER(h.nombre)LIKE CONCAT('%', :texto, '%'))");//lower es minusculas
            q.setParameter("texto", texto);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }//cierre del metodo listarBusqueda
    
    /**
     * Metodo para listar las habitaciones por buscadas por tipo
     * @param tipo acepta un dato tipo String
     * @param texto acepta un dato tipo String
     * @return devuelve una lista
     */
    public List<Habitacion> listarBusquedaTipo(String tipo,String texto){
       List<Habitacion> lista= new ArrayList<>();
        try {
            Query q = getManager()
                    .createQuery("SELECT h FROM Habitacion h where (LOWER(h.nombre)LIKE CONCAT('%', :texto, '%')) and h.tipo = :tipo");
            q.setParameter("texto", texto);
            q.setParameter("tipo", tipo);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }//cierre del metodo listarBusquedaTipo
    
    /**
     * Metodo para listar las habitaciones por buscadas por estado
     * 
     * @param texto acepta un dato tipo String
     * @return devuelve una lista
     */
    public List<Habitacion> listarBusquedaCodigo(String texto){
        List<Habitacion> lista= new ArrayList<>();
        try {
            Query q = getManager()
                    .createQuery("SELECT h FROM Habitacion h WHERE (LOWER(h.codigo) LIKE CONCAT('%', :texto, '%'))");
            q.setParameter("texto", texto);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }//cierre del metodo listarBusquedaEstado
    
    /**
     * Metodo para listar las habitaciones disponibles
     * @param inicio acepta un dato tipo Date
     * @param fin acepta un dato tipo Date
     * @return devuelve una lista
     */
    public List<Habitacion> listarDisponibles(Date inicio, Date fin){
       List<Habitacion> lista= new ArrayList<>();
       try {
            Query q = getManager()
                    .createQuery("SELECT h FROM Habitacion h "
                            + "WHERE h.cantidad > (SELECT COUNT(r) as INT FROM Reservacion r "
                            + "WHERE r.habitacion.id_habitacion = h.id_habitacion "
                            + "AND ((r.fecha_inicio >= :inicio AND r.fecha_fin <= :fin) "
                            + "OR (r.fecha_inicio <= :inicio AND r.fecha_fin >= :fin)))");
            q.setParameter("inicio", inicio);
            q.setParameter("fin", fin);
            lista = q.getResultList();
        } catch (Exception e) {
            System.out.println("HabitacionDao | Met: listarDisponiples " + e);
        }
       return lista;
    }//cierre del metodo listarDisponibles
    
    /**
     * Metodo para listar las habitaciones disponibles
     * @param inicio acepta un dato tipo Date
     * @param fin acepta un dato tipo Date
     * @param codigo acepta un dato tipo String
     * @return devuelve un numero grande
     */
    public Long cantidadDisponibles(Date inicio, Date fin, String codigo){
        Long cantidad = new Long(0);
        try {
            Query q = getManager()
                    .createQuery("SELECT h.cantidad - (SELECT COUNT(r) as INT FROM Reservacion r "
                            + "WHERE r.habitacion.codigo = :codigo "
                            + "AND ((r.fecha_inicio >= :inicio AND r.fecha_fin <= :fin) "
                            + "OR (r.fecha_inicio <= :inicio AND r.fecha_fin >= :fin))) AS INT "
                            + "FROM Habitacion h WHERE h.codigo = :codigo");
            q.setParameter("inicio", inicio);
            q.setParameter("fin", fin);
            q.setParameter("codigo", codigo);
            cantidad = (Long) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("HabitacionDao | Met: cantidadDisponibles - " + e);
        }
        return cantidad;
    }//cierre del metodo cantidadDisponibles
    /**
     * Metodo para listar las habitaciones disponibles 
     * @param inicio acepta un dato tipo Date
     * @param fin acepta un dato tipo Date
     * @return devuelve un numero grande
     */
    public Long cantidadDisponibles(Date inicio, Date fin){
        Long cantidad = new Long(0);
        try {
            Query q = getManager()
                    .createQuery("SELECT SUM(h.cantidad - (SELECT COUNT(r) as INT FROM Reservacion r "
                            + "WHERE ((r.fecha_inicio >= :inicio AND r.fecha_fin <= :fin) "
                            + "OR (r.fecha_inicio <= :inicio AND r.fecha_fin >= :fin)) AND h.id_habitacion = r.habitacion.id_habitacion)) "
                            + "FROM Habitacion h");
            q.setParameter("inicio", inicio);
            q.setParameter("fin", fin);
            cantidad = (Long) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("HabitacionDao | Met: cantidadDisponibles2 - " + e);
        }
        return cantidad;
    }//cierre del metodo cantidadDisponibles
}//cierre del la clase HabitacionDao
