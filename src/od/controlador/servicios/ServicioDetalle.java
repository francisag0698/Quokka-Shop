/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.servicios;
import java.util.List;
import od.controlador.daos.DetalleDao;
import od.modelo.Detalle;

/**
 *
 * @author Dennis
 */
public class ServicioDetalle {
    private DetalleDao obj = new DetalleDao();
    
    public Detalle getDetalle(){
        return obj.getDetalle();
    }
    
    public boolean guardar(){
        return obj.guardar();
    }
    
    public List<Detalle>todos(){
        return obj.listar();
    }
    
    public Detalle obtener(Long id){
        return obj.obtener(id);
    }
    
    public void fijarCuenta(Detalle detalle){
        obj.setDetalle(detalle);
    }
}
