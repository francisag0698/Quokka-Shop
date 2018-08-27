/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.servicio;
import java.util.List;
import od.controlador.dao.DetalleDao;
import od.modelo.Detalle;

/**
* Clase que permite utilizar los metodos del ServicoDetalle
 * @author Agreda Francisco
 * @author Macas Dennis
 * @author Ortega César
 * @version JDK 1.8
 */
public class ServicioDetalle {
    //inicializacion de una variable privada
    private DetalleDao obj = new DetalleDao();
    /**
     * Permite extraer un metodo del getDetalle
     * @return devuelve un objeto de Detalle
     */
    public Detalle getDetalle(){
        return obj.getDetalle();
    }//cierre del metodo getDetalle
    
    /**
     * Metodo que llamar el metodo guardar
     * @return devuelve un objeto guardar
     */
    public boolean guardar(){
        return obj.guardar();
    }//cierre del metodo guardar
    
    /**
     * Metodo que permite llamar el metodo listar de AdaptadorDao
     * @return devuelve un objeto tipo lista
     */
    public List<Detalle>todos(){
        return obj.listar();
    }//cierre del metodo todos
    
    /**
     * Metodo que permite obtener el id de Detalle
     * @param id acepta un dato tipo Long
     * @return devuelve un objeto  obtener
     */
    public Detalle obtener(Long id){
        return obj.obtener(id);
    }//cierre del metodo obtener
    /**
     * Permite modificar un detalle
     * @param detalle acepta un dato detalle de tipo Detalle
     */
    public void fijarDetalle(Detalle detalle){
        obj.setDetalle(detalle);
    }//ciere del metodo fijarDetalle
}//cierr de la clase ServicioDetallae
