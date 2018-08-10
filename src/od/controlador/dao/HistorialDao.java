/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;

import java.io.IOException;
import od.controlador.Conexion;
import od.modelo.Historial;

/**
 *
 * @author Dennis
 */
public class HistorialDao extends AdaptadorDao<Historial>{
    private Historial historial;
    public HistorialDao(){
        super(new Conexion(),Historial.class);
    }
    public Historial getHistorial(){
        if(historial == null)
            historial = new Historial();
        return historial;
    }
    public void fijarInstancia(Historial historial){
        this.historial = historial;
    }
     public boolean guardar(){
        boolean band = false;
        try {
            if (historial.getId_historial()!= null) {
                
            }else{
                this.historial.setId_historial(generarID());
                this.guardar(historial);
            }
            band = true;
        } catch (IOException e) {
            System.out.println("No se pudo guardar "+e);
        }
        return band;
    }
}
