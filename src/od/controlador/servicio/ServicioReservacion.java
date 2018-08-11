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
 *
 * @author Dennis
 */
public class ServicioReservacion {
    private ReservacionDao obj;

    public ServicioReservacion() {

    }

    public boolean guardar() {
        return this.obj.guardar();
    }

    public Reservacion obtenerReservacion() {
        return obj.getReservacion();
    }

    public Reservacion obtenerReservacion(Long id) {
        return obj.obtener(id);
    }

    public List<Reservacion> listado() {
        return obj.listar();
    }
}
