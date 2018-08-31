/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;
import od.modelo.Detalle;

/**
 *Clase que permite utilizar los metodos del adaptador
 * @author Agreda Francisco
 * @author Macas Dennis
 * @author Ortega César
 * @version JDK 1.8
 */
public class DetalleDao extends AdaptadorDao<Detalle>{
    //inicializacion de una variable privada
    private Detalle detalle;
    /**
     * Constructor que encapsula la transferencia de datos desde el modelo detalle
     */
    public DetalleDao() {
        super(Detalle.class);
    }//Cierre del constructor
    
    /**
     * Permite obtener un nuevo detalle
     * @return devueve un detalle
     */
    public Detalle getDetalle() {
        if(detalle==null)
           detalle= new Detalle();
        return detalle;
    }// cierre de getDetalle
    
    /**
     * Permite modificar un detalle
     * @param detalle acepta un dato detalle de tipo Detalle
     */
   public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }//Cierre del setDetalle
   
   /**
     * Constructor para la variable detalle
     * @param detalle acepta un dato detalle de tipo Detalle 
     */
    public void Detalle(Detalle detalle) {
        this.detalle = detalle;
    }//Cierre del constructor detalle
    
    /**
     * Metodo que permite guardar y modificar el detalle
     * @return devuelve un valor de tipo booleano
     */
    public boolean guardar (){
        boolean verificar = false;
        try{
            getManager().getTransaction().begin();
            if(detalle.getId_detalle()!=null){
                modificar(detalle);
            }else{
                guardar(detalle);
            }
            getManager().getTransaction().commit();
            verificar = true;
        }catch(Exception e){
            System.out.println("No se ha podido guardar o modificar " + e);
        }
        return verificar;
    }//cierre del metodo guardar
}//Cierre de la clase DetalleDao
