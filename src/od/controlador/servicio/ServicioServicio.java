/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.servicio;

import java.util.List;
import od.controlador.dao.ServicioDao;
import od.modelo.Reservacion;
import od.modelo.Servicio;

/**
 * Clase que permite utilizar los metodos del ServicioDao
 * @author Agreda Francisco
 * @author Macas Dennis
 * @author Ortega César
 * @version JDK 1.8
 */
public class ServicioServicio {
    //inicializacion de una variable privada
    private ServicioDao obj = new ServicioDao();
    /**
     * Permite extraer un metodo del getServicio
     * @return devuelve un objeto de Servicio
     */
    public Servicio getServicio(){
        return obj.getServicio();
    }//Cierre del metodo getServicio
    
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
    public List<Servicio>todos(){
        return obj.listar();
    }//Cierre del metodo todos
    
    /**
     * Metodo que permite obtener el id del servicio
     * @param id acepta un dato tipo Long
     * @return devuelve un objeto  obtener
     */
    public Servicio obtener(Long id){
        return obj.obtener(id);
    }//Cierre del metodo obtener
    
    /**
     * Permite modificar un servicio
     * @param servicio acepta un dato servicio de tipo Servicio
     */
    public void fijarServicio(Servicio servicio){
        obj.setServicio(servicio);
    }//Cierre del fijarServicio
    
    /**
     * Metodo que permite llamar el metodo listar del ServicioDao
     * @param texto que acepta un dato tipo String
     * @return devuelve un objeto tipo lista
     */
    public List<Servicio>listarBusqueda(String texto){
        return obj.listarBusqueda(texto);
    }//cierre del metodo listarBusqueda
}//Cierre de la clase SErvicioServicio
