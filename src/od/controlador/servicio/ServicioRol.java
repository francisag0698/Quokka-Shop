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
 * @author acer
 */
public class ServicioRol {

    private RolDao obj;

    public ServicioRol() {
        this.obj = new RolDao();
    }

    public boolean guardar() {
        return this.guardar();
    }

    public Rol obtenerRol() {
        return obj.getRol();
    }

    public Rol obtenerRol(Long id) {
        return obj.obtener(id);
    }

    public List<Rol> listado() {
        return obj.listar();
    }
    /*
    public Rol incioSesion(String usuario,String clave){
        return obj.inicioSesion(usuario,clave);
    }
     */
}
