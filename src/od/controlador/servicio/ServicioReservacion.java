/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.servicio;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import od.controlador.dao.ReservacionDao;
import od.modelo.Reservacion;

/**
 *
 * @author Dennis
 */
public class ServicioReservacion {

    private ReservacionDao obj = new ReservacionDao();

    public Reservacion getReservacion() {
        return obj.getReservacion();
    }

    public boolean guardar() {
        return obj.guardar();
    }

    public List<Reservacion> todos() {
        return obj.listar();
    }

    public Reservacion obtener(Long id) {
        return obj.obtener(id);
    }

    public void fijarReservacion(Reservacion reservacion) {
        obj.setReservacion(reservacion);
    }

    public void crearReserva(){
        ServicioReservacion sr = new ServicioReservacion();
        sr.getReservacion().setEstado("Pendiente");
        sr.getReservacion().setFecha(new Date());
        sr.getReservacion().setPago_total(154.50);
        sr.getReservacion().setPersona(new ServicioPersona().ObtenerPersonaCedula("1105335010"));
        
        ServicioDetalle sd = new ServicioDetalle();
        sd.getDetalle().setAdultos(2);
        sd.getDetalle().setMenores(1);
        try {
            sd.getDetalle().setFecha_inicio(new SimpleDateFormat("dd/MM/yyyy").parse("25/08/2018"));
            sd.getDetalle().setFecha_fin(new SimpleDateFormat("dd/MM/yyyy").parse("28/08/2018"));
        } catch (Exception e) {
        }
        sd.getDetalle().setPago_total(125.50);
        sd.getDetalle().setReservacion(sr.getReservacion());
        
        sr.getReservacion().setDetalle(sd.getDetalle());
        sr.guardar();
    }
}
