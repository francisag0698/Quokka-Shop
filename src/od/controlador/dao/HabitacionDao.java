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
import od.modelo.Habitacion;

/**
 *
 * @author Dennis
 */
public class HabitacionDao extends AdaptadorDao<Habitacion>{
    private Habitacion habitacion;

    public HabitacionDao() {
        super(Habitacion.class);
    }
    public Habitacion getHabitacion() {
        if(habitacion==null)
           habitacion= new Habitacion();
        return habitacion;
    }
   public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }
    public void Habitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }
    
    public boolean guardar (){
        boolean verificar = false;
        try{
            getManager().getTransaction().begin();
            if(habitacion.getId_habitacion()!=null){
                modificar(habitacion);
            }else{
                guardar(habitacion);
            }
            getManager().getTransaction().commit();
            verificar = true;
        }catch(Exception e){
            System.out.println("No se ha podido guardar o modificar " + e);
        }
        return verificar;
    }
    public List<Habitacion> listarTipo(String tipo){
        List<Habitacion> lista= new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT h FROM Habitacion h where h.tipo = :tipo");  
            q.setParameter("tipo", tipo);
            lista = q.getResultList();
        } catch (Exception e) {
            System.out.println("error"+e);
        }
        return lista;
    }
    public List<Habitacion> listarEstado(Boolean estado){
       List<Habitacion> lista= new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT h FROM Habitacion h where h.estado = :estado");  
            q.setParameter("estado", estado);
            lista = q.getResultList();
        } catch (Exception e) {
            System.out.println("error"+e);
        }
        return lista;
    }
    public List<Habitacion>listarBusqueda(String texto){
        List<Habitacion> lista= new ArrayList<>();
        try {
            Query q = getManager()
                    .createQuery("SELECT h FROM Habitacion h where (LOWER(h.nombre)LIKE CONCAT('%', :texto, '%'))");//lower es minusculas
            q.setParameter("texto", texto);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
    public List<Habitacion> listarBusquedaTipo(String tipo,String texto){
       List<Habitacion> lista= new ArrayList<>();
        try {
            Query q = getManager()
                    .createQuery("SELECT h FROM Habitacion h where (LOWER(h.nombre)LIKE CONCAT('%', :texto, '%')) and h.tipo = :tipo");
            q.setParameter("texto", texto);
            q.setParameter("tipo", tipo);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
    public List<Habitacion> listarBusquedaEstado(Boolean estado,String texto){
        List<Habitacion> lista= new ArrayList<>();
        try {
            Query q = getManager()
                    .createQuery("SELECT h FROM Habitacion h WHERE (LOWER(h.nombre) LIKE CONCAT('%', :texto, '%')) AND h.estado = :estado");
            q.setParameter("texto", texto);
            q.setParameter("estado", estado);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
    
    public List<Habitacion> listarDisponibles(Date inicio, Date fin){
       List<Habitacion> lista= new ArrayList<>();
       try {
            Query q = getManager()
                    .createQuery("SELECT h FROM Habitacion h "
                            + "WHERE h.cantidad > (SELECT COUNT(r) as INT FROM Reservacion r "
                            + "WHERE r.habitacion.id_habitacion = h.id_habitacion "
                            + "AND r.fecha_inicio >= :inicio AND r.fecha_fin <= :fin)");
            q.setParameter("inicio", inicio);
            q.setParameter("fin", fin);
            lista = q.getResultList();
        } catch (Exception e) {
        }
       return lista;
    }
    
    public Long cantidadDisponibles(Date inicio, Date fin, String codigo){
        Long cantidad = new Long(0);
        try {
            Query q = getManager()
                    .createQuery("SELECT h.cantidad - (SELECT COUNT(r) as INT FROM Reservacion r "
                            + "WHERE r.habitacion.codigo = :codigo "
                            + "AND r.fecha_inicio >= :inicio AND r.fecha_fin <= :fin) AS INT "
                            + "FROM Habitacion h WHERE h.codigo = :codigo");
            q.setParameter("inicio", inicio);
            q.setParameter("fin", fin);
            q.setParameter("codigo", codigo);
            cantidad = (Long) q.getSingleResult();
        } catch (Exception e) {
        }
        return cantidad;
    }
}
