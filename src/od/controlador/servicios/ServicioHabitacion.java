/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.servicios;
import java.util.List;
import od.controlador.daos.HabitacionDao;
import od.modelo.Habitacion;

/**
 *
 * @author Dennis
 */
public class ServicioHabitacion {
    private HabitacionDao obj = new HabitacionDao();
    
    public Habitacion getHabitacion(){
        return obj.getHabitacion();
    }
    
    public boolean guardar(){
        return obj.guardar();
    }
    
    public List<Habitacion>todos(){
        return obj.listar();
    }
    
    public Habitacion obtener(Long id){
        return obj.obtener(id);
    }
    
    public void fijarCuenta(Habitacion habitacion){
        obj.setHabitacion(habitacion);
    }
}
