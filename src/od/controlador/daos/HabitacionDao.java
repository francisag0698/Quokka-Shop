/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.daos;
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
}
