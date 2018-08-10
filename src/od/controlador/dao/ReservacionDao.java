/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;

import java.io.IOException;
import od.controlador.Conexion;
import od.modelo.Reservacion;

/**
 *
 * @author Dennis
 */
public class ReservacionDao extends AdaptadorDao<Reservacion>{
    private Reservacion reservacion;
    public ReservacionDao(){
        super(new Conexion(),Reservacion.class);
    }
    public Reservacion getReservacion(){
        if(reservacion == null)
            reservacion = new Reservacion();
        return reservacion;
    }
    public void fijarInstancia(Reservacion reservacion){
        this.reservacion = reservacion;
    }
     public boolean guardar(){
        boolean band = false;
        try {
            if (reservacion.getId_reservacion()!= null) {
                
            }else{
                this.reservacion.setId_reservacion(generarID());
                this.guardar(reservacion);
            }
            band = true;
        } catch (IOException e) {
            System.out.println("No se pudo guardar "+e);
        }
        return band;
    }
}
