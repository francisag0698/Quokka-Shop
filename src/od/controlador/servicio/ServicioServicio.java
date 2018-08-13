/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.servicio;

import java.util.List;
import od.controlador.dao.ServicioDao;
import od.modelo.Servicio;

/**
 *
 * @author Dennis
 */
public class ServicioServicio {
    private ServicioDao obj = new ServicioDao();
    
    public Servicio getRol(){
        return obj.getServicio();
    }
    
    public boolean guardar(){
        return obj.guardar();
    }
    
    public List<Servicio>todos(){
        return obj.listar();
    }
    
    public Servicio obtener(Long id){
        return obj.obtener(id);
    }
    public void fijarRol(Servicio servicio){
        obj.setServicio(servicio);
    }
}
