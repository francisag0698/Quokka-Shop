/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.servicios;

import java.util.List;
import od.controlador.daos.RolDao;
import od.modelo.Rol;

/**
 *
 * @author Dennis
 */
public class ServicoRol {
    private RolDao obj = new RolDao();
    
    public Rol getRol(){
        return obj.getRol();
    }
    
    public boolean guardar(){
        return obj.guardar();
    }
    
    public List<Rol>todos(){
        return obj.listar();
    }
    
    public Rol obtener(Long id){
        return obj.obtener(id);
    }
    public void fijarRol(Rol rol){
        obj.setRol(rol);
    }
}
