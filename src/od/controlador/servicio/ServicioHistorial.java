/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.servicio;

import java.util.List;
import od.controlador.dao.HistorialDao;
import od.modelo.Historial;

/**
 *
 * @author Dennis
 */
public class ServicioHistorial {
    private HistorialDao obj = new HistorialDao();
    public Historial getHistorial(){
        return obj.getHistorial();
    }
    public boolean guardar(){
        return obj.guardar();
    }
    
    public List<Historial>todos(){
        return obj.listar();
    }
     public Historial obtener(Long id){
        return obj.obtener(id);
    }
     public void fijarHistorial(Historial historial){
         obj.setHistorial(historial);
     }
     public List<Historial> listarLikeHistorial(String texto) {
        return obj.listarLikeHistorial(texto);
    }
}
