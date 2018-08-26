/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
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

      public List<Reservacion>listarTipo(String tipo){
        List<Reservacion> lista= new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT p FROM Reservacion p where p.estado = :tipo");  
            q.setParameter("tipo", tipo);
            lista = q.getResultList();
        } catch (Exception e) {
            System.out.println("error"+e);
        }
        return lista;
    }

    public List<Reservacion>listarBusqueda(String texto){
        List<Reservacion> lista= new ArrayList<>();
        try {
            Query q = getManager()
                    .createQuery("SELECT r FROM Reservacion r where ((LOWER(r.persona.apellidos)LIKE CONCAT(:texto, '%')) OR (LOWER(r.persona.nombres)LIKE CONCAT(:texto, '%')))");//lower es minusculas
            q.setParameter("texto", texto);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
    public List<Reservacion> listarBusquedaTipo(String tipo,String texto){
        List<Reservacion> listado= new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT r FROM Reservacion r where ((LOWER(r.persona.apellidos)LIKE CONCAT(:texto, '%')) OR (LOWER(r.persona.nombres)LIKE CONCAT(:texto, '%'))) and r.estado = :tipo");
            q.setParameter("tipo", tipo);
            q.setParameter("texto", texto);
            listado = q.getResultList();
        } catch (Exception e) {
        }
        
        return listado;
    }
     public List<Reservacion> ordenAscendente(String orden){
        orden = (orden.equals("Fecha")) ? "r.fecha" : "r.persona.nombres";
        List<Reservacion> listado= new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT r FROM Reservacion r ORDER BY "+ orden +"  ASC");
            listado = q.getResultList();
        } catch (Exception e) {
        }
        return listado;
    }
}
