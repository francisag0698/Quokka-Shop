/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;

import java.io.IOException;
import od.controlador.Conexion;
import od.modelo.Habitacion;

/**
 *
 * @author Dennis
 */
public class HabitacionDao extends AdaptadorDao<Habitacion>{
    private Habitacion habitacion;
    public HabitacionDao(){
        super(new Conexion(),Habitacion.class);
    }
    public Habitacion getHabitacion(){
        if(habitacion == null)
            habitacion = new Habitacion();
        return habitacion;
    }
    public void fijarInstancia(Habitacion habitacion){
        this.habitacion = habitacion;
    }
     public boolean guardar(){
        boolean band = false;
        try {
            if (habitacion.getId_habitacion()!= null) {
                
            }else{
                this.habitacion.setId_habitacion(generarID());
                this.guardar(habitacion);
            }
            band = true;
        } catch (IOException e) {
            System.out.println("No se pudo guardar "+e);
        }
        return band;
    }
}
