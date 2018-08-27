/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import od.modelo.Reservacion;
import od.modelo.Servicio;

/**
* Clase que permite utilizar los metodos del adaptador
 * @author Agreda Francisco
 * @author Macas Dennis
 * @author Ortega César
 * @version JDK 1.8
 */
public class ServicioDao extends  AdaptadorDao<Servicio>{
    //inicializacion de una variable privada
    
    private Servicio servicio;
    
    /**
     * Constructor para obtener las variables de modelo Servicio
     */
    public ServicioDao() {
        super(Servicio.class);
    }//Cierre del constructor

    /**
     * Permite obtener una nueva Reservacion
     * @return devueve una reservacion
     */    
    public Servicio getServicio() {
        if(servicio==null)
           servicio= new Servicio();
        return servicio;
    }//Cierre de getServicio
    
    /**
     * Permite modificar una persona
     * @param servicio acepta un dato servicio de tipo Servicio
     */

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }//cierre de setServicio
    
    /**
     * Metodo que permite guardar y modificar servicio
     * @return devuelve un valor de tipo booleano
     */    
    public boolean guardar (){
        boolean verificar = false;
        try{
            getManager().getTransaction().begin();
            if(servicio.getId_servicio()!=null){
                modificar(servicio);
            }else{
                guardar(servicio);
            }
            getManager().getTransaction().commit();
            verificar = true;
        }catch(Exception e){
            System.out.println("No se ha podido guardar o modificar " + e);
        }
        return verificar;
    }//cierre del metodo guardar
    /**
     * Metodo que permite listar los servicios por busqueda
     * @param texto acepta un dato orden de tipo String
     * @return devuelve una lista con los servicios que coinciden con la busqueda
     */
    public List<Servicio>listarBusqueda(String texto){
        List<Servicio> lista= new ArrayList<>();
        try {
            Query q = getManager()
                    .createQuery("SELECT s FROM Servicio s WHERE (LOWER(s.nombre_servicio) LIKE CONCAT('%', :texto, '%'))");
            q.setParameter("texto", texto);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }//cierre del metodo listarBusqueda
}//cierre de la clase servicioBusqueda
