/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.servicio;

import java.util.List;
import od.controlador.dao.ReservacionDao;
import od.modelo.Reservacion;

/**
 * Clase que permite utilizar los metodos del ServicioDao
 * @author Agreda Francisco
 * @author Macas Dennis
 * @author Ortega César
 * @version JDK 1.8
 */
public class ServicioReservacion {
    //inicializacion de una variable privada
    private ReservacionDao obj = new ReservacionDao();

    /**
     * Permite extraer un metodo del getReservacion
     * @return devuelve un objeto de Reservacion
     */
    public Reservacion getReservacion() {
        return obj.getReservacion();
    }//Cierre del metodo getReservacion

    /**
     * Metodo que llamar el metodo guardar
     * @return devuelve un objeto tipo booelan
     */
    public boolean guardar() {
        return obj.guardar();
    }//Cierre del metodo guardar

    /**
     * Metodo que permite llamar el metodo listar de AdaptadorDao
     * @return devuelve un objeto tipo lista
     */
    public List<Reservacion> todos() {
        return obj.listar();
    }//Cierre del metodo todos

    /**
     * Metodo que permite obtener el id del Reservaciones
     * @param id acepta un dato tipo Long
     * @return devuelve un objeto  obtener
     */
    public Reservacion obtener(Long id) {
        return obj.obtener(id);
    }//cierre del metodo obtener

    /**
     * Permite modificar un Reservaciones
     * @param reservacion acepta un dato reservacion de tipo Reservacion
     */
    public void fijarReservacion(Reservacion reservacion) {
        obj.setReservacion(reservacion);
    }//cierre del metodo fijarReservacion
    
    /**
     * Metodo que permite llamar el metodo listar del listarPorPersona
     * @param id que acepta un dato tipo Lomg
     * @return devuelve un objeto tipo lista
     */
    public List<Reservacion> listarPorPersona(Long id){
        return obj.listarPorPersona(id);
    }//cierre del metodo listarPorPersona
    
    /**
     * Metodo que permite llamar el metodo listar del listarPorPersonaActivo
     * @param id que acepta un dato tipo Lomg
     * @return devuelve un objeto tipo lista
     */
    public List<Reservacion> listarPorPersonaActivo(Long id){
        return obj.listarPorPersonaActivo(id);
    }//cierre del metodo listarPorPersonaActivo

    /**
     * Metodo que permite llamar el metodo listar del listarBusqueda
     * @param texto que acepta un dato tipo String
     * @return devuelve un objeto tipo lista
     */
    public List<Reservacion> listarBusqueda(String texto){
        return obj.listarBusqueda(texto);
    }//cierre del metodo listarBusqueda
    
    /**
     * Metodo que permite llamar el metodo listar del listarBusqueda
     * @param texto que acepta un dato tipo String
     * @param id que acepta un dato tipo Long
     * @return devuelve un objeto tipo lista
     */
    public List<Reservacion>listarBusqueda(String texto, Long id){
        return obj.listarBusqueda(texto, id);
    }//cierre del metodo listarBusqueda
    
    /**
     * Metodo que permite llamar el metodo listar por tipo de listarTipo
     * @param tipo que acepta un dato tipo String
     * @return devuelve un objeto tipo lista
     */
    public List<Reservacion> listarTipo(Boolean tipo){
        return obj.listarTipo(tipo);
    }//cierre del metodo listarTipo
    
    /**
     * Metodo que permite llamar el metodo listar por tipo de listarTipo
     * @param tipo que acepta un dato tipo String
     * @param id que acepta un dato tipo Long
     * @return devuelve un objeto tipo lista
     */
    public List<Reservacion>listarTipo(Boolean tipo, Long id){
        return obj.listarTipo(tipo, id);
    }//cierre del metodo listarTipo
    
    /**
     * Metodo que permite llamar el metodo listar por busqueda por tipo del listarBusquedaTipo
     * @param tipo que acepta un dato tipo Boolean
     * @param texto que acepta un dato tipo String
     * @return devuelve un objeto tipo lista
     */    
    public List<Reservacion> listarBusquedaTipo(Boolean tipo,String texto){
        return obj.listarBusquedaTipo(tipo, texto);
    }//cierro del metodo listarBusquedaTipo
    
    /**
     * Metodo que permite llamar el metodo listar por busqueda por tipo del listarBusquedaTipo
     * @param tipo que acepta un dato tipo String
     * @param texto que acepta un dato tipo String
     * @param id que acepta un dato tipo Long
     * @return devuelve un objeto tipo lista
     */
    public List<Reservacion> listarBusquedaTipo(Boolean tipo,String texto, Long id){
        return obj.listarBusquedaTipo(tipo, texto, id);
    }//cierre del metodo listarBusquedaTipo
    
    /**
     * Metodo que permite llamar el metodo listar por orden ascendente del ServicioReservacion
     * @param orden que acepta un dato tipo String
     * @return devuelve un objeto tipo lista
     */    
    public List<Reservacion> ordenAscendente(String orden){
        return obj.ordenAscendente(orden);
    }//cierre del metodo ordenAscendente
    
    public List<Reservacion> ordenAscendente(String orden, Long id){
        return obj.ordenAscendente(orden, id);
    }
    
    /**
     * Metodo que contabilizar el numero de reservas activas
     * @return devuelve el numero de reservas activas
     */
    public Long nroReservasActivas(){
        return obj.nroReservasActivas();
    }//cierre del metodo nroReservasActivas
    
    /**
     * Metodo que contabilizar el numero de reservas activas del cliente     * 
     * @param id acepta un valor del tipo Long
     * @return devuelve el numero de reservas activas del cliente
     */
    public Long nroReservasActivas(Long id){
        return obj.nroReservasActivas(id);
    }//cierre del metodonroReservasActivas
    
    /**
     * Metodo que contabilizar el numero de reservas totales del cliente     * 
     * @param id acepta un valor del tipo Long
     * @return devuelve el numero de reservas totales del cliente
     */
    public Long nroReservasTotalesCliente(Long id){
        return obj.nroReservasTotalesCliente(id);
    }//cierre del metodo nroReservasTotalesCliente
}//cierre la clase ServicioReservacion
