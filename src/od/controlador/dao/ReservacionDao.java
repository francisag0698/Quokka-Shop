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
import od.modelo.Reservacion;

/**
 * Clase que permite utilizar los metodos del adaptador
 * @author Agreda Francisco
 * @author Macas Dennis
 * @author Ortega César
 * @version JDK 1.8
 */
public class ReservacionDao extends AdaptadorDao<Reservacion>{
    //inicializacion de una variable privada
     private Reservacion reservacion;
     /**
     * Constructor que encapsula la transferencia de datos desde el modelo Reservacion
     */
     public ReservacionDao() {
        super(Reservacion.class);
    }//Cierre del constructor

     /**
     * Permite obtener una nueva reservacion
     * @return devueve una reservacion
     */
    public Reservacion getReservacion() {
        if(reservacion==null)
           reservacion= new Reservacion();
        return reservacion;
    }//Cierre de getReservacion
    
/**
     * Permite modificar una Reservacion
     * @param reservacion acepta un dato reservacion de tipo Reservacion
     */
    public void setReservacion(Reservacion reservacion) {
        this.reservacion = reservacion;
    }//Cierre de setReservacion
    
    /**
     * Metodo que permite guardar y modificar reservacion
     * @return devuelve un valor de tipo booleano
     */
    public boolean guardar (){
        boolean verificar = false;
        try{
            getManager().getTransaction().begin();
            if(reservacion.getId_reservacion()!=null){
                modificar(reservacion);
            }else{
                guardar(reservacion);
            }
            getManager().getTransaction().commit();
            verificar = true;
        }catch(Exception e){
            System.out.println("ReservacionDao | Guardar: " + e);
        }
        return verificar;
    }//Cierre del metodo guardar
    
