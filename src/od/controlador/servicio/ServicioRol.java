/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.servicio;

import java.util.List;
import od.controlador.dao.RolDao;
import od.modelo.Rol;

/**
 *
 * @author Dennis
 */
public class ServicioRol {
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
    
    public Rol buscarRolNombre(String nombre){
        return obj.buscarRolNombre(nombre);
    }
    
    public void crearRoles(){
        if (obj.listar().isEmpty()) {
            getRol().setNombre("Administrador");
            guardar();
            fijarRol(null);
            
            getRol().setNombre("Recepcionista");
            guardar();
            fijarRol(null);
            
            getRol().setNombre("Cliente");
            guardar();
            fijarRol(null);
        }
    }
}
