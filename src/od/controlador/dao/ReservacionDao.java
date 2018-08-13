/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;
import od.modelo.Reservacion;

/**
 *
 * @author Dennis
 */
public class ReservacionDao extends AdaptadorDao<Reservacion>{
    
     private Reservacion reservacion;
     public ReservacionDao() {
        super(Reservacion.class);
    }

    public Reservacion getReservacion() {
        if(reservacion==null)
           reservacion= new Reservacion();
        return reservacion;
    }

    public void setReservacion(Reservacion reservacion) {
        this.reservacion = reservacion;
    }
    
    public boolean guardar (){
        boolean verificar = false;
        try{
            getManager().getTransaction().begin();
            if(reservacion.getId_reservacion()!=null){
                modificar(reservacion);
            }else{
                guardar(reservacion);
            }
            getManager().getTransaction().commit();
            verificar = true;
        }catch(Exception e){
            System.out.println("No se ha podido guardar o modificar " + e);
        }
        return verificar;
    }
}
