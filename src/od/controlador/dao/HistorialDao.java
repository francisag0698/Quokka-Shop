/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import od.modelo.Historial;
/**
 * Clase que permite utilizar los metodos del adaptador
 * @author Agreda Francisco
 * @author Macas Dennis
 * @author Ortega César
 * @version JDK 1.8
 */
public class HistorialDao extends AdaptadorDao<Historial>{
    //inicializacion de una variable privada
    private Historial historial;

    /**
     * Constructor que encapsula la transferencia de datos desde el modelo Historial
     */
    public HistorialDao() {
        super(Historial.class);
    }//Cierre del constructor
    
    /**
     * Permite obtener un nuevo historial
     * @return devueve un historial
     */
    public Historial getHistorial() {
        if(historial==null)
           historial= new Historial();
        return historial;
    }//Cierre del getHistorial
    
     /**
     * Permite modificar un historial
     * @param historial acepta un dato historial de tipo Historial
     */
   public void setHistorial(Historial historial) {
        this.historial = historial;
    }//Cierre del setHistorial
   
   /**
     * Constructor para la variable cuenta
     * @param historial acepta un dato historial de tipo Historial 
     */
    public void Historial(Historial historial) {
        this.historial = historial;
    }//Cierre del constructor historial
    
    /**
     * Metodo que permite guardar y modificar la hiztorial
     * @return devuelve un valor de tipo booleano
     */
    public boolean guardar (){
        boolean verificar = false;
        try{
            getManager().getTransaction().begin();
            if(historial.getId_historial()!=null){
                modificar(historial);
            }else{
                guardar(historial);
            }
            getManager().getTransaction().commit();
            verificar = true;
        }catch(Exception e){
            System.out.println("No se ha podido guardar o modificar " + e);
        }
        return verificar;
    }//cierre del metodo guardar
    /**
     * Metodo para listar las personas disponibles 
     * @param id acepta un dato tipo Long
     * @return devuelve un numero grande
     */
    public List<Historial> listarPorPersona(Long id){
        List<Historial> lista = new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT h FROM Historial h WHERE h.persona.id_persona = :id");
            q.setParameter("id", id);
            lista = q.getResultList();
        } catch (Exception e) {
            System.out.println("Historial Dao | Met: listarPorPersona: " + e);
        }
        return lista;
    }//cierre del metodo listarPorPersona
}//cierre de la clase HistorialDao
