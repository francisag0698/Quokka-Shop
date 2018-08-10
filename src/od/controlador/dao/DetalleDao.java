/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;

import java.io.IOException;
import od.controlador.Conexion;
import od.modelo.Detalle;

/**
 *
 * @author Dennis
 */
public class DetalleDao extends AdaptadorDao<Detalle>{
    private Detalle detalle;
    public DetalleDao(){
        super(new Conexion(),Detalle.class);
    }
    public Detalle getDetalle(){
        if(detalle == null)
            detalle = new Detalle();
        return detalle;
    }
    public void fijarInstancia(Detalle detalle){
        this.detalle = detalle;
    }
     public boolean guardar(){
        boolean band = false;
        try {
            if (detalle.getId_detalle()!= null) {
                
            }else{
                this.detalle.setId_detalle(generarID());
                this.guardar(detalle);
            }
            band = true;
        } catch (IOException e) {
            System.out.println("No se pudo guardar "+e);
        }
        return band;
    }
}
