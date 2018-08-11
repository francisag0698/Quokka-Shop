/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.servicio;
import java.util.List;
import od.controlador.dao.HabitacionDao;
import od.modelo.Habitacion;

/**
 *
 * @author Dennis
 */
public class ServicioHabitacion {
    private HabitacionDao obj;

    public ServicioHabitacion() {

    }

    public boolean guardar() {
        return this.obj.guardar();
    }

    public Habitacion obtenerHabitacion() {
        return obj.getHabitacion();
    }

    public Habitacion obtenerHabitacion(Long id) {
        return obj.obtener(id);
    }

    public List<Habitacion> listado() {
        return obj.listar();
    }
}
