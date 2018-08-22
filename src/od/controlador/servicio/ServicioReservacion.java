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
        sr.getReservacion().setEstado("Pagado");
        sr.getReservacion().setFecha(new Date());
        sr.getReservacion().setPago_total(154.50);
        sr.getReservacion().setPersona(new ServicioPersona().ObtenerPersonaCedula("1900858034"));
        
        ServicioDetalle sd = new ServicioDetalle();
        sd.getDetalle().setAdultos(1);
        sd.getDetalle().setMenores(0);
        try {
            sd.getDetalle().setFecha_inicio(new SimpleDateFormat("dd/MM/yyyy").parse("14/07/2018"));
            sd.getDetalle().setFecha_fin(new SimpleDateFormat("dd/MM/yyyy").parse("28/08/2018"));
        } catch (Exception e) {
        }
        sd.getDetalle().setPago_total(125.50);
        sd.getDetalle().setReservacion(sr.getReservacion());
        
        sr.getReservacion().setDetalle(sd.getDetalle());
        sr.guardar();
        //////////////////////////////////////////
        ServicioReservacion sr1 = new ServicioReservacion();
        sr1.getReservacion().setEstado("Pendiente");
        sr1.getReservacion().setFecha(new Date());
        sr1.getReservacion().setPago_total(154.50);
        sr1.getReservacion().setPersona(new ServicioPersona().ObtenerPersonaCedula("0734893456"));
        
        ServicioDetalle sd1 = new ServicioDetalle();
        sd1.getDetalle().setAdultos(3);
        sd1.getDetalle().setMenores(3);
        try {
            sd1.getDetalle().setFecha_inicio(new SimpleDateFormat("dd/MM/yyyy").parse("25/05/2018"));
            sd1.getDetalle().setFecha_fin(new SimpleDateFormat("dd/MM/yyyy").parse("01/06/2018"));
        } catch (Exception e) {
        }
        sd1.getDetalle().setPago_total(125.50);
        sd1.getDetalle().setReservacion(sr1.getReservacion());
        
        sr1.getReservacion().setDetalle(sd1.getDetalle());
        sr1.guardar();
        //////////////////////////////////////////
        ServicioReservacion sr2 = new ServicioReservacion();
        sr2.getReservacion().setEstado("Pagado");
        sr2.getReservacion().setFecha(new Date());
        sr2.getReservacion().setPago_total(154.50);
        sr2.getReservacion().setPersona(new ServicioPersona().ObtenerPersonaCedula("1105335010"));
        
        ServicioDetalle sd2 = new ServicioDetalle();
        sd2.getDetalle().setAdultos(3);
        sd2.getDetalle().setMenores(3);
        try {
            sd2.getDetalle().setFecha_inicio(new SimpleDateFormat("dd/MM/yyyy").parse("01/12/2018"));
            sd2.getDetalle().setFecha_fin(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2018"));
        } catch (Exception e) {
        }
        sd2.getDetalle().setPago_total(125.50);
        sd2.getDetalle().setReservacion(sr2.getReservacion());
        
        sr2.getReservacion().setDetalle(sd2.getDetalle());
        sr2.guardar();
        
    }
    public List<Reservacion> listarBusqueda(String texto){
        return obj.listarBusqueda(texto);
    }
    public List<Reservacion> listarTipo(String tipo){
        return obj.listarTipo(tipo);
    }
    public List<Reservacion> listarBusquedaTipo(String tipo,String texto){
        return obj.listarBusquedaTipo(tipo, texto);
    }
    public List<Reservacion> ordenAscendente(String orden){
        return obj.ordenAscendente(orden);
    }
}
