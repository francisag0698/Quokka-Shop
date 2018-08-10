/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;

import java.io.IOException;
import od.controlador.Conexion;
import od.modelo.Servicio;

/**
 *
 * @author Dennis
 */
public class ServicioDao extends AdaptadorDao<Servicio>{
    private Servicio servicio;
    public ServicioDao(){
        super(new Conexion(),Servicio.class);
    }
    public Servicio getServicio(){
        if(servicio == null)
            servicio = new Servicio();
        return servicio;
    }
    public void fijarInstancia(Servicio servicio){
        this.servicio = servicio;
    }
     public boolean guardar(){
        boolean band = false;
        try {
            if (servicio.getId_servicio()!= null) {
                
            }else{
                this.servicio.setId_servicio(generarID());
                this.guardar(servicio);
            }
            band = true;
        } catch (IOException e) {
            System.out.println("No se pudo guardar "+e);
        }
        return band;
    }
}