    public List<Reservacion> listarPorPersona(Long id){
        List<Reservacion> lista = new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT r FROM Reservacion r WHERE r.persona.id_persona = :id");
            q.setParameter("id", id);
            lista = q.getResultList();
        } catch (Exception e) {
            System.out.println("ReservacionDao | listarPorPersona: " + e);
        }
        return lista;
    }
    
    public List<Reservacion> listarPorPersonaActivo(Long id){
        List<Reservacion> lista = new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT r FROM Reservacion r WHERE r.persona.id_persona = :id AND r.estado = :estado");
            q.setParameter("id", id);
            q.setParameter("estado", true);
            lista = q.getResultList();
        } catch (Exception e) {
            System.out.println("ReservacionDao | listarPorPersonaActivo: " + e);
        }
        return lista;
    }

    /**
     * Metodo que permite listar Reservaciones por tipo
     * @param tipo acepta un dato reservacion de tipo String
     * @return devuelve una lista con las reservaciones
     */
    public List<Reservacion>listarTipo(Boolean tipo){
        List<Reservacion> lista= new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT p FROM Reservacion p where p.estado = :tipo");  
            q.setParameter("tipo", tipo);
            lista = q.getResultList();
        } catch (Exception e) {
            System.out.println("ReservacionDao | Listar por Tipo: " + e);
        }
        return lista;
    }//cierre del metodo listarTipo
      
    public List<Reservacion>listarTipo(Boolean tipo, Long id){
        List<Reservacion> lista= new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT p FROM Reservacion p WHERE p.estado = :tipo AND p.persona.id_persona = :id");  
            q.setParameter("tipo", tipo);
            q.setParameter("id", id);
            lista = q.getResultList();
        } catch (Exception e) {
            System.out.println("ReservacionDao | Listar por Tipo (Rol): " + e);
        }
        return lista;
    }

      /**
     * Metodo que permite listar Reservaciones por busqueda
     * @param texto acepta un dato texto de tipo String
     * @return devuelve una lista con las reservaciones que coinciden con la busqueda
     */
    public List<Reservacion>listarBusqueda(String texto){
        List<Reservacion> lista= new ArrayList<>();
        try {
            Query q = getManager()
                    .createQuery("SELECT r FROM Reservacion r WHERE ((LOWER(r.persona.apellidos)LIKE CONCAT(:texto, '%')) OR (LOWER(r.persona.nombres)LIKE CONCAT(:texto, '%')))");//lower es minusculas
            q.setParameter("texto", texto);
            lista = q.getResultList();
        } catch (Exception e) {
            System.out.println("ReservacionDao | Listar Busqueda: " + e);
        }
        return lista;
    }//Cierre del metodo listarBusqueda
    
    public List<Reservacion>listarBusqueda(String texto, Long id){
        List<Reservacion> lista= new ArrayList<>();
        try {
            Query q = getManager()
                    .createQuery("SELECT r FROM Reservacion r WHERE ((LOWER(r.persona.apellidos)LIKE CONCAT(:texto, '%')) OR (LOWER(r.persona.nombres)LIKE CONCAT(:texto, '%'))) AND r.persona.id_persona = :id");
            q.setParameter("texto", texto);
            q.setParameter("id", id);
            lista = q.getResultList();
        } catch (Exception e) {
            System.out.println("ReservacionDao | Listar Busqueda: " + e);
        }
        return lista;
    }
    
     /**
     * Metodo que permite listar Reservaciones por busqueda y tipo
     * @param tipo acepta un dato tipo de tipo String
     * @param texto acepta un dato texto de tipo String
     * @return devuelve una lista con las reservaciones que coinciden con la busqueda
     */
    public List<Reservacion> listarBusquedaTipo(Boolean tipo,String texto){
        List<Reservacion> listado= new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT r FROM Reservacion r where ((LOWER(r.persona.apellidos)LIKE CONCAT(:texto, '%')) OR (LOWER(r.persona.nombres)LIKE CONCAT(:texto, '%'))) and r.estado = :tipo");
            q.setParameter("tipo", tipo);
            q.setParameter("texto", texto);
            listado = q.getResultList();
        } catch (Exception e) {
            System.out.println("ReservacionDao | Listar Busqueda por Tipo: " + e);
        }
        
        return listado;
    }//cierre del metodo listarBusquedaTipo
    
    public List<Reservacion> listarBusquedaTipo(Boolean tipo,String texto, Long id){
        List<Reservacion> listado= new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT r FROM Reservacion r WHERE ((LOWER(r.persona.apellidos)LIKE CONCAT(:texto, '%')) OR (LOWER(r.persona.nombres)LIKE CONCAT(:texto, '%'))) AND r.estado = :tipo AND r.persona.id_persona = :id");
            q.setParameter("tipo", tipo);
            q.setParameter("texto", texto);
            q.setParameter("id", id);
            listado = q.getResultList();
        } catch (Exception e) {
            System.out.println("ReservacionDao | Listar Busqueda por Tipo: " + e);
        }
        
        return listado;
    }
    
    /**
     * Metodo que permite listar las Reservaciones en orden ascendente
     * @param orden acepta un dato orden de tipo String
     * @return devuelve una lista con las reservaciones que coinciden con la busqueda
     */
     public List<Reservacion> ordenAscendente(String orden){
        orden = (orden.equals("Fecha")) ? "r.fecha" : "r.persona.nombres";
        List<Reservacion> listado= new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT r FROM Reservacion r ORDER BY "+ orden +"  ASC");
            listado = q.getResultList();
        } catch (Exception e) {
            System.out.println("ReservacionDao | Orden Ascendente: " + e);
        }
        return listado;
    }//cierre del metodo ordenAscendente
     
    public Long nroReservasActivas(){
        Long cantidad = new Long(0);
        try {
            Query q = getManager().createQuery("SELECT COUNT(r) FROM Reservacion r WHERE r.estado = :estado");
            q.setParameter("estado", true);
            cantidad = (Long) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("ReservacionDao | Nro de Reservas Activas: " + e);
        }
        return cantidad;
    }
    /**
     * Metodo que permite contabilizar las reservas activas de un cliente
     * @param id acepta un dato texto de tipo Long 
     * @return devuelve un numero de las reservas que esta activas
     */
    public Long nroReservasActivas(Long id){
        Long cantidad = new Long(0);
        try {
            Query q = getManager().createQuery("SELECT COUNT(r) FROM Reservacion r WHERE r.estado = :estado AND r.persona.id_persona = :id");
            q.setParameter("estado", true);
            q.setParameter("id", id);
            cantidad = (Long) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("ReservacionDao | Nro de Reservas Activas: " + e);
        }
        return cantidad;
    }//cierre del metodo nroReservasActivas
    
    /**
     * Metodo que permite contabilizar las reservas de un cliente
     * @param id acepta un dato texto de tipo Long 
     * @return devuelve un numero de las reservas totales del cliente
     */
    public Long nroReservasTotalesCliente(Long id){
        Long cantidad = new Long(0);
        try {
            Query q = getManager().createQuery("SELECT COUNT(r) FROM Reservacion r WHERE r.persona.id_persona = :id");
            q.setParameter("id", id);
            cantidad = (Long) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("ReservacionDao | Nro de Reservas Activas: " + e);
        }
        return cantidad;
    }//cierre del metodo nroReservasTotalesCliente
}//cierre de la clase ReservacionDao
