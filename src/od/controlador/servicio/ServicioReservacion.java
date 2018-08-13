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
    private ReservacionDao obj = new ReservacionDao();
    public Reservacion getReservacion(){
        return obj.getReservacion();
    }
    public boolean guardar(){
        return obj.guardar();
    }
    public List<Reservacion>todos(){
        return obj.listar();
    }
    public Reservacion obtener(Long id){
        return obj.obtener(id);
    }
    public void fijarReservacion(Reservacion reservacion){
        obj.setReservacion(reservacion);
    }
}
